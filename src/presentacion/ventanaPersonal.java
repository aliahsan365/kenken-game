package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaPersonal extends JFrame {

	private JPanel contentPane;
	private JTextField N;
	private ControladoraVista cV;
	

	//Definitivo.
	public ventanaPersonal(ControladoraVista cV) {
		super("Parametros Partida");
		this.cV = cV;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		N = new JTextField();
		N.setBounds(168, 102, 86, 20);
		contentPane.add(N);
		N.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Dificultad");
		lblNewLabel.setBounds(27, 44, 89, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tama\u00F1o");
		lblNewLabel_1.setBounds(27, 105, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Zonas");
		lblNewLabel_2.setBounds(27, 149, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"F\u00E1cil", "Normal", "Dif\u00EDcil"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(168, 43, 86, 17);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Random", "Sumas", "Restas", "Divisiones", "Multiplicaciones", "Sumas y Restas", "Sumas y Divisiones", "Sumas y Multiplicaciones", "Restas y Divisiones", "Restas y Multiplicaciones", "Divisiones y Multiplicaciones", "Sumas, Restas y Divisiones", "Sumas, Restas y Multiplicaciones", "Restas, Divisiones y Multiplicaciones"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(168, 146, 250, 20);
		contentPane.add(comboBox_1);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcJugarPartida();
				dispose();
			}
		});
		btnAtras.setBounds(27, 215, 89, 23);
		contentPane.add(btnAtras);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Comprovacion
				if (comboBox.getSelectedIndex() == -1 || comboBox_1.getSelectedIndex() == -1 || N.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No has introducido bien los datos");
				}
				else {
					int n = Integer.parseInt(N.getText());
					if (n < 1 || n > 25) JOptionPane.showMessageDialog(null, "El Tamaño no es el adecuado ");
					else if (n >= 1 && n <= 5) {
						if ( comboBox.getSelectedIndex() == 0 ) {cV.generarPartidaPersonalizada(n, comboBox.getSelectedIndex()+1, comboBox_1.getSelectedIndex()); dispose();}
						else {JOptionPane.showMessageDialog(null, "El Tamaño no es el adecuado para la dificultad selcionada");}
					     }
					else if (n >= 6 && n <= 15) {
						if ( comboBox.getSelectedIndex() == 1 ) {cV.generarPartidaPersonalizada(n, comboBox.getSelectedIndex()+1, comboBox_1.getSelectedIndex()); dispose();}
						else {JOptionPane.showMessageDialog(null, "El Tamaño no es el adecuado para la dificultad selcionada");}
					    }
					
					else {
						if (comboBox.getSelectedIndex() == 2) {cV.generarPartidaPersonalizada(n, comboBox.getSelectedIndex()+1, comboBox_1.getSelectedIndex()); dispose();}
						else {JOptionPane.showMessageDialog(null, "El Tamaño no es el adecuado para la dificultad selcionada");}
						
						}
					}
				}
	     });
		btnJugar.setBounds(363, 215, 89, 23);
		contentPane.add(btnJugar);
		
		JLabel lblSiFacil = new JLabel("si Facil : Tama\u00F1o 1 a 5");
		lblSiFacil.setBounds(27, 69, 132, 14);
		contentPane.add(lblSiFacil);
		
		JLabel lblSiMedio = new JLabel("Si Normal : Tama\u00F1o  6 a 15");
		lblSiMedio.setBounds(182, 69, 159, 14);
		contentPane.add(lblSiMedio);
		
		JLabel lblSiDificil = new JLabel("Si dificil :Tama\u00F1o 16 a 25");
		lblSiDificil.setBounds(364, 69, 159, 14);
		contentPane.add(lblSiDificil);
	}
	
	
	
	
}
