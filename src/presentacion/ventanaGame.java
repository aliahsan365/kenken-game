/*
 * Toni Palacios
 */

package presentacion;
 
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domini.Pair;

import java.util.ArrayList;
import java.util.Random;
 
public class ventanaGame extends JFrame {
	private static final long serialVersionUID = 3469097333294121983L;
	//Attributes.
	//Data variables.
    private ArrayList<String> stringsOperations;
    private int dataZone[][];
    private int dataDefault[][];
    private int dataValue[][];
	//Default objects.
	private static int menuObj = 6;
	//Table dimensions.
	private int n;
	private static int casillaSize = 50;
	private static int minGridSize = 250;
	private int gridSize;
	private int sizeBtn;
	private static int margin = 10;
	private static int offset = margin*3+5;
	
	//Controller
    private ControladoraVista cv;
    
	private static int getGridSize(int n) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = (int)(gd.getDisplayMode().getWidth()*0.9);
		int height = (int)(gd.getDisplayMode().getHeight()*0.9);
		
		int casilla_size = casillaSize;
		int size = casilla_size*n;
		
		if(size<minGridSize) size = minGridSize;
		
		if(size>width || size>height) {
			if(width>height) return height;
			return width;
		}
		return size;
	}
	
	private int getBtnSize() {
    	int size = 50;
    	
		if(gridSize<=(size*menuObj)) {
			size = gridSize/(menuObj+1);
		}
		
		return size;
    }
	
	public static int tryParseString2Integer(String text) {
		try {
			 int value = Integer.parseInt(text);
			 return value;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
    public void setGrid(Container pane, JTextField grid[][], int dataZone[][], int dataDefault[][], int dataValue[][], ArrayList<JLabel> operations, ArrayList<String> stringsOperations, ArrayList<Integer> dataOperacion, Color colors[]) {
        pane.setLayout(new GridLayout(n,n));
    	pane.setBounds(10, 10, gridSize, gridSize);
    	
    	//Auxiliary variables.
    	Random rand = new Random();
    	int contElementsByZone[] = new int[stringsOperations.size()/2];
    	
        for(int i = 0; i < n; i++) {        	
        	for(int j = 0; j < n; j++) {
        		
        		if(contElementsByZone[dataZone[i][j]] == 0) {
		        	colors[dataZone[i][j]] = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	        	}
        		
        		JTextField input = new JTextField();
        		
	        	grid[i][j] = input;
	        	
	        	setZoneCoord(i, j, grid, colors[dataZone[i][j]]);
		    	contElementsByZone[dataZone[i][j]]++;
	        	
	        	pane.add(grid[i][j]);
	        	
	        	if(dataDefault[i][j]==0) {
	        		
		        	input.getDocument().addDocumentListener(new DocumentListener() {
						public void changedUpdate(DocumentEvent e) {
							setValue(e, dataValue);
						}
						public void removeUpdate(DocumentEvent e) {
							setValue(e, dataValue);
						}
						public void insertUpdate(DocumentEvent e) {
							setValue(e, dataValue);
						}
						
						public void setValue(DocumentEvent e, int dataValue[][]) {						
							int x = pane.getComponentZOrder(input)/n;
		        			int y = pane.getComponentZOrder(input)%n;
		        			
							if(input.getText().isEmpty()) {
								dataValue[x][y] = 0;
							}
							else {
								int value = tryParseString2Integer(input.getText());
								
								if(value<1 || value>n) {
									JOptionPane.showMessageDialog(null,"Se debe introducir un número entero mayor que 0 y menor o igual a "+n+".");
								}
								else {
									dataValue[x][y] = value;
								}
							}
						}
		        	});
		        	
		        	if(dataValue[i][j]>0) input.setText(String.valueOf(dataValue[i][j]));
        		}
        		else {
        			input.setText(String.valueOf(dataDefault[i][j]));
        			input.setEditable(false);
        			
        			Border s = BorderFactory.createLineBorder(colors[dataZone[i][j]]);
	            	input.setBorder(s);
	            	dataValue[i][j] = dataDefault[i][j];
        		}
        	}
        }
    }
    
    public void setOperations(JTextField grid[][], int dataZone[][], ArrayList<JLabel> operations, ArrayList<String> stringsOperations, ArrayList<Integer> dataOperacion, Color colors[]) {
    	int contElementsByZone[] = new int[stringsOperations.size()/2];
    	
    	for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
		    	if(contElementsByZone[dataZone[i][j]] == 0) {
		    		createOperacion(i, j, grid, dataZone, operations, stringsOperations, dataOperacion, colors[dataZone[i][j]]);
		    	}
		    	else ordOperacion(dataZone[i][j], dataZone, grid, operations, dataOperacion);
		    	
		    	contElementsByZone[dataZone[i][j]]++;
        	}
    	}
    }
    
    public static void setZoneCoord(int i, int j, JTextField grid[][], Color randomColor) {
    	grid[i][j].setBackground(randomColor);
    }
    
    public static void setData(int data[][]) {
    	for(int i = 0; i < data.length; i++) {
    		for(int j = 0; j < data[i].length; j++) data[i][j] = -1;
    	}
    }
	
	public static void createOperacion(int x, int y, JTextField grid[][], int dataZone[][], ArrayList<JLabel> operations, ArrayList<String> stringsOperations, ArrayList<Integer> dataOperacion, Color c) {
	
		JLabel operationField = new JLabel();
	
		String text = stringsOperations.get(dataZone[x][y]*2) + stringsOperations.get((dataZone[x][y]*2)+1); 
		operationField.setText(text);
		operationField.setBackground(c);
		
		operationField.setSize(operationField.getPreferredSize());
		
		if(operationField.getWidth()>grid[x][y].getWidth()) {
			int fontSize = (int)(operationField.getFont().getSize()*((double)grid[x][y].getWidth()/operationField.getWidth()));
			if(fontSize<6) fontSize = 6;
			
			operationField.setFont(new Font(operationField.getName(), Font.PLAIN, fontSize));
			operationField.setSize(operationField.getPreferredSize());
			
			operationField.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					JOptionPane.showMessageDialog(null, operationField.getText());
	            }
			});
		}
		
		operationField.setLocation(grid[x][y].getWidth()-operationField.getWidth()-2, 2);
		
		operations.set(dataZone[x][y], operationField);
		dataOperacion.set(dataZone[x][y], x);
		dataOperacion.set(dataZone[x][y]+1, y);
		
		grid[x][y].add(operations.get(dataZone[x][y]));
	
	}
	
	public static void ordOperacion(int zone, int dataZone[][], JTextField grid[][], ArrayList<JLabel> operations, ArrayList<Integer> dataOperacion) {
		
		int maxX, maxY;
		
		maxX = dataZone.length;
		maxY = -1;
		
		for(int x = 0; x < dataZone.length; x++) {
			for(int y = 0; y < dataZone[x].length; y++) {
				if(dataZone[x][y]==zone) {
					if(x<=maxX && y>=maxY) {
						maxX = x;
						maxY = y;
					}
				}
			}
		}
		
		operations.get(zone).setLocation(grid[maxX][maxY].getWidth()-operations.get(zone).getWidth()-2, 2);
		dataOperacion.set(zone, maxX);
		dataOperacion.set(zone+1, maxY);
		
		grid[maxX][maxY].add(operations.get(zone));
	}
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI(JFrame iFrame) {
        //Create and set up the window.
        JFrame frame = iFrame;
        
        //Create and set up the panels.
        JPanel grid = new JPanel();
        JPanel menu = new JPanel();
        
        //Create and set up the buttons.
        JButton resolveBtn = new JButton("Resolver");
        JButton sugerenciaBtn = new JButton("Sugerencia");
        JButton solutionBtn = new JButton("Solucion");
        JButton saveBtn = new JButton("Guardar");
        JButton rendirseBtn = new JButton("Rendirse");
        JButton exitBtn = new JButton("Salir");
        
        //Create and set up the inputs.
        JTextField[][] gridField = new JTextField[n][n];
    	
    	//Create and set up the strings.
        ArrayList<JLabel> operations = new ArrayList<JLabel>();
    	
        //Create and set up the auxiliary variables.
        ArrayList<Integer> dataOperacion = new ArrayList<Integer>();
    	
        //Init resources.
        for(int i = 0; i < stringsOperations.size()/2; i++) {
        	operations.add(new JLabel());
        }
        for(int i = 0; i < stringsOperations.size(); i++) {
        	dataOperacion.add(0);
        }
        
        Color colors[] = new Color[stringsOperations.size()];
        
        //Set up the frames
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, (int)(gridSize*1.5)+offset, gridSize+(int)(offset*1.7));
        
        //Set up the menu.
        menu.setBounds(gridSize+offset, 0, 250, 500);
        menu.setLayout(null);
        
        //Set up the grid.
        setGrid(grid, gridField, dataZone, dataDefault, dataValue, operations, stringsOperations, dataOperacion, colors);
        
        //Set up the buttons menu.
        resolveBtn.setBounds(gridSize+offset, margin, gridSize/2-offset+margin, sizeBtn);
        resolveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cv.resolverKenken(dataValue)) {
            		JOptionPane.showMessageDialog(null, "Aleluya! Has resuleto el Kenken!");
            		cv.finPartida(true);
            		frame.dispose();
            	}
            	else JOptionPane.showMessageDialog(null, "Fa-fa-fa-faaaail!");
			}
		});
        
        
        sugerenciaBtn.setBounds(gridSize+offset, margin*2+sizeBtn, gridSize/2-offset+margin, sizeBtn);
        sugerenciaBtn.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
            	Pair p = cv.ayudaMarcarCasilla(dataValue);
            	
            	if(!p.empty()) {
            	
	            	Border s = BorderFactory.createLineBorder(Color.yellow);
	            	
	            	gridField[p.getFila()][p.getColumna()].setBorder(s);
	            	frame.repaint();
            	}
            	else JOptionPane.showMessageDialog(null,"FAAAAIL");
        	}
        });
        
        solutionBtn.setBounds(gridSize+offset, margin*3+sizeBtn*2, gridSize/2-offset+margin, sizeBtn);
        solutionBtn.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
            	Pair p = cv.ayudaMarcarCasilla(dataValue);
            	
            	if(!p.empty()) {
            	
	            	int value = cv.ayudaNextNum(p);
	            	
	            	Border s = BorderFactory.createLineBorder(Color.red);
	            	
	            	gridField[p.getFila()][p.getColumna()].setText(String.valueOf(value));            	
	            	gridField[p.getFila()][p.getColumna()].setBorder(s);
	            	frame.repaint();
            	}
            	else JOptionPane.showMessageDialog(null,"FAAAAIL");
            }
        });
        
        saveBtn.setBounds(gridSize+offset, margin*4+sizeBtn*3, gridSize/2-offset+margin, sizeBtn);
        saveBtn.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
            	cv.vistaGuardadoPartida(dataValue);
            }
        });
        
        rendirseBtn.setBounds(gridSize+offset, margin*5+sizeBtn*4, gridSize/2-offset+margin, sizeBtn);
        rendirseBtn.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent e) {
            	resolveBtn.setVisible(false);
                sugerenciaBtn.setVisible(false);
                solutionBtn.setVisible(false);
                saveBtn.setVisible(false);
                rendirseBtn.setVisible(false);
                
                int iRes[][] = cv.getResultado();
                
                for(int i = 0; i < gridField.length; i++) {
                	for(int j = 0; j < gridField.length; j++) {
                		gridField[i][j].setText(String.valueOf(iRes[i][j]));
                	}
                }
                
                frame.repaint();
            }
        });
        
        exitBtn.setBounds(gridSize+offset, margin*6+sizeBtn*5, gridSize/2-offset+margin, sizeBtn);
        exitBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
            	cv.finPartida(false);
            	frame.dispose();
            }
		});
        
        //Add the buttons.        
		menu.add(resolveBtn);
		menu.add(sugerenciaBtn);
		menu.add(solutionBtn);
		if(!cv.isUserNull()) menu.add(saveBtn);
		menu.add(rendirseBtn);
		menu.add(exitBtn);
		
		//Add the panels.
		frame.add(grid);
		frame.add(menu);
 
        //Display the window and last release.
        frame.setVisible(true);
        
        setOperations(gridField, dataZone, operations, stringsOperations, dataOperacion, colors);
        frame.repaint();
    }
    
    public ventanaGame(int n, ControladoraVista cv, ArrayList<String> operations, int dataZone[][], int dataDefault[][]) {    	
    	super("TaulellKenken");
    	
    	//Data variables.
    	this.n = n;
    	this.gridSize = getGridSize(this.n);
    	this.sizeBtn = getBtnSize();
        this.dataValue = new int[this.n][this.n];
        
        //Loads from domain.
        this.cv = cv;
        this.stringsOperations = operations;
    	this.dataZone = dataZone;
    	this.dataDefault = dataDefault;
    	
    	createAndShowGUI(this);
    }
    
    public ventanaGame(int n, ControladoraVista cv, ArrayList<String> operations, int dataZone[][], int dataDefault[][], int dataValue[][]) {
    	super("TaulellKenken");
    	
    	//TEST
    	
    	System.out.println("N>>>>>>>>>>>>>"+n);
    	
    	System.out.println("OPERATIONS");
    	for(int i = 0; i < operations.size(); i++) System.out.print(operations.get(i)+" ");
    	
    	System.out.println("DATA_ZONE");
    	for(int i = 0; i < dataZone.length; i++) {
    		System.out.print("|");
    		for(int j = 0; j < dataZone[i].length; j++) System.out.print(dataZone[i][j]+"|");
    		System.out.println();
    	}
    	
    	System.out.println("DATA_DEFAULT");
    	for(int i = 0; i < dataDefault.length; i++) {
    		System.out.print("|");
    		for(int j = 0; j < dataDefault[i].length; j++) System.out.print(dataDefault[i][j]+"|");
    		System.out.println();
    	}
    	
    	System.out.println("DATA_VALUE");
    	for(int i = 0; i < dataValue.length; i++) {
    		System.out.print("|");
    		for(int j = 0; j < dataValue[i].length; j++) System.out.print(dataValue[i][j]+"|");
    		System.out.println();
    	}
    	
    	//Data variables.
    	this.n = n;
    	this.gridSize = getGridSize(this.n);
    	this.sizeBtn = getBtnSize();
        this.dataValue = dataValue;
        
        //Loads from domain.
        this.cv = cv;
        this.stringsOperations = operations;
    	this.dataZone = dataZone;
    	this.dataDefault = dataDefault;
    	
    	createAndShowGUI(this);
    }
}