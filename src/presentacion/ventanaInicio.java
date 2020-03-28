package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaInicio extends JFrame {

	private JPanel contentPane;
    private ventanaRegistro ventReg;
    private ControladoraVista cV;

	public ventanaInicio(ControladoraVista cV) {
		super("Inicio");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JButton btnNewButton = new JButton("Jugar\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcJuegosAnonim();
				dispose();
			}
		});
		
		btnNewButton.setBounds(10, 21, 414, 66);
		contentPane.add(btnNewButton);
		
		JButton btnLogin = new JButton("Login\r\n");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaLogin();
			    dispose();
			}
		});
		btnLogin.setBounds(10, 98, 414, 66);
		contentPane.add(btnLogin);
		//ventanaRegistro windRegister = new ventanaRegistro();
	
	
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventReg = new ventanaRegistro(cV);
				ventReg.setVisible(true);
				dispose();
				
				
			
			}
		});
		btnRegistrarse.setBounds(10, 175, 414, 66);
		contentPane.add(btnRegistrarse);
	}

}
