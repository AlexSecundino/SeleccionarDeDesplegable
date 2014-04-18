import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.sql.*;

import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class IES extends JFrame {

	private JPanel contentPane;
	private Connection conection;
	private JComboBox<String> listaInstitutos;
	private JButton btnAñadirIES;
	
	public static String institutoSeleccionado;
	private JButton btnSalir;
	private JButton btnBorrar;
	
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
		setTitle("Institutos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAñadirIES = new JButton("A\u00F1adir Instituto");
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
		btnAñadirIES.setBounds(105, 143, 126, 23);
		contentPane.add(btnAñadirIES);
		
		listaInstitutos = new JComboBox<String>();
		listaInstitutos.setBounds(33, 91, 247, 29);
		contentPane.add(listaInstitutos);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(listaInstitutos.getSelectedIndex() >= 0){
					institutoSeleccionado = listaInstitutos.getSelectedItem().toString();
					Modulos modulos = new Modulos();
					modulos.setVisible(true);
				}
			}
		});
		btnSeleccionar.setBounds(306, 49, 118, 23);
		contentPane.add(btnSeleccionar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(-1);
			}
		});
		btnSalir.setBounds(306, 143, 118, 23);
		contentPane.add(btnSalir);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
					
					try {
						String delete = "delete from ies where descripcion='" + listaInstitutos.getSelectedItem() +"'";
						Statement consulta = conection.createStatement();
						int filasAfectadas = consulta.executeUpdate(delete);

						if(filasAfectadas >= 1){
							JOptionPane.showMessageDialog(contentPane, "Se ha eliminado correctamente.");
						
						
							listaInstitutos.removeAllItems();
							String select = "SELECT descripcion FROM IES";
							Statement consulta5 = conection.createStatement();
							ResultSet resultado = consulta5.executeQuery(select);
	
							while(resultado.next()){
								listaInstitutos.addItem(resultado.getString("descripcion"));
							}
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
		});
		btnBorrar.setBounds(306, 94, 118, 23);
		contentPane.add(btnBorrar);
		
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
	public String getInstitutoSeleccionado() {
		return institutoSeleccionado;
	}

	public JComboBox<String> getComboBox() {
		return listaInstitutos;
	}
	public JButton getBtnNewButton() {
		return btnAñadirIES;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
}
