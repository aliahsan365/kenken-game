package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaOpcUsuario extends JFrame {

	private JPanel contentPane;
    private ControladoraVista cV;

	public ventanaOpcUsuario(ControladoraVista cV) {
		super("Opciones de Usuario");
		this.cV = cV;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 289, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCambiarPassword = new JButton("Cambiar Password ");
		btnCambiarPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaModificarPassword();
				dispose();
			}
		});
		btnCambiarPassword.setBounds(10, 11, 268, 50);
		contentPane.add(btnCambiarPassword);
		
		JButton btnBorrarCuenta = new JButton("Borrar Cuenta");
		btnBorrarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaBorrarCuenta();
				
				dispose();
			}
		});
		btnBorrarCuenta.setBounds(10, 69, 268, 50);
		contentPane.add(btnBorrarCuenta);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(10, 130, 268, 49);
		contentPane.add(btnAtras);
	}

}
