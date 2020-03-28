package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaGuardadoTablero extends JFrame {

	private JPanel contentPane;
	private JTextField nomTab;
    private ControladoraVista cV;


	/**
	 * Create the frame.
	 */
	public ventanaGuardadoTablero(ControladoraVista cV) {
		super("Guardar Tablero");
		this.setResizable(false);
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nomTab = new JTextField();
		nomTab.setBounds(206, 52, 124, 20);
		contentPane.add(nomTab);
		nomTab.setColumns(10);
		
		JLabel lblNombreDeTablero = new JLabel("Nombre de Tablero ");
		lblNombreDeTablero.setBounds(25, 55, 147, 14);
		contentPane.add(lblNombreDeTablero);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(25, 193, 124, 23);
		contentPane.add(btnCancelar);
		
		JButton btnGuaradar = new JButton("Guardar");
		btnGuaradar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = nomTab.getText();
				String sSin = s.replace(" ","_");
				cV.guardarTablero(sSin);
				dispose();
			}
		});
		btnGuaradar.setBounds(206, 193, 124, 23);
		contentPane.add(btnGuaradar);
	}
}
