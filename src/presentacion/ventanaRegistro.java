package presentacion;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domini.UserController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaRegistro extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tUsuario;
	private JPasswordField tPass;
	private JPasswordField tPassRepetido;
	private ventanaInicio ventIni;
    private JButton btnAtras;
    private JButton btnRegistro;
    private ControladoraVista cV;
    

	/**
	 * Create the frame.
	 */
	public ventanaRegistro(ControladoraVista cV) {
		super("Registro");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegistro = new JButton("Registrarse");
		
		btnRegistro.addActionListener(this); 
		

		btnRegistro.setBounds(237, 201, 146, 23);
		contentPane.add(btnRegistro);
		
		tUsuario = new JTextField();
		tUsuario.setBounds(237, 34, 146, 20);
		contentPane.add(tUsuario);
		tUsuario.setColumns(10);
		
		tPass = new JPasswordField();
		tPass.setBounds(237, 89, 146, 20);
		contentPane.add(tPass);
		tPass.setColumns(10);
		
		tPassRepetido = new JPasswordField();
		tPassRepetido.setBounds(237, 148, 146, 20);
		contentPane.add(tPassRepetido);
		tPassRepetido.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(31, 37, 103, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(31, 92, 103, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRepetirPassword = new JLabel("Repetir Password");
		lblRepetirPassword.setBounds(31, 151, 146, 14);
		contentPane.add(lblRepetirPassword);
		
		btnAtras = new JButton("Atras");
        btnAtras.setBounds(31, 201, 89, 23);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(this); 
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== btnAtras) {
			cV.vistaInicio();
			dispose();
        }
		
		if (e.getSource()== btnRegistro) {
			
		
			String nombre , pass , passR;
			nombre = tUsuario.getText() ;
			pass = tPass.getText();
			passR = tPassRepetido.getText();
			
			if (!nombre.equals("") || !pass.equals("") || !passR.equals("")) {
				if (pass.equals(passR)){
					if(cV.crearUserNuevo(nombre,pass)) {
						dispose();
					}
					
					}
				else {
					JOptionPane.showMessageDialog(null, "los passwords no son los mismos");
					}
				 }
			else {
				JOptionPane.showMessageDialog(null, "No hay nada escrito");
				
			}
		 }
	   }
	}
	
		
	
		
	



