import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;


public class Modulos extends JFrame {

	private JPanel contentPane;

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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox listaModulos = new JComboBox();
		listaModulos.setBounds(37, 76, 235, 20);
		contentPane.add(listaModulos);
		
		JButton btnAnadirModulo = new JButton("A\u00F1adir Modulo");
		btnAnadirModulo.setBounds(308, 41, 140, 23);
		contentPane.add(btnAnadirModulo);
		
		JButton btnVerSesiones = new JButton("Ver Sesiones");
		btnVerSesiones.setBounds(308, 75, 140, 23);
		contentPane.add(btnVerSesiones);
		
		JButton btnVerTemario = new JButton("Ver Temario");
		btnVerTemario.setBounds(308, 109, 140, 23);
		contentPane.add(btnVerTemario);
		
		JButton btnCrearSesion = new JButton("Crear Sesion");
		btnCrearSesion.setBounds(308, 143, 140, 23);
		contentPane.add(btnCrearSesion);
	}
}
