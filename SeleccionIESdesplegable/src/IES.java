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

	private JPanel contentPane;
	private Connection conection;
	private JComboBox listaInstitutos;
	private JButton btnAñadirIES;
	
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
		
		btnAñadirIES = new JButton("A\u00F1adir IES");
		btnAñadirIES.addActionListener(new ActionListener() {
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
								listaInstitutos.removeAllItems();
								String select = "SELECT descripcion FROM IES";
								Statement consulta2 = conection.createStatement();
								ResultSet resultado = consulta2.executeQuery(select);

								while(resultado.next()){
									listaInstitutos.addItem(resultado.getString("descripcion"));
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
		btnAñadirIES.setBounds(298, 228, 126, 23);
		contentPane.add(btnAñadirIES);
		
		listaInstitutos = new JComboBox();
		listaInstitutos.setBounds(31, 148, 247, 29);
		contentPane.add(listaInstitutos);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listaInstitutos.getSelectedIndex() >= 0){
					String institutoSelecionado = listaInstitutos.getSelectedItem().toString();
					
					try{
						Class.forName("com.mysql.jdbc.Driver");
						conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
						
						try {
							String select = "select count(*) from asignaturasdelies where codigoIES = (select codigoIES from ies where descripcion = '" + institutoSelecionado + "')";
							Statement consulta = conection.createStatement();
							ResultSet resultado = consulta.executeQuery(select);
							
							if(resultado.next())
							{
								Modulos modulos = new Modulos();
								modulos.setVisible(true);
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
				else
					return;
			}
		});
		btnAceptar.setBounds(317, 151, 89, 23);
		contentPane.add(btnAceptar);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
			
			try {
				String consulta2 = "SELECT descripcion FROM IES";
				Statement consulta = conection.createStatement();
				ResultSet resultado = consulta.executeQuery(consulta2);

				while(resultado.next()){
					listaInstitutos.addItem(resultado.getString("descripcion"));
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
		return listaInstitutos;
	}
	public JButton getBtnNewButton() {
		return btnAñadirIES;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
}
