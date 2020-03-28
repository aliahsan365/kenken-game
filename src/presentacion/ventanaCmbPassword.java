package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ventanaCmbPassword extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JButton btnAtras;
	private JButton btnModificar;
    private ControladoraVista cV;
    private JPasswordField passOld;
    private JPasswordField passNew;
    private JPasswordField passNewRepe;

	public ventanaCmbPassword(ControladoraVista cV) {
		super("Modificar Password");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nombre = new JTextField();
		nombre.setEditable(false);
		nombre.setBounds(207, 11, 140, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(25, 17, 123, 14);
		contentPane.add(lblNombreUsuario);
		
		JLabel lblPasswordAntiguo = new JLabel("Password Antiguo");
		lblPasswordAntiguo.setBounds(25, 74, 123, 14);
		contentPane.add(lblPasswordAntiguo);
		
		JLabel lblPasswordNuevo = new JLabel("Password Nuevo");
		lblPasswordNuevo.setBounds(25, 137, 140, 14);
		contentPane.add(lblPasswordNuevo);
		
		JLabel lblPasswordNuevoRepetido = new JLabel("Password Nuevo Repetido");
		lblPasswordNuevoRepetido.setBounds(25, 182, 160, 14);
		contentPane.add(lblPasswordNuevoRepetido);
		
		btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcUsuario();
				dispose();
			}
		});
		btnAtras.setBounds(25, 228, 123, 23);
		contentPane.add(btnAtras);
		
		btnModificar = new JButton("Modificar ");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String passO,passN,passNR;
			    passO = passOld.getText(); 
			    passN = passNew.getText();
			    passNR = passNewRepe.getText();
			    if (passO.equals("") || passN.equals("") || passNR.equals("")) {
			    	JOptionPane.showMessageDialog(null, "Rellene bien los campos");
			    	
			    }
			    else {
			    	if (!passN.equals(passNR)) {
			    		JOptionPane.showMessageDialog(null, "Los passwords nuevos no coinciden");
			    	}
			    	else {
			    		if(cV.modificarPassword(passO,passN)) {dispose();}
			    		
			    		
			    	}
			    }
			}
		});
		btnModificar.setBounds(204, 228, 143, 23);
		contentPane.add(btnModificar);
		
		passOld = new JPasswordField();
		passOld.setBounds(209, 71, 140, 20);
		contentPane.add(passOld);
		
		passNew = new JPasswordField();
		passNew.setBounds(209, 134, 140, 20);
		contentPane.add(passNew);
		
		passNewRepe = new JPasswordField();
		passNewRepe.setBounds(207, 179, 143, 20);
		contentPane.add(passNewRepe);
	}

	public void setNombre(String name) {
		nombre.setText(name);
		}
}
