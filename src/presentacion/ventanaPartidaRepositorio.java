package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domini.MyOwnException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ventanaPartidaRepositorio extends JFrame implements ActionListener , ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	  private ControladoraVista cV;
	private JComboBox comboBox;

	public ventanaPartidaRepositorio(ControladoraVista cV) throws MyOwnException {
		System.out.println("antes de petar entro en vista  ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.cV = cV;
		ArrayList<String> Lista = new ArrayList<String>();
		Lista = cV.consultarTableros();
		if (Lista.size() == 0)JOptionPane.showMessageDialog(null, "Rellene bien los campos");
		setBounds(100, 100, 450, 300);
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
		
		
		
		JLabel lblPartidasDelRepositorio = new JLabel("Tableros Del  Repositorio");
		lblPartidasDelRepositorio.setBounds(138, 28, 257, 14);
		contentPane.add(lblPartidasDelRepositorio);
		
		JButton btnAtras = new JButton("Atras ");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesPartida();
				dispose();
			}
		});
		btnAtras.setBounds(41, 200, 89, 23);
		contentPane.add(btnAtras);
		
		JButton btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getItemCount()>0) {
					int id = comboBox.getSelectedIndex();
					cV.cargarPartidaRepo(ids.get(id));
					dispose();
				}
			 }
		});
		btnJugar.setBounds(298, 200, 89, 23);
		contentPane.add(btnJugar);
		
		
		comboBox.setBounds(138, 56, 115, 20);
		contentPane.add(comboBox);
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
