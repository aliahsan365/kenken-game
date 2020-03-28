package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.util.List;  
import java.util.ArrayList;
import java.util.Arrays;
 

public class ventanaCargarListaP extends JFrame implements ActionListener , ItemListener {

	private JPanel contentPane;
    private ControladoraVista cV;
    private JComboBox comboBox ;
    

	public ventanaCargarListaP(ControladoraVista cV, ArrayList<String> Lista) {
		super("Cargar Partidas");
		this.cV =cV;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 351, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<String> nombres = new ArrayList<String>();
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < Lista.size(); i+=2) {
			nombres.add(Lista.get(i));
			ids.add(Integer.parseInt(Lista.get(i+1)));
		}
		
		
		comboBox = new JComboBox(new DefaultComboBoxModel(nombres.toArray()));
	
		comboBox.setBounds(94, 69, 150, 25);
		contentPane.add(comboBox);
		comboBox.addActionListener(this);
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		btnAtras.setBounds(38, 181, 96, 23);
		contentPane.add(btnAtras);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getItemCount()>0) {
					int id = comboBox.getSelectedIndex();
					cV.cargarPartidaUser(ids.get(id));
					dispose();
				}
				else JOptionPane.showMessageDialog(null,"No existe la partida a cargar.");
			}
		});
		btnSeleccionar.setBounds(186, 181, 109, 23);
		contentPane.add(btnSeleccionar);
		
		JLabel lblPartidas = new JLabel("Partidas");
		lblPartidas.setBounds(145, 32, 150, 14);
		contentPane.add(lblPartidas);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		//NO borrar es implements
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	 //no borrar es de implementes
		
	}
	

	
	
	
	
	
}
