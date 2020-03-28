package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ventanaPersonalN extends JFrame {

	private JPanel contentPane;
	private JTextField N;
    private ControladoraVista cV;


	public ventanaPersonalN(ControladoraVista cV) {
		super("Kenken Perzonalizado");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 600, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		N = new JTextField();
		N.setBounds(40, 119, 86, 20);
		contentPane.add(N);
		N.setColumns(10);
		
		JLabel lblIntroduceElTamnyo = new JLabel("Introduce el Tamnyo");
		lblIntroduceElTamnyo.setBounds(40, 46, 141, 14);
		contentPane.add(lblIntroduceElTamnyo);
		
		JLabel lblElTamanyoHa = new JLabel("RECUERDA: El Tamanyo ha de ser mayor o igual que 1 y menor o igual a 25");
		lblElTamanyoHa.setLocation(40, 81);
		lblElTamanyoHa.setSize(lblElTamanyoHa.getPreferredSize());
		contentPane.add(lblElTamanyoHa);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		            cV.vistaOpcionesJuegos();
		            dispose();
			}
		});
		btnAtras.setBounds(40, 182, 89, 23);
		contentPane.add(btnAtras);
		
		JButton btnSiguiente = new JButton("Crear");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String s = N.getText();
			    int n = Integer.parseInt(s);
			    if (n < 1 || n > 25) {JOptionPane.showMessageDialog(null, "El tamanyo introducido no es valido");}
			    else {
			    	cV.vistaCustomizado(n);
			    	dispose();
			    }
			}
		});
		btnSiguiente.setBounds(278, 182, 89, 23);
		contentPane.add(btnSiguiente);
	}

	

}




