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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.Random;

public class CustomGame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3469097333294121983L;
	private ControladoraVista cv;
	// Table dimensions.
	private int n;
	private ArrayList<JLabel> operations;
	private int dataZone[][];;
	private int dataDefault[][];
	private static int casillaSize = 50;
	private static int minGridSize = 250;
	private static int sizeCoord = 65;
	private static int sizeBtn = 30;
	private static int sizeZonebtn = 25;
	private int gridSize;
	private int maxNumCoords;
	private static int margin = 10;
	private static int offset = margin * 3 + 5;
	// Default objects.
	private static int menuObj = 3;
	private static int zonePopupObj = 5;
	// Auxiliary variables.
	private static Color randomColor = Color.white;
	private static int cont = 0;
	private static int currentZone = -1;
	private static String defaultOperation = "+";
	private static boolean edition = false;

	private int getGridSize(int n) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = (int) (gd.getDisplayMode().getWidth() * 0.9);
		int height = (int) (gd.getDisplayMode().getHeight() * 0.9);

		int casilla_size = casillaSize;
		int size = casilla_size * n;

		if (size < minGridSize)
			size = minGridSize;

		if (size > width || size > height) {
			if (width > height)
				return height;
			return width;
		}
		return size;
	}

	private int getMaxNumCoords(int gridSize, int elementSize) {
		return (gridSize / elementSize) - 1;
	}

	public static int tryParseString2Integer(String text) {
		try {
			int value = Integer.parseInt(text);
			return value;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public void setGrid(Container pane, JTextField grid[][], JButton coords[][], ArrayList<JButton> zones,
			int dataZone[][], int dataDefault[][], JFrame zonePopup, JFrame frame, ArrayList<JLabel> operations,
			ArrayList<Integer> dataOperacion) {
		pane.setLayout(new GridLayout(n, n));
		pane.setBounds(10, 10, gridSize, gridSize);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
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
						int x = pane.getComponentZOrder(input) / n;
						int y = pane.getComponentZOrder(input) % n;

						if (input.getText().isEmpty()) {
							dataDefault[x][y] = 0;
						} else {
							int value = tryParseString2Integer(input.getText());

							if (value < 1 || value > n) {
								JOptionPane.showMessageDialog(null,
										"Se debe introducir un número entero mayor que 0 y menor o igual a " + n + ".");
							} else {
								dataDefault[x][y] = value;
							}
						}
					}
				});
				input.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						int x = pane.getComponentZOrder(e.getComponent()) / n;
						int y = pane.getComponentZOrder(e.getComponent()) % n;

						if (zonePopup.isVisible()) {
							if (notAddYet(x, y, coords) && !isCurrentZone(x, y, dataZone)) {

								setZoneCoord(x, y, grid, randomColor);
								coords[x][y] = new JButton("(" + x + "," + y + ")");

								if (!edition)
									dataZone[x][y] = zones.size();
								else
									dataZone[x][y] = currentZone;

								int cont_aux = cont % maxNumCoords;

								int xBound = ((10 * (cont_aux + 1)) + (sizeCoord * cont_aux));
								int yBound = ((cont / maxNumCoords) * 35) + 45;

								coords[x][y].setBounds(xBound, yBound, sizeCoord, 25);
								coords[x][y].addMouseListener(new MouseAdapter() {
									public void mousePressed(MouseEvent e) {
										zonePopup.remove(coords[x][y]);
										coords[x][y] = null;
										dataZone[x][y] = -1;
										cont--;

										if (clearZoneCoord(x, y, grid)) {
											ordOperacion(zones, dataZone, grid, operations, dataOperacion);

											if (cont == 0) {
												if (edition) {
													dataOperacion.remove(currentZone * 2);
													dataOperacion.remove(currentZone * 2);
													operations.remove(currentZone);
												} else {
													dataOperacion.remove((operations.size() - 1) * 2);
													dataOperacion.remove((operations.size() - 1) * 2);
													operations.remove(operations.size() - 1);
												}
											}
										}
										ordZoneCoords(zonePopup);
										zonePopup.repaint();
										frame.repaint();
									}
								});

								if (cont == 0)
									createOperacion(x, y, grid, operations, dataOperacion);
								else
									ordOperacion(zones, dataZone, grid, operations, dataOperacion);

								// Add coordinate and repaint zone pop-up.
								zonePopup.add(coords[x][y]);
								zonePopup.repaint();
								frame.repaint();

								cont++;
							} else if (isCurrentZone(x, y, dataZone)) {
								zonePopup.remove(coords[x][y]);
								coords[x][y] = null;
								dataZone[x][y] = -1;
								cont--;

								if (clearZoneCoord(x, y, grid)) {
									ordOperacion(zones, dataZone, grid, operations, dataOperacion);

									if (cont == 0) {
										if (edition) {
											dataOperacion.remove(currentZone * 2);
											dataOperacion.remove(currentZone * 2);
											operations.remove(currentZone);
										} else {
											dataOperacion.remove((operations.size() - 1) * 2);
											dataOperacion.remove((operations.size() - 1) * 2);
											operations.remove(operations.size() - 1);
										}
									}
								}
								ordZoneCoords(zonePopup);
								zonePopup.repaint();
								frame.repaint();
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

	public static boolean clearZoneCoord(int i, int j, JTextField grid[][]) {
		grid[i][j].setBackground(Color.white);
		if (grid[i][j].getComponentCount() > 0) {
			grid[i][j].remove(0);
			return true;
		}
		return false;
	}

	public void ordZoneCoords(JFrame zone) {
		for (int i = zonePopupObj; i < zone.getContentPane().getComponentCount(); i++) {
			int cont_aux = (i - zonePopupObj) % maxNumCoords;

			int xBound = ((10 * (cont_aux + 1)) + (sizeCoord * cont_aux));
			int yBound = (((i - zonePopupObj) / maxNumCoords) * 35) + 45;

			zone.getContentPane().getComponent(i).setBounds(xBound, yBound, sizeCoord, 25);
		}
	}

	public static boolean notAddYet(int x, int y, JButton coords[][]) {
		if (coords[x][y] == null)
			return true;

		return false;
	}

	public static boolean isCurrentZone(int x, int y, int dataZone[][]) {
		if (dataZone[x][y] == currentZone)
			return true;

		return false;
	}

	public static void setElementsZone(int dataZone[][], JButton coords[][], JFrame zonePopup) {
		cont = 0;

		for (int i = 0; i < dataZone.length; i++) {
			for (int j = 0; j < dataZone[i].length; j++) {
				if (dataZone[i][j] == currentZone) {
					zonePopup.add(coords[i][j]);
					cont++;
				}
			}
		}
	}

	public static void setCurrentZone(JPanel menu, Color c) {
		boolean find = false;

		for (int i = menuObj; i < menu.getComponentCount() && !find; i++) {
			JButton button = (JButton) menu.getComponent(i);

			if (button.getBackground() == c) {
				currentZone = i - menuObj;
				randomColor = c;
				find = true;
			}
		}
	}

	public static void setData(int data[][]) {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				data[i][j] = -1;
		}
	}

	public static void clearElementsZone(JFrame zonePopup) {
		for (int i = zonePopupObj; i < zonePopup.getContentPane().getComponentCount();) {
			zonePopup.remove(zonePopup.getContentPane().getComponent(i));
		}
	}

	public static void deleteElementsZone(JFrame zonePopup, JTextField grid[][], JButton coords[][], int dataZone[][]) {
		for (int i = zonePopupObj; i < zonePopup.getContentPane().getComponentCount();) {
			JButton button = (JButton) zonePopup.getContentPane().getComponent(i);
			String coordText = button.getText();

			int x = Integer.parseInt(coordText.substring(coordText.indexOf('(') + 1, coordText.indexOf(',')));
			int y = Integer.parseInt(coordText.substring(coordText.indexOf(',') + 1, coordText.indexOf(')')));

			clearZoneCoord(x, y, grid);

			coords[x][y] = null;
			dataZone[x][y] = -1;
			zonePopup.remove(zonePopup.getContentPane().getComponent(i));
		}
	}

	public static void updateZonesData(int dataZone[][]) {
		for (int x = 0; x < dataZone.length; x++) {
			for (int y = 0; y < dataZone[x].length; y++) {
				if (dataZone[x][y] > currentZone)
					dataZone[x][y]--;
			}
		}
	}

	public void ordZones(JPanel menu) {
		for (int i = menuObj; i < menu.getComponentCount(); i++) {
			menu.getComponent(i).setBounds(gridSize + offset,
					((margin + sizeBtn) * menuObj) + ((10 * (i - menuObj + 1)) + ((i - menuObj) * sizeZonebtn)),
					gridSize / 2 - offset + margin, sizeZonebtn);
		}
	}

	public static void createOperacion(int x, int y, JTextField grid[][], ArrayList<JLabel> operations,
			ArrayList<Integer> dataOperacion) {

		JLabel operationField = new JLabel();

		operationField.setText(defaultOperation);
		operationField.setBackground(randomColor);

		operationField.setSize(operationField.getPreferredSize());

		if (operationField.getWidth() > grid[x][y].getWidth()) {
			int fontSize = (int) (grid[x][y].getWidth() * (double) (grid[x][y].getWidth() / operationField.getWidth()));
			operationField.setFont(new Font(operationField.getName(), Font.PLAIN, fontSize));
			operationField.setSize(operationField.getPreferredSize());
		}

		operationField.setLocation(grid[x][y].getWidth() - operationField.getWidth() - 2, 2);

		if (!edition) {
			operations.add(operationField);
			dataOperacion.add(x);
			dataOperacion.add(y);

			grid[x][y].add(operations.get(operations.size() - 1));
		} else {
			operations.set(currentZone, operationField);
			dataOperacion.set(currentZone * 2, x);
			dataOperacion.set(currentZone * 2 + 1, y);

			grid[x][y].add(operations.get(currentZone));
		}

	}

	public static void ordOperacion(ArrayList<JButton> zones, int dataZone[][], JTextField grid[][],
			ArrayList<JLabel> operations, ArrayList<Integer> dataOperacion) {
		if (cont > 0) {

			int zone, maxX, maxY;

			if (edition)
				zone = currentZone;
			else
				zone = zones.size();

			maxX = dataZone.length;
			maxY = -1;

			for (int x = 0; x < dataZone.length; x++) {
				for (int y = 0; y < dataZone[x].length; y++) {
					if (dataZone[x][y] == zone) {
						if (x <= maxX && y >= maxY) {
							maxX = x;
							maxY = y;
						}
					}
				}
			}

			operations.get(zone).setLocation(grid[maxX][maxY].getWidth() - operations.get(zone).getWidth() - 2, 2);
			dataOperacion.set(zone, maxX);
			dataOperacion.set(zone + 1, maxY);

			grid[maxX][maxY].add(operations.get(zone));
		}
	}

	public static void setOperation(JTextField grid[][], int dataZone[][], String operation, ArrayList<JButton> zones,
			ArrayList<JLabel> operations) {
		int zone;

		if (edition)
			zone = currentZone;
		else
			zone = zones.size();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (dataZone[i][j] == zone && grid[i][j].getComponentCount() > 0) {
					operations.get(zone).setText(operation);

					JLabel opText = (JLabel) grid[i][j].getComponent(0);
					opText.setText(operation);

					grid[i][j].remove(0);
					grid[i][j].add(opText);

					break;
				}
			}
		}
	}

	public ArrayList<Character> parse2CharArray(ArrayList<JLabel> operations) {
		ArrayList<Character> iOp = new ArrayList<Character>();
		
		for(int i = 0; i < operations.size(); i++) {
			iOp.add(operations.get(i).getText().charAt(0));
		}
		
		return iOp;
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	public void createAndShowGUI(JFrame iFrame) {
		// Create and set up the window.
		JFrame frame = iFrame;
		JFrame zonePopup = new JFrame();

		// Create and set up the panels.
		JPanel grid = new JPanel();
		JPanel menu = new JPanel();

		// Create and set up the buttons.
		JButton createZonebtn = new JButton("Nueva Zona");
		JButton saveCustombtn = new JButton("Guardar");
		JButton returnCustombtn = new JButton("Volver");
		JButton setOperationbtn = new JButton("Modificar");
		JButton setZonebtn = new JButton("Guardar");
		JButton cancelZonebtn = new JButton();
		JButton[][] coords = new JButton[n][n];
		ArrayList<JButton> zones = new ArrayList<JButton>();

		// Create and set up the inputs.
		JTextField[][] gridField = new JTextField[n][n];
		JTextField operationField = new JTextField();

		// Create and set up the strings.
		JLabel coord = new JLabel();

		// Create and set up the auxiliary variables.
		Random rand = new Random();
		ArrayList<Integer> dataOperacion = new ArrayList<Integer>();

		// Set up the frames
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, (int) (gridSize * 1.5) + offset, gridSize + (int) (offset * 1.7));

		zonePopup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		zonePopup.getContentPane().setLayout(null);
		zonePopup.setBounds(0, 0, gridSize, gridSize);

		// Set up the menu.
		menu.setBounds(gridSize + offset, 0, 250, 500);
		menu.setLayout(null);

		// Set up the grid.
		setGrid(grid, gridField, coords, zones, dataZone, dataDefault, zonePopup, frame, operations, dataOperacion);

		// Set up the buttons menu.
		createZonebtn.setBounds(gridSize + offset, margin, gridSize / 2 - offset + margin, sizeBtn);
		createZonebtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Color random = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
				cont = 0;
				currentZone = zones.size();

				cancelZonebtn.setText("Cancelar");
				zonePopup.setTitle("NuevaZona");
				zonePopup.getContentPane().setBackground(random);
				zonePopup.setVisible(true);

				setColor(random);

				if (edition)
					clearElementsZone(zonePopup);
				else
					deleteElementsZone(zonePopup, gridField, coords, dataZone);

				edition = false;

				zonePopup.repaint();
			}
		});

		saveCustombtn.setBounds(gridSize + offset, margin * 2 + sizeBtn, gridSize / 2 - offset + margin, sizeBtn);
		saveCustombtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {				
				if(cv.resolverKenken(n, dataDefault, dataZone, dataDefault, parse2CharArray(operations))) cv.vistaGuardadoTablero();
				else JOptionPane.showMessageDialog(null,
						"Este tablero no tiene solucion.");
			}
		});

		returnCustombtn.setBounds(gridSize + offset, margin * 3 + sizeBtn * 2, gridSize / 2 - offset + margin, sizeBtn);
		returnCustombtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				cv.vistaOpcionesJuegos();
				frame.dispose();
			}
		});
		
		// Set up the buttons zone.
		setOperationbtn.setBounds(gridSize - 125, 10, 100, 25);
		setOperationbtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				String operation = operationField.getText();

				if (operation.equals("+") || operation.equals("-") || operation.equals("x") || operation.equals("/"))
					setOperation(gridField, dataZone, operation, zones, operations);
				else
					JOptionPane.showMessageDialog(null, "La operacion debe de ser una de las siguientes: +, -, x o /");

				frame.repaint();
			}
		});
		cancelZonebtn.setBounds(gridSize - 125, gridSize - 70, 100, 25);
		cancelZonebtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				zonePopup.setVisible(false);
				deleteElementsZone(zonePopup, gridField, coords, dataZone);

				if (edition) {

					if (cont > 0) {
						dataOperacion.remove(currentZone * 2);
						dataOperacion.remove(currentZone * 2);
						operations.remove(currentZone);
					}

					menu.remove(zones.get(currentZone));
					zones.remove(currentZone);

					updateZonesData(dataZone);
					ordZones(menu);

					frame.repaint();
				} else if (!edition && cont > 0) {
					dataOperacion.remove((operations.size() - 1) * 2);
					dataOperacion.remove((operations.size() - 1) * 2);
					operations.remove(operations.size() - 1);
				}
			}
		});
		setZonebtn.setBounds(gridSize - 235, gridSize - 70, 100, 25);
		setZonebtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				zonePopup.setVisible(false);
				clearElementsZone(zonePopup);

				if (!edition) {
					JButton zone = new JButton("Zona");
					zone.setBackground(randomColor);
					zone.setBounds(gridSize + offset,
							((margin + sizeBtn) * menuObj) + ((10 * (zones.size() + 1)) + (zones.size() * sizeZonebtn)),
							gridSize / 2 - offset + margin, sizeZonebtn);
					zone.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Color current = e.getComponent().getBackground();

							cancelZonebtn.setText("Eliminar");
							zonePopup.setTitle("EditarZona");
							zonePopup.getContentPane().setBackground(current);
							zonePopup.setVisible(true);

							if (!edition)
								deleteElementsZone(zonePopup, gridField, coords, dataZone);
							else
								clearElementsZone(zonePopup);
							edition = true;

							setCurrentZone(menu, current);
							setElementsZone(dataZone, coords, zonePopup);
							ordZoneCoords(zonePopup);

							zonePopup.repaint();
						}
					});
					zones.add(zone);

					menu.add(zones.get(zones.size() - 1));
					frame.repaint();
				}
			}
		});

		// Set up the inputs.
		coord.setText("Operación:");
		coord.setLocation(10, 10);
		coord.setSize(coord.getPreferredSize());

		operationField.setBounds(95, 10, 25, 25);

		// Add the buttons.
		menu.add(createZonebtn);
		menu.add(saveCustombtn);
		menu.add(returnCustombtn);

		zonePopup.add(setOperationbtn);
		zonePopup.add(cancelZonebtn);
		zonePopup.add(setZonebtn);

		// Add the inputs.
		zonePopup.add(coord);

		zonePopup.add(operationField);

		// Add the panels.
		frame.add(grid);
		frame.add(menu);

		// Display the window.
		frame.setVisible(true);
	}

	public CustomGame(ControladoraVista cv, int n) {
		super("TaulellKenken");
		this.cv = cv;
		this.n = n;
		this.gridSize = getGridSize(n);
		this.maxNumCoords = getMaxNumCoords(gridSize, sizeCoord);
		this.operations = new ArrayList<JLabel>();
		this.dataZone = new int[this.n][this.n];
		this.dataDefault = new int[this.n][this.n];

		setData(this.dataZone);

		createAndShowGUI(this);
	}
}