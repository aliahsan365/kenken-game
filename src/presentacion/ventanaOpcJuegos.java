package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaOpcJuegos extends JFrame {

	private JPanel contentPane;
	private JTextField nombreUser;
    private ControladoraVista cV;
	private int id;


	public ventanaOpcJuegos(ControladoraVista cV) {
		super("Opciones de Juego");
		this.cV = cV;
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario: ");
		lblNombreDeUsuario.setBounds(20, 11, 125, 14);
		contentPane.add(lblNombreDeUsuario);
		
		nombreUser = new JTextField();
		nombreUser.setEditable(false);
		nombreUser.setBounds(135, 8, 125, 20);
		contentPane.add(nombreUser);
		nombreUser.setColumns(10);
		
		JButton btnJugarPartida = new JButton("Jugar Partida");
		btnJugarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesPartida();
				dispose();
			}
		});
		btnJugarPartida.setBounds(0, 32, 305, 46);
		contentPane.add(btnJugarPartida);
		
		JButton btnCragarPartida = new JButton("Cargar Partida");
		btnCragarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaCargarListaP();
				dispose();
			}
		});
		btnCragarPartida.setBounds(0, 89, 305, 44);
		contentPane.add(btnCragarPartida);
		
		JButton btnEstadisticaPersonal = new JButton("Estadistica Personal");
		btnEstadisticaPersonal.setBounds(0, 144, 305, 44);
		btnEstadisticaPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaEstadisticas();
				dispose();
			}
		});
		contentPane.add(btnEstadisticaPersonal);
		
		JButton btnRankingSistema = new JButton("Ranking Sistema");
		btnRankingSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaRanking(1);
				dispose();
			}
		});
		btnRankingSistema.setBounds(0, 199, 305, 44);
		contentPane.add(btnRankingSistema);
		
		JButton btnCustomizarKenken = new JButton("Customizar Kenken");
		btnCustomizarKenken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaNKenkenPersonal();
				dispose();
			}
		});
		btnCustomizarKenken.setBounds(0, 254, 305, 44);
		contentPane.add(btnCustomizarKenken);
		
		JButton btnAyudas = new JButton("Ayudas");
		btnAyudas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistasAyudas();
				dispose();
			}
		});
		btnAyudas.setBounds(0, 308, 305, 44);
		contentPane.add(btnAyudas);
		
		JButton btnOpcionesDeUsuario = new JButton("Opciones de Usuario");
		btnOpcionesDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcUsuario();
				dispose();
			}
		});
		btnOpcionesDeUsuario.setBounds(0, 363, 305, 44);
		contentPane.add(btnOpcionesDeUsuario);
		
		JButton btnSalir = new JButton("Salir ");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			cV.salirDelJuego();
			dispose();
			}
		});
		btnSalir.setBounds(0, 412, 305, 44);
		contentPane.add(btnSalir);
	}
	
	void setNom(String nombre) {
		nombreUser.setText(nombre);
		}
	void setUserId(int id) {
		this.id = id;
		}
	
	
}
