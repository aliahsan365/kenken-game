package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domini.MyOwnException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaOpcPartidas extends JFrame {

	private JPanel contentPane;
	private ControladoraVista cV;


	public ventanaOpcPartidas(ControladoraVista cV) {
		super ("Partidas");
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 308, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPartidaPerzonalizada = new JButton("Partida Perzonalizada");
		btnPartidaPerzonalizada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.generarPartidas(2);
				dispose();
			
			}
		});
		btnPartidaPerzonalizada.setBounds(0, 82, 285, 50);
		contentPane.add(btnPartidaPerzonalizada);
		
		JButton btnPartidaKenken = new JButton("Partida Kenken");
		btnPartidaKenken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.generarPartidas(1);
				dispose();
			}
		});
		btnPartidaKenken.setBounds(0, 23, 285, 50);
		contentPane.add(btnPartidaKenken);
		
		JButton btnPartidaRepositorio = new JButton("Partida Repositorio");
		btnPartidaRepositorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cV.vistaPartidaRepositorio();
				} catch (MyOwnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnPartidaRepositorio.setBounds(0, 143, 285, 50);
		contentPane.add(btnPartidaRepositorio);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(0, 202, 285, 50);
		contentPane.add(btnAtras);
	}

}
