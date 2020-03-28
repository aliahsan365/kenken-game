package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaGuardado extends JFrame {

	private JPanel contentPane;
	private JTextField strNombreP;
    private ControladoraVista cV;

	public ventanaGuardado(ControladoraVista cV, int dataValue[][]) {
		super("Ventana guardado");
		
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		strNombreP = new JTextField();
		strNombreP.setBounds(225, 62, 150, 20);
		contentPane.add(strNombreP);
		strNombreP.setColumns(10);
		
		JLabel lblNombreDeLa = new JLabel("Nombre  de la Partida ");
		lblNombreDeLa.setBounds(21, 65, 168, 14);
		contentPane.add(lblNombreDeLa);
		
		JButton btnNoGuaradar = new JButton("Cancelar");
		btnNoGuaradar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNoGuaradar.setBounds(231, 174, 144, 23);
		contentPane.add(btnNoGuaradar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = strNombreP.getText();
				String sSin = s.replace(" ","_");
				cV.guardarPartida(sSin,dataValue);
				dispose();
			
			}
		});
		btnGuardar.setBounds(21, 174, 150, 23);
		contentPane.add(btnGuardar);
	}
	
	
	
}
