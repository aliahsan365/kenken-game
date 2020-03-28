/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Customization by Toni Palacios
 */

package presentacion;
 
/*
 * CustomLayoutDemo.java requires one other file:
 *   DiagonalLayout.java
 */
 
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Random;
 
public class ventanaPartidaRapida extends JApplet  {
	/**
	 * 
	 */
	
	private ControladoraVista cV;
	private static final long serialVersionUID = 3469097333294121983L;
	//Table dimensions.
	private static int n = 3;
	private static int casillaSize = 75;
	private static int minGridSize = 250;
	private static int gridSize = getGridSize(n);
	private static int margin = 10;
	private static int offset = margin*3+5;
	//Auxiliary variables.
	private static Color randomColor = Color.white;
	private Random rand;
    //private ArrayList <Color> colores;
   
	private static int getGridSize(int n) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = (int)(gd.getDisplayMode().getWidth()*0.75);
		int height = (int)(gd.getDisplayMode().getHeight()*0.75);
		
		int casilla_size = casillaSize;
		int size = casilla_size*n;
		
		if(size<minGridSize) size = minGridSize;
		
		if(size>width || size>height) {
			if(width>height) return height;
			return width;
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
	
    public static void setGrid(Container pane, JTextField grid[][], int dataZone[][], int dataDefault[][], JFrame frame, ArrayList<JTextPane> operations, ArrayList<Integer> dataOperacion) {
        pane.setLayout(new GridLayout(n,n));
    	pane.setBounds(10, 10, gridSize, gridSize);
    	
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
        		JTextField input = new JTextField();
	        	input.getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						setValue(e, dataDefault);
					}
					public void removeUpdate(DocumentEvent e) {
						setValue(e, dataDefault);
					}
					public void insertUpdate(DocumentEvent e) {
						setValue(e, dataDefault);
					}
					
					public void setValue(DocumentEvent e, int dataDefault[][]) {						
						int x = pane.getComponentZOrder(input)/n;
	        			int y = pane.getComponentZOrder(input)%n;
	        			
						if(input.getText().isEmpty()) {
							dataDefault[x][y] = -1;
						}
						else {
							int value = tryParseString2Integer(input.getText());
							//TODO Change border color when error occurs.
							
							if(value<1 || value>n) {
								System.out.println("Se debe introducir un número entero mayor que 0 y menor o igual a "+n+".");
							}
							else {
								dataDefault[x][y] = value;
							}
						}
					}
	        	});
	        	grid[i][j] = input;
	        	pane.add(grid[i][j]);
        	}
        }
    }
    

    
    
    public static void setColor(Color newColor) {
    	randomColor = newColor;
    }
    
    public static void setZoneCoord(int i, int j, JTextField grid[][], Color randomColor) {
    	grid[i][j].setBackground(randomColor);
    }
    
    public static void setData(int data[][]) {

    	for(int i = 0; i < data.length; i++) {
    		for(int j = 0; j < data[i].length; j++) data[i][j] = -1;
    	}
    }
    public  Color colorGen() {
   	 rand = new Random();
	 float r = rand.nextFloat();
	 float g = rand.nextFloat();
	 float b = rand.nextFloat();
	 Color randomColor = new Color(r, g, b);
     return randomColor;	
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    
    
    
    
    /*
    //esta son tus funciones.
    
	public static void ordOperacion(ArrayList<JButton> zones, int dataZone[][], JTextField grid[][], ArrayList<JTextPane> operations, ArrayList<Integer> dataOperacion) {
		if(cont>0) {
		
			int zone, maxX, maxY;
			
			if(edition) zone = currentZone;
			else zone = zones.size();
			
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
			
			operations.get(zone).setLocation(grid[maxX][maxY].getWidth()-17, 2);
			dataOperacion.set(zone, maxX);
			dataOperacion.set(zone+1, maxY);
			
			grid[maxX][maxY].add(operations.get(zone));
		}
	}
	
	public static void setOperation(JTextField grid[][], int dataZone[][], String operation, ArrayList<JButton> zones, ArrayList<JTextPane> operations) {
		int zone;
		
		if(edition) zone = currentZone;
		else zone = zones.size();
		
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[i].length; j++) {
				if(dataZone[i][j] == zone && grid[i][j].getComponentCount()>0) {
					operations.get(zone).setText(operation);
					
					JTextPane opText = (JTextPane) grid[i][j].getComponent(0);
					opText.setText(operation);
					
					grid[i][j].remove(0);
					grid[i][j].add(opText);
					
					break;
				}
			}
		}
	}
	
	*/
    
 
    
    public static void createAndShowGUI() {
        //Create and set up the window.
        
    	JFrame frame = new JFrame("Partida Rapida ");
        
        //Create and set up the panels.
        JPanel grid = new JPanel();
        JPanel menu = new JPanel();
        
        //Create and set up the inputs.
        JTextField[][] gridField = new JTextField[n][n];
        
        //Create and set up the auxiliary variables.
        Random rand = new Random();
        ArrayList<Integer> dataOperacion = new ArrayList<Integer>();
       
       
        
        //Data variables.
        ArrayList<JTextPane> operations = new ArrayList<JTextPane>();
        int  dataZone[][] =  new int[n][n];
     

        int dataDefault[][] = new int[n][n];
        
       // setData(dataZone);
        //setData(dataDefault);
        
        //Set up the frames
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 561, 373);
        
        //Set up the menu.
        menu.setBounds(gridSize+offset, 0, 250, 500);
        menu.setLayout(null);
        
        //Set up the grid.
        //Set up the colors
        //Paint the table.
        setGrid(grid, gridField, dataZone, dataDefault, frame, operations, dataOperacion);
        
        //settin up the colors
        
        //EMULACION DE INTROCUCCUION DE DATOS PARA DATAOPERATION.
        dataOperacion.add(0);
        dataOperacion.add(1);
        dataOperacion.add(2);
        
        
        //CODIGO REAL DE COLORES.
        int nz = dataOperacion.size(); // devuelve el numero de zonas q hay.
        
        ArrayList<Color> colores = new ArrayList<Color>();
        
        
        for (int i = 0; i < nz; ++i) {
       	 rand = new Random();
 			 float r = rand.nextFloat();
 			 float g = rand.nextFloat();
 			 float b = rand.nextFloat();
 			 Color randomColor = new Color(r, g, b);
 			 colores.add(randomColor);
 			 System.out.println("numero de zonas "+ i + "el color iessimo "+ i );	
       
        }
        
        //vector de colores esta lleno de nz colores.
        
        //EMULACION DE INTRODUCCION DE DATOS PARA DATAZONA.
        //donde le doy el valor a la matriz , la puedes ir cambiando tu para probar q funciona*/
        //cambiar el numero entre un rango de [0..2].
        
        //zona de 0
        
        dataZone[0][0]= 0;
        dataZone[0][1]= 0;
        dataZone[0][2]= 0;
        
        //zona de 1
        dataZone[1][0]= 0;
        dataZone[1][1]= 1;
        dataZone[1][2]= 1;
        
        // zona de 2
        dataZone[2][0]= 2;
        dataZone[2][1]= 2;
        dataZone[2][2]= 2;
        
        
        //Painting the table.
        //CODIGO REAL DE COLORES.
        for (int i = 0; i < nz; ++i) {
        	for (int j = 0; j  < n; ++j) {
        		for (int k = 0; k < n; ++k) {
        			if (dataZone[j][k] == i) {
        			 setZoneCoord(j, k, gridField,colores.get(i));	
        			 System.out.println("el color q voy a pintar es de la casilla " + i +  " "+ j );
        			}
        			
        		}
        	}
        	 
        }
		
        
        //Add the panels.
		frame.getContentPane().add(grid);
		frame.getContentPane().add(menu);
		
		JButton btnSolucionar = new JButton("Solucionar");
		btnSolucionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//codigo de solucionar partida ;
				boolean b = false; //donde true hemos de resolver el kenken.
				if (b) JOptionPane.showMessageDialog(null, "Solucion Correcta");
				else {
					JOptionPane.showMessageDialog(null, "Solucion Incorrecta");
					
				}
			
			}
		});
		
		btnSolucionar.setBounds(336, 30, 127, 46);
		menu.add(btnSolucionar);
		
		JButton btnGuardarPartida = new JButton("Guardar Partida");
		btnGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//codigo de guardar partida(guardar la data que haya en el tablero.) 
			}
		});
		btnGuardarPartida.setBounds(336, 85, 127, 51);
		menu.add(btnGuardarPartida);
		
		JButton btnGuardarKenken = new JButton("Guardar Kenken");
		btnGuardarKenken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//codigo de guardar el estado inicial de tablero(dataDefault)
			}
		});
		btnGuardarKenken.setBounds(336, 147, 127, 46);
		menu.add(btnGuardarKenken);
		
		JButton btnCasillaFacil = new JButton("Casilla Facil");
		btnCasillaFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//tema de ayudas 
			}
		});
		btnCasillaFacil.setBounds(336, 204, 127, 47);
		menu.add(btnCasillaFacil);
		
		JButton btnRellenarCasilla = new JButton("Rellenar Casilla");
		btnRellenarCasilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// tema de dar la sol a una casilla.
			
			}
		});
		btnRellenarCasilla.setBounds(336, 262, 127, 46);
		menu.add(btnRellenarCasilla);
       
		//Display the window.
        frame.setVisible(true);
    }
    
    public ventanaPartidaRapida(ControladoraVista cV) {
    	this.cV = cV;
    	createAndShowGUI();
    }
    /*
    public static void main(String[] args) {
        new ventanaPartidaRapida();
    }
*/

}