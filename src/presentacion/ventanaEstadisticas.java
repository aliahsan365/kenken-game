package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ventanaEstadisticas extends JFrame {
	private JPanel contentPane;
    private ControladoraVista cV;
    
    
	public ventanaEstadisticas(ControladoraVista cV) {
		super("Estadisticas");
	    this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 163, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFacil = new JButton("Facil");
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaEstadisticaUsuario(1);
				dispose();
			}
		});
		btnFacil.setBounds(10, 11, 125, 40);
		contentPane.add(btnFacil);

		JButton btnNormal = new JButton("Normal");
		btnNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaEstadisticaUsuario(2);
				dispose();
			}
		});
		btnNormal.setBounds(10, 62, 125, 40);
		contentPane.add(btnNormal);
		
		JButton btnDificil = new JButton("Dificil");
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaEstadisticaUsuario(3);
				dispose();
			}
		});
		btnDificil.setBounds(10, 113, 125, 40);
		contentPane.add(btnDificil);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(10, 163, 125, 40);
		contentPane.add(btnAtras);
	}
}
