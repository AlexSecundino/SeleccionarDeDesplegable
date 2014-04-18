import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Modulos extends JFrame {

	private JPanel contentPane;
	private Connection conection;
	private JComboBox listaModulos;
	private JButton btnAnadirModulo;
	private JButton btnVerTemario;
	private JButton btnCrearSesion;
	private JButton btnVerSesiones;
	private JButton btnCerrar;
	private JButton btnBorrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modulos frame = new Modulos();
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
	public Modulos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 227);
		setTitle(IES.institutoSeleccionado);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listaModulos = new JComboBox();
		listaModulos.setBounds(37, 46, 235, 20);
		contentPane.add(listaModulos);
		
		btnAnadirModulo = new JButton("A\u00F1adir Modulo");
		btnAnadirModulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreModulo = "";
				nombreModulo = JOptionPane.showInputDialog(contentPane, "Introduzca el nombre del modulo", "Añadir Modulo", 3);
				String numeroHorasLectivas = "";
				numeroHorasLectivas = JOptionPane.showInputDialog(contentPane, "Introduzca el número de horas lectivas del módulo", "Añadir Modulo", 3);
				if(nombreModulo != null)
					if(!nombreModulo.isEmpty()){
						
						try{
							Class.forName("com.mysql.jdbc.Driver");
							conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
							
							try {
								String insertar = "INSERT INTO modulo(descripcion, horasLectivas) values('" + nombreModulo + "','"+ numeroHorasLectivas + "')";
								Statement consulta = conection.createStatement();
								consulta.execute(insertar);
								
								String selectCodIES = "select codigoIES from ies where descripcion ='" + IES.institutoSeleccionado + "'";
								Statement consulta2 = conection.createStatement();
								ResultSet codIES = consulta2.executeQuery(selectCodIES);
								
								String selectCodModulo = "select codigoModulo from modulo where descripcion ='" + nombreModulo +"'";
								Statement consulta3 = conection.createStatement();
								ResultSet codMod = consulta3.executeQuery(selectCodModulo);
								
								if(codIES.next() && codMod.next()){
									System.out.println(codIES.getString("codigoIES"));
									System.out.println(codMod.getString("codigoModulo"));
									String insertar2 = "INSERT INTO asignaturasdelies(codigoIES, codigoModulo) values('" + codIES.getString("codigoIES") + "', '" + codMod.getString("codigoModulo") + "')";
									Statement consulta4 = conection.createStatement();
									consulta4.execute(insertar2);
								}
								
								listaModulos.removeAllItems();
								String select = "select m.descripcion from modulo m INNER JOIN asignaturasdelies adi on m.codigoModulo = adi.codigoModulo INNER JOIN ies i on i.codigoIES = adi.codigoIES where i.descripcion ='"+IES.institutoSeleccionado+"'";
								Statement consulta5 = conection.createStatement();
								ResultSet resultado = consulta5.executeQuery(select);

								while(resultado.next()){
									listaModulos.addItem(resultado.getString("descripcion"));
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
		btnAnadirModulo.setBounds(308, 11, 140, 23);
		contentPane.add(btnAnadirModulo);
		
		btnVerSesiones = new JButton("Ver Sesiones");
		btnVerSesiones.setBounds(160, 110, 140, 23);
		contentPane.add(btnVerSesiones);
		
		btnVerTemario = new JButton("Ver Temario");
		btnVerTemario.setBounds(308, 79, 140, 23);
		contentPane.add(btnVerTemario);
		
		btnCrearSesion = new JButton("Crear Sesion");
		btnCrearSesion.setBounds(10, 110, 140, 23);
		contentPane.add(btnCrearSesion);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setBounds(308, 110, 140, 23);
		contentPane.add(btnCerrar);
		
		btnBorrar = new JButton("Borrar M\u00F3dulo");
		btnBorrar.setBounds(308, 45, 140, 23);
		contentPane.add(btnBorrar);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conection = DriverManager.getConnection("jdbc:mysql://localhost/proyecto1", "root", "");
			
			try {
				String select2 = "select m.descripcion from modulo m INNER JOIN asignaturasdelies adi on m.codigoModulo = adi.codigoModulo INNER JOIN ies i on i.codigoIES = adi.codigoIES where i.descripcion ='"+IES.institutoSeleccionado+"'";
				Statement consulta2 = conection.createStatement();
				ResultSet resultado2 = consulta2.executeQuery(select2);

				while(resultado2.next()){
					listaModulos.addItem(resultado2.getString("descripcion"));
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
	public JComboBox getListaModulos() {
		return listaModulos;
	}
	public JButton getBtnAnadirModulo() {
		return btnAnadirModulo;
	}
	public JButton getBtnVerTemario() {
		return btnVerTemario;
	}
	public JButton getBtnCrearSesion() {
		return btnCrearSesion;
	}
	public JButton getBtnVerSesiones() {
		return btnVerSesiones;
	}
	public JButton getBtnCerrar() {
		return btnCerrar;
	}
}
