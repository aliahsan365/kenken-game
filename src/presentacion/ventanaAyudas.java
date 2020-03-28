package presentacion;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;

//ALI MUHAMMAD 
public class ventanaAyudas extends JFrame {

	private JPanel contentPane;
    private ControladoraVista cV;
    

    
	public ventanaAyudas(ControladoraVista cV) {
	
		super("Ayudas");
		this.setResizable(false);
	    this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 271, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnManual = new JButton("Manual");
		btnManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPdf();
			}
		});
		btnManual.setBounds(10, 30, 235, 43);
		contentPane.add(btnManual);
		
		JButton btnComoJugar = new JButton("Como Jugar");
		btnComoJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWebpage("http://www.kenkenpuzzle.com/howto/solve");
			}
		});
		btnComoJugar.setBounds(10, 84, 235, 44);
		contentPane.add(btnComoJugar);
		
		JButton btnActivarAyudas = new JButton("Activar Ayudas ");
		btnActivarAyudas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistasAyudasUsuario();
				dispose();
			}
		});
		btnActivarAyudas.setBounds(10, 139, 235, 43);
		contentPane.add(btnActivarAyudas);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(10, 193, 235, 43);
		contentPane.add(btnAtras);
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
