package presentacion;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class ventanaOpcJuegosAnonim extends JFrame {

	private JPanel contentPane;
    private ControladoraVista cV;

	public ventanaOpcJuegosAnonim(ControladoraVista cV) {
		super("Opciones de Juego");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuarioAnonimo = new JLabel("Usuario Anonimo");
		lblUsuarioAnonimo.setBounds(290, 11, 110, 14);
		contentPane.add(lblUsuarioAnonimo);
		
		JButton btnJugar = new JButton("Jugar Partida");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcJugarPartidaAnonimo();
				dispose();
				
			}
		});
		btnJugar.setBounds(20, 26, 221, 46);
		contentPane.add(btnJugar);
		
		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaRanking(2);
				dispose();
			}
		});
		btnRanking.setBounds(20, 83, 221, 46);
		contentPane.add(btnRanking);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.salirDelJuego();
			}
		});
		btnSalir.setBounds(20, 268, 221, 46);
		contentPane.add(btnSalir);
		
		JButton btnComoJugar = new JButton("Como Jugar");
		btnComoJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWebpage("http://www.kenkenpuzzle.com/howto/solve");
			}
		});
		btnComoJugar.setBounds(20, 145, 221, 49);
		contentPane.add(btnComoJugar);
		
		JButton btnManual = new JButton("Manual");
		btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPdf();
			}
		});
		btnManual.setBounds(20, 205, 221, 49);
		contentPane.add(btnManual);
	}
	
	private  void openWebpage(String urlString) {
	    try {
	        Desktop.getDesktop().browse(new URL(urlString).toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    

	
     private void openPdf()  {
		
		try {
		     File path = new File ("bin\\datos\\Manual.pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}
	}
	
	
	
	
}
