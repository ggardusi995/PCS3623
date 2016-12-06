package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class MyFrame extends JFrame {

	static MyFrame jf;

	private MyFrame(String title) {
		super(title);
	}

	public static void setup() {
		JPanel jp = new JPanel();

		JTextField select = new JTextField("Mostrar");
		JTextField where = new JTextField("onde");
		JTextField condition = new JTextField("condição");

		JComboBox<String> model = new JComboBox<String>(Listas.models);
		JComboBox<String> field = new JComboBox<String>(Listas.initField);
		JComboBox<String> operation = new JComboBox<String>(Listas.operations);

		JButton search = new JButton("Pesquisar");

		select.setEditable(false);
		where.setEditable(false);

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> m = (JComboBox<String>) jf.getContentPane().getComponent(1);
				String tableName = Listas.tables[m.getSelectedIndex()];
				
				JComboBox<String> f = (JComboBox<String>) jf.getContentPane().getComponent(3);
				JComboBox<String> o = (JComboBox<String>) jf.getContentPane().getComponent(4);
				JTextField c = (JTextField) jf.getContentPane().getComponent(5);
				
				String cond;
				if (c.getText().equals("")) {
					cond = "1=1";
				} else {
					cond = (String) f.getSelectedItem() + o.getSelectedItem() + c.getText();
				}
				DefaultTableModel dtm = DAO.getInstance().select(tableName," WHERE "+cond);
				JTable jtable = new JTable(dtm);
				JScrollPane jsp = new JScrollPane(jtable);
				JPanel tp = new JPanel();
				JButton delete = new JButton("Deletar Linha");
				delete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JScrollPane p = (JScrollPane) tp.getComponent(0);
						JTable t = (JTable) p.getViewport().getView();
						int row = t.getSelectedRow();
						String id = "" + t.getModel().getValueAt(row, 0);
						boolean res = DAO.getInstance().delete(tableName, id);
						if (res) {
							((DefaultTableModel)t.getModel()).removeRow(row);
						}
					}
				});
				JButton update = new JButton("Salvar Linha");
				update.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JScrollPane p = (JScrollPane) tp.getComponent(0);
						JTable t = (JTable) p.getViewport().getView();
						int row = t.getSelectedRow();
						String id = "" + t.getModel().getValueAt(row, 0);
						LinkedList<String> rowData = new LinkedList<String>();
						for (int cnt = 0; cnt < t.getModel().getColumnCount(); cnt++) {
							String n = t.getModel().getValueAt(row, cnt).getClass().getName();
							if (n.equals("java.lang.String") | n.equals("java.sql.Date")) {
								rowData.add("'"+((DefaultTableModel)t.getModel()).getValueAt(row, cnt)+"'");
							} else rowData.add(""+((DefaultTableModel)t.getModel()).getValueAt(row, cnt));
						}
						DAO.getInstance().update(tableName, id, rowData);
					}
				});
				tp.setLayout(new GridBagLayout());
				GridBagConstraints g = new GridBagConstraints();
				g.gridx = 0;
				g.gridy = 0;
				g.gridwidth = 2;
				g.fill = GridBagConstraints.HORIZONTAL;
				tp.add(jsp,g);
				g.gridy = 1;
				g.gridwidth = 1;
				g.weightx = 0.5;
				tp.add(delete,g);
				g.gridx = 1;
				tp.add(update,g);
				//jsp.add
				JFrame tf = new JFrame("Resultados para "+Listas.models[m.getSelectedIndex()]);
				tf.setContentPane(tp);
				tf.setSize(new Dimension(800, 600));
				tf.setLocationRelativeTo(null);
				tf.setVisible(true);
				//JOptionPane.showMessageDialog(null, jsp);
			}
		});

		jp.setLayout(new GridLayout(0,7));
		// 0=select, 1=model, 2=where, 3=field,
		// 4=operation, 5=condition, 6=search, 7=
		jp.add(select);
		jp.add(model);
		jp.add(where);
		jp.add(field);
		jp.add(operation);
		jp.add(condition);
		jp.add(search);

		jf.setSize(new Dimension(700, 100));
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setContentPane(jp);
		jf.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		jf = new MyFrame("Projeto de BD - Transporte Urbano");
		setup();

		JComboBox<String> model;
		int currentModel = 0;
		int selectedModel;

		while (jf.isVisible()) {
			model = (JComboBox<String>) jf.getContentPane().getComponent(1);
			selectedModel = model.getSelectedIndex();
			if (currentModel != selectedModel) {
				currentModel = selectedModel;
				String tableName = Listas.tables[currentModel];
				LinkedList<String> columnNames = DAO.getInstance().getColumnNames(tableName);
				JComboBox<String> field = (JComboBox<String>) jf.getContentPane().getComponent(3);
				field.removeAllItems();
				for (String f : columnNames) {
					field.addItem(f);
				}
			}
		}
	}
}
