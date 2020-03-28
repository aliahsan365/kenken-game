package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaRanking extends JFrame {

	private JPanel contentPane;
    private ControladoraVista cV;
    
	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaRanking frame = new ventanaRanking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaRanking(ControladoraVista cV,int n) {
		super("Ventana Ranking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.cV = cV;
		setBounds(100, 100, 339, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPosicion = new JLabel("Posicion");
		lblPosicion.setBounds(10, 23, 70, 14);
		contentPane.add(lblPosicion);
		
		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setBounds(90, 23, 65, 14);
		contentPane.add(lblUserName);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(225, 23, 46, 14);
		contentPane.add(lblScore);
		
		JButton btnAtras = new JButton("Atras");
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (n == 2) {
					cV.vistaOpcJuegosAnonim();
					dispose();
				}
				else {
					cV.vistaOpcionesJuegos();
					dispose();
					
				}
				
			}
		});
		
		btnAtras.setBounds(101, 464, 122, 23);
		contentPane.add(btnAtras);
		
		ArrayList<String> s = new ArrayList<String>();
		s = cV.consultarRankingVista();
		int tam = s.size();
		int i;
		
		ArrayList<String> s1 = new ArrayList<String>();
		ArrayList<String> s2 = new ArrayList<String>();
		
		for (i = 0; i < tam; ++i ) {
			if (i%2 == 0) {
				s1.add(s.get(i));
				}
			else {
				s2.add(s.get(i));
				}
		}
		
		ArrayList<JTextField> l = new ArrayList<JTextField>();
		ArrayList<JTextField> l2 = new ArrayList<JTextField>();
		
	
		for ( i = 0; i < tam/2;++i) {
			String iString = Integer.toString(i+1);
			
			JTextField text = new JTextField();
			l.add(text);
			l.get(i).setBounds(84, 58+(i*39), 86, 20);
			l.get(i).setEditable(false);
			contentPane.add(l.get(i));
			l.get(i).setColumns(10);
			l.get(i).setText(s1.get(i));
			
			JTextField text2 = new JTextField();
			l2.add(text2);
			l2.get(i).setBounds( 207,58+(i*39), 86, 20);
			l2.get(i).setEditable(false);
			contentPane.add(l2.get(i));
			l2.get(i).setColumns(10);
			l2.get(i).setText(s2.get(i));
			
			
			JLabel label = new JLabel(iString);
			label.setBounds(10, 58+(i*39), 46, 14);
			contentPane.add(label);
		}
		

	}
 }


