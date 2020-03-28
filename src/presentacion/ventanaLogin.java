package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import datos.UserGestor;
import domini.UserController;

public class ventanaLogin extends JFrame implements ActionListener {


	private JPanel contentPane;
	private JTextField strNombreUsuario;
	private JPasswordField strPassword;
    private String Pass;
    private String Nombre;
	private ventanaOpcJuegos vOpcJuegos;
    private ventanaInicio ventIni;
    private JButton btnAtras;
    private JButton btnLogin;
    private ControladoraVista cV;

	
    public ventanaOpcJuegos getVentanaOpcJuegos()  {
    	return vOpcJuegos;
    	
    }
    
    public ventanaLogin(ControladoraVista cV)  {
		super("Login");
		this.cV = cV;
		this.setResizable(false);
		Nombre = " ";
		Pass = " ";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(36, 61, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 148, 107, 14);
		contentPane.add(lblPassword);
		
		strNombreUsuario = new JTextField();
		strNombreUsuario.setBounds(202, 58, 202, 20);
		contentPane.add(strNombreUsuario);
		strNombreUsuario.setColumns(10);
		
		strPassword = new JPasswordField();
		strPassword.setColumns(10);
		strPassword.setBounds(202, 145, 202, 20);
		contentPane.add(strPassword);
		//vOpcJuegos = new ventanaOpcJuegos();
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(247, 208, 89, 23);
		contentPane.add(btnLogin);
		
		
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(this);
		btnAtras.setBounds(32, 208, 89, 23);
		contentPane.add(btnAtras);
	}
	
	
    @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String nombre = strNombreUsuario.getText();
            String pass = strPassword.getText();
            
            if (!nombre.equals("") && !pass.equals("")) {
            	boolean cerrar = cV.loguearse(nombre,pass);
            	if (cerrar ) dispose();
            	}
            else {
            	JOptionPane.showMessageDialog(null, "Rellene bien los campos");
            	
            }
           }
            	
            
           
			
		if (e.getSource() == btnAtras) {
			ventIni = new ventanaInicio(cV);
			ventIni.setVisible(true);
			dispose();
		}
	}

}
