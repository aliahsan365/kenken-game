package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaOpcPartidasAnonim extends JFrame {

	private JPanel contentPane;
    private ControladoraVista cV;

    
	public ventanaOpcPartidasAnonim(ControladoraVista cV) {
        super("Opciones de Partida");
    	this.setResizable(false);
        this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPartidaRapida = new JButton("Partida Rapida ");
		btnPartidaRapida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.generarPartidaRapidaAnonimo();
				dispose();
			}
		});
		btnPartidaRapida.setBounds(10, 11, 333, 57);
		contentPane.add(btnPartidaRapida);
		
		JButton btnPartidaPerzonalizada = new JButton("Partida Perzonalizada ");
		btnPartidaPerzonalizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaPersonal();
				dispose();
			}
		});
		btnPartidaPerzonalizada.setBounds(10, 72, 333, 51);
		contentPane.add(btnPartidaPerzonalizada);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcJuegosAnonim();
				dispose();
			}
		});
		btnAtras.setBounds(10, 126, 333, 51);
		contentPane.add(btnAtras);
	}

}
