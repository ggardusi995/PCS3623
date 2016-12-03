package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.DAO;

public class MyFrame extends JFrame {

	static MyFrame jf;

	private MyFrame() {
	}

	public static void setup() {
		JPanel jp = new JPanel();

		JTextField select = new JTextField("Mostrar");
		JTextField where = new JTextField("onde");
		JTextField condition = new JTextField("condição");

		JComboBox<String> model = new JComboBox<String>(Listas.models);
		JComboBox<String> field = new JComboBox<String>();
		JComboBox<String> operation = new JComboBox<String>(Listas.operations);

		JButton search = new JButton("Pesquisar");

		select.setEditable(false);
		where.setEditable(false);
		condition.setPreferredSize(new Dimension(150,25));

		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("oi");
				JComboBox<String> m = (JComboBox<String>) jf.getContentPane().getComponent(1);
				String tableName = Listas.tables[m.getSelectedIndex()];
				
				JComboBox<String> f = (JComboBox<String>) jf.getContentPane().getComponent(3);
				JComboBox<String> o = (JComboBox<String>) jf.getContentPane().getComponent(4);
				JTextField c = (JTextField) jf.getContentPane().getComponent(5);
				
				String cond = (String) f.getSelectedItem() + o.getSelectedItem() + c.getText();
				
				DefaultTableModel dtm = DAO.getInstance().select(tableName," WHERE "+cond);
				JTable jtable = new JTable(dtm);
				JOptionPane.showMessageDialog(null, new JScrollPane(jtable));
			}
		});

		// 0=select, 1=model, 2=where, 3=field,
		// 4=operation, 5=condition, 6=search, 7=result
		jp.add(select);
		jp.add(model);
		jp.add(where);
		jp.add(field);
		jp.add(operation);
		jp.add(condition);
		jp.add(search);

		jf.setSize(new Dimension(800, 600));
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.setContentPane(jp);
		jf.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		jf = new MyFrame();
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
