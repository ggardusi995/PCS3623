package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Viagem;
import dao.DAO;

public class MyFrame extends JFrame {

	static MyFrame jf;

	private MyFrame() {
	}

	public static void setup() {
		JPanel jp = new JPanel();

		JTextField select = new JTextField("Mostrar");
		JTextField where = new JTextField("onde");
		JTextField condition = new JTextField("Condi��o");

		JComboBox<String> model = new JComboBox<String>(Listas.models);
		JComboBox<String> field = new JComboBox<String>();
		JComboBox<String> operation = new JComboBox<String>(Listas.operations);

		JButton search = new JButton("Pesquisar");

		JTable result = new JTable();

		select.setEditable(false);
		where.setEditable(false);
		condition.setPreferredSize(new Dimension(150,25));

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JButton search = (JButton) e.getSource();
				// JPanel jp = search.get
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
		jp.add(result, BorderLayout.SOUTH);

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
				LinkedList<String> fields = new LinkedList<String>();
				LinkedList<Viagem> v = DAO.getInstance().selectViagem();
				for (Viagem viagem : v) {
					System.out.println(viagem.getCarro().getModelo());
				}
				jf.getContentPane().remove(7);
				JTable table;
			}
		}
	}
}
