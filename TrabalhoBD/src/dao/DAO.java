package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DAO {

	private static final DAO instance = new DAO();

	private DAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/TrabBD", "postgres", "1234");
	}

	public static DAO getInstance() {
		return instance;
	}

	/**
	private Motorista getMotorista(ResultSet rs) throws SQLException {
		Motorista m = new Motorista();
		m.setCPF(rs.getString("m_cpf"));
		m.setRG(rs.getString("m_rg"));
		m.setCNH(rs.getString("cnh"));
		m.setNome(rs.getString("m_nome"));
		m.setAvaliacao(rs.getInt("avaliacao"));
		return m;
	}
	
	private Carro getCarro(ResultSet rs) throws SQLException {
		Carro c = new Carro();
		c.setPlaca(rs.getString("placa"));
		c.setRenavam(rs.getString("renavam"));
		c.setPremium(rs.getBoolean("premium"));
		c.setModelo(rs.getString("modelo"));
		c.setCor(rs.getString("cor"));
		return c;
	}
	
	private Cliente getCliente(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.setCPF(rs.getString("cpf"));
		c.setRG(rs.getString("rg"));
		c.setNome(rs.getString("nome"));
		c.setTelefone(rs.getString("telefone"));
		c.setEmail(rs.getString("email"));
		return c;
	}
	
	private Percurso getPercurso(ResultSet rs) throws SQLException {
		Percurso p = new Percurso();
		p.setOrigem(rs.getString("origem"));
		p.setDestino(rs.getString("destino"));
		p.setDistancia(rs.getDouble("distancia"));
		p.setTempoInicial(rs.getTime("tempo_inicial"));
		p.setTempoFinal(rs.getTime("tempo_final"));
		return p;
	}
	
	private Cartao getCartao(ResultSet rs) throws SQLException {
		Cartao c = new Cartao();
		c.setNumero(rs.getString("numero"));
		c.setBandeira(rs.getString("bandeira"));
		c.setBanco(rs.getString("banco"));
		c.setTitular(rs.getString("titular"));
		c.setVencimento(rs.getDate("vencimento"));
		return c;
	}
	
	private Viagem getViagem(ResultSet rs) throws SQLException {
		Viagem v = new Viagem();
		v.setData(rs.getDate("data_viagem"));
		v.setValor(rs.getDouble("valor"));
		v.setCarro(getCarro(rs));
		v.setCliente(getCliente(rs));
		v.setMotorista(getMotorista(rs));
		v.setPercurso(getPercurso(rs));
		return v;
	}
	**/
	
	public boolean delete(String table, String id) {
		Connection conn = null;
		boolean res = false;
		try {
			conn = getConnection();
			String query = "DELETE FROM "+table+" WHERE id = "+id;
			PreparedStatement ps = conn.prepareStatement(query);
			res = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public void update(String table, String id, LinkedList<String> row) {
		Connection conn = null;
		try {
			conn = getConnection();
			String query = "UPDATE "+table+" SET ";
			LinkedList<String> cols = getColumnNames(table);
			for (int cnt = 1; cnt < (cols.size()-1); cnt++) {
				query += (cols.get(cnt)+" = "+row.get(cnt)+", ");
			}
			query += (cols.get(cols.size()-1)+" = "+row.get(cols.size()-1));
			query += "WHERE id = "+id;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public DefaultTableModel select(String table, String condition) {
		//LinkedList<Viagem> viagens = new LinkedList<Viagem>();
		Connection conn = null;
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		try {
			conn = getConnection();
			String query = "SELECT * FROM " + table + condition;
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();

		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
			
			while (rs.next()) {
				Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
				//viagens.add(getViagem(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return new DefaultTableModel(data, columnNames);
	}
	
	public LinkedList<String> getColumnNames(String table) {
		DefaultTableModel dtm = select(table, "");
		LinkedList<String> columnNames = new LinkedList<String>();
		for (int cnt = 0; cnt < dtm.getColumnCount(); cnt++) {
			columnNames.add(dtm.getColumnName(cnt));
		}
		return columnNames;
	}
}
