package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//ALI MUHAMMAD 
public class ventanaBorrarUser extends JFrame {

	private JPanel contentPane;
	private JTextField nombreUser;
	private JPasswordField pass;
	private JPasswordField passR;
    private ControladoraVista cV;
    private JButton btnAtras;
    private JButton btnBorrarCuenta;

	public ventanaBorrarUser(ControladoraVista cV) {
		super("Borrar Usuario");
		this.cV = cV;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre Usuario");
		lblNewLabel.setBounds(27, 28, 114, 14);
		contentPane.add(lblNewLabel);
		
		nombreUser = new JTextField();
		nombreUser.setEditable(false);
		nombreUser.setBounds(151, 25, 114, 20);
		contentPane.add(nombreUser);
		nombreUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setBounds(27, 96, 86, 14);
		contentPane.add(lblPassword);
		
		pass = new JPasswordField();
		pass.setBounds(151, 93, 114, 20);
		contentPane.add(pass);
		pass.setColumns(10);
		
		JLabel lblPasswordRepetido = new JLabel("Password Repetido");
		lblPasswordRepetido.setBounds(27, 161, 114, 14);
		contentPane.add(lblPasswordRepetido);
		
		passR = new JPasswordField();
		passR.setBounds(151, 158, 114, 20);
		contentPane.add(passR);
		passR.setColumns(10);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcUsuario();
				dispose();
			}
		});
		btnAtras.setBounds(24, 198, 89, 23);
		contentPane.add(btnAtras);
		
		btnBorrarCuenta = new JButton("Borrar Cuenta");
		btnBorrarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p,p2;
				p = pass.getText();
				p2 = passR.getText();
				
				if (p.equals("")||p2.equals("")) {
					JOptionPane.showMessageDialog(null, "Rellene bien los campos");
					
				}
				else  {
					if (p.equals(p2)) {	
						if (cV.borrarCuenta()) {
								dispose();
							}
						}
					else {
						JOptionPane.showMessageDialog(null, "El pass esta mal escrito");
					}
					
				}
			
			}
		});
		btnBorrarCuenta.setBounds(151, 198, 114, 23);
		contentPane.add(btnBorrarCuenta);
	}
	
	
	public void setNombre(String nom) {
		nombreUser.setText(nom);
		
	}

}
