package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domini.UserController;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ventanaAyudasUsuario extends JFrame {
	
	//private UserController uc;
	private JPanel contentPane;
	private JButton btnAtras;
	private JButton btnAcept;
	private JCheckBox checkMarcar;
	private JCheckBox checkSug;
	private JCheckBox checkNext;
	private ControladoraVista cV;
	
    public ventanaAyudasUsuario(ControladoraVista cV) {
    	super("Ayudas de Usuario");
    	//uc = UserController.getInstance();
    	//uc.login("jona", "asd");
    	boolean[] ayu = cV.getAyudas(); // esta en cv ha de llamar uc.getAyudas 
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		checkSug = new JCheckBox("Sugerencia", ayu[0]);
		checkSug.setBounds(6, 145, 120, 23);
		contentPane.add(checkSug);
		
		checkMarcar = new JCheckBox("Marcar Casilla", ayu[1]);
		checkMarcar.setBounds(6, 25, 120, 23);
		contentPane.add(checkMarcar);
		
		checkNext = new JCheckBox("Siguiente Num", ayu[2]);
		checkNext.setBounds(6, 85, 120, 23);
		contentPane.add(checkNext);
		
		JLabel lblAyudaQuePermite = new JLabel("Ayuda que permite que te se\u00F1alen la casilla m\u00E1s");
		lblAyudaQuePermite.setBounds(132, 25, 292, 23);
		contentPane.add(lblAyudaQuePermite);
		
		JLabel lblFavorableParaSer = new JLabel("favorable para ser resuelta");
		lblFavorableParaSer.setBounds(132, 40, 292, 23);
		contentPane.add(lblFavorableParaSer);
		
		JLabel lblAyudaQuePermite_1 = new JLabel("Ayuda que permite que te a\u00F1adan un resultado en");
		lblAyudaQuePermite_1.setBounds(132, 85, 292, 23);
		contentPane.add(lblAyudaQuePermite_1);
		
		JLabel lblAyudaQuePermite_2 = new JLabel("Ayuda que permite que vayan apareciendo suge-");
		lblAyudaQuePermite_2.setBounds(132, 145, 292, 23);
		contentPane.add(lblAyudaQuePermite_2);
		
		JLabel lblCasillaMsFavorable = new JLabel("la casilla m\u00E1s favorable");
		lblCasillaMsFavorable.setBounds(132, 100, 292, 23);
		contentPane.add(lblCasillaMsFavorable);
		
		JLabel lblAyuda = new JLabel("rencias");
		lblAyuda.setBounds(132, 160, 292, 23);
		contentPane.add(lblAyuda);
		
		btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(335, 228, 89, 23);
		contentPane.add(btnAtras);
		
		btnAcept = new JButton("Aceptar");
		btnAcept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//uc.setAyudas(checkSug.isSelected(), checkMarcar.isSelected(), checkNext.isSelected());
				cV.setAyudas(checkSug.isSelected(), checkMarcar.isSelected(), checkNext.isSelected());
				cV.vistasAyudas();
				dispose();
			}
		});
		btnAcept.setBounds(239, 228, 89, 23);
		contentPane.add(btnAcept);
    	
    }
}
	
	
