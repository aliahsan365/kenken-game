/*
 * Jonathan Nebot
 */
package presentacion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class ventanaEstadisticaUsuario extends JFrame {
	
	private JPanel contentPane;
    private ControladoraVista cV;
	
    /*
     * Pinta la ventana estadisticas con los datos de data
     */
	public ventanaEstadisticaUsuario(ControladoraVista cV, ArrayList<String> data) {
		super("Estadisticas de Usuario");
		this.cV = cV;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 401);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if (data.size() <= 1) {
			JLabel lab = new JLabel();
			lab.setText(data.get(0));
			lab.setSize(lab.getPreferredSize());
			contentPane.add(lab);
		} else {
			JPanel panelWL = new JPanel();
			panelWL.setBounds(10, 36, 200, 200);
			panelWL.add(createPieChartWL(data.get(2), data.get(1), data.get(0)));
			contentPane.add(panelWL);
			
			JPanel panelRatioHelp = new JPanel();
			panelRatioHelp.setBounds(241, 36, 200, 200);
			panelRatioHelp.add(createPieChartHelpRatios(data.get(8), data.get(9), data.get(10)));
			contentPane.add(panelRatioHelp);
			
			JLabel lblTotalGames = new JLabel("Juegos Totales: "+data.get(0));
			lblTotalGames.setBounds(10, 11, 120, 14);
			contentPane.add(lblTotalGames);
			
			JLabel lblAvgTime = new JLabel("Tiempo Medio Partidas: "+data.get(3)+" s.");
			lblAvgTime.setBounds(10, 247, 170, 14);
			contentPane.add(lblAvgTime);
			
			JLabel lblFastestTime = new JLabel("Tiempo mas Rapido: "+data.get(4)+" s.");
			lblFastestTime.setBounds(10, 297, 170, 14);
			contentPane.add(lblFastestTime);
			
			JLabel lblLowestTime = new JLabel("Tiempo mas Lento: "+data.get(5)+" s.");
			lblLowestTime.setBounds(10, 272, 170, 14);
			contentPane.add(lblLowestTime);
			
			JLabel lblBestPunt = new JLabel("Mejor Puntuacion: "+data.get(6));
			lblBestPunt.setBounds(235, 247, 170, 14);
			contentPane.add(lblBestPunt);
			
			JLabel lblTotalPunt = new JLabel("Puntuacion Total: "+data.get(7));
			lblTotalPunt.setBounds(235, 272, 170, 14);
			contentPane.add(lblTotalPunt);
			
			JLabel lblHelpRatios = new JLabel("Help Ratios:");
			lblHelpRatios.setBounds(241, 11, 75, 14);
			contentPane.add(lblHelpRatios);
		}
		
		JButton btnVolverMenu = new JButton("Volver al Menu");
		btnVolverMenu.setBounds(254, 329, 118, 23);
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaOpcionesJuegos();
				dispose();
			}
		});
		contentPane.add(btnVolverMenu);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cV.vistaEstadisticas();
				dispose();
			}
		});
		btnAtras.setBounds(377, 329, 89, 23);
		contentPane.add(btnAtras);
	}
	
	/*
	 * Crea los datos para el Pie de Win/Loose
	 */
	private static PieDataset createDatasetWR(double win, double loose) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Loose", new Double(loose));
        dataset.setValue("Win", new Double(win));
        return dataset;        
    }
	
	/*
	 * Crea los datos para el Pie de Help Ratios
	 */
	private static PieDataset createDatasetHelpRatios(double cr, double sr, double nr) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Marcar Casilla", new Double(cr));
        dataset.setValue("Siguiente Numero", new Double(nr));
        dataset.setValue("Sugerencias", new Double(sr));
        return dataset;        
    }
    
    /*
     * Crea la grafica segun el dataset
     */
    private static JFreeChart createChart(PieDataset dataset) {
        
        JFreeChart chart = ChartFactory.createPieChart(
            "",  // chart title
            dataset,             // data
            false,               // include legend
            false,
            false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
        
    }
    
    /*
     * Prepara los datos para crear el Pie de Win/Loose
     */
    public static JPanel createPieChartWL(String win, String loose, String total) {
    	double w = Double.parseDouble(win);
    	double l = Double.parseDouble(loose);
    	double t = Double.parseDouble(total);
        JFreeChart chart = createChart(createDatasetWR((w/t)*100, (l/t)*100));
        return new ChartPanel(chart, 200, 200, 200, 200, 200, 200, false, false, false, false, false, false);
    }
    
   /*
    * Prepara los datos para crear el Pie de Help Ratios
    */
    public static JPanel createPieChartHelpRatios(String casRatio, String sugRatio, String nextRatio) {
    	double cr = Double.parseDouble(casRatio);
    	double sr = Double.parseDouble(sugRatio);
    	double nr = Double.parseDouble(nextRatio);
    	double r = cr + sr + nr;
        JFreeChart chart = createChart(createDatasetHelpRatios((cr/r)*100, (sr/r)*100, (nr/r)*100));
        return new ChartPanel(chart, 200, 200, 200, 200, 200, 200, false, false, false, false, false, false);
    }

}
