import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class IES extends JFrame {

	private static final Object[] String = null;
	private JPanel contentPane;
	private Connection conection;
	private JComboBox comboBox;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IES frame = new IES();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IES() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("A\u00F1adir IES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreInstituto = "";
				nombreInstituto = JOptionPane.showInputDialog(contentPane, "Introduzca el nombre del instituto", "Nuevo instituto", 3);
				if(nombreInstituto != null)
					if(!nombreInstituto.isEmpty()){
						
						try{
							Class.forName("com.mysql.jdbc.Driver");
							conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
							
							try {
								String insertar = "INSERT INTO ies(descripcion) values('" + nombreInstituto + "')";
								Statement consulta = conection.createStatement();
								consulta.execute(insertar);
								comboBox.removeAllItems();
								String select = "SELECT descripcion FROM IES";
								Statement consulta2 = conection.createStatement();
								ResultSet resultado = consulta2.executeQuery(select);

								while(resultado.next()){
									comboBox.addItem(resultado.getString("descripcion"));
								}
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(contentPane, e1.getMessage());
							}
						}
						catch(ClassNotFoundException e1){
							JOptionPane.showMessageDialog(contentPane, e1.getMessage());
						}
						catch(SQLException e1){
							JOptionPane.showMessageDialog(contentPane, e1.getMessage());
						}
						catch(Exception e1){
							JOptionPane.showMessageDialog(contentPane, e1.getMessage());
						}
					}
			}
		});
		btnNewButton.setBounds(147, 190, 126, 23);
		contentPane.add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setBounds(75, 58, 247, 29);
		contentPane.add(comboBox);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
			
			try {
				String consulta2 = "SELECT descripcion FROM IES";
				Statement consulta = conection.createStatement();
				ResultSet resultado = consulta.executeQuery(consulta2);

				while(resultado.next()){
					comboBox.addItem(resultado.getString("descripcion"));
				}
				
				
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(contentPane, e1.getMessage());
			}
		}
		catch(ClassNotFoundException e1){
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
		catch(SQLException e1){
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
		catch(Exception e1){
			JOptionPane.showMessageDialog(contentPane, e1.getMessage());
		}
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	public JButton getBtnNewButton() {
		return btnNewButton;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
}
