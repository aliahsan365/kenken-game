/*
 * Toni Palacios
 */

package domini;

import java.util.ArrayList;
import domini.CasillaKenken;

public class TaulellKenken extends Taulell {
	
	/* Atributos */
	private int id_usuario;
	private int id_tablero;
	private String KenkenName;
	private CasillaKenken MatrixKenken[][];						//Matriz de CasillaKenken que conforman las celdas del tablero
	private ArrayList<Zona> zonas;								//ArrayList de Zona que especifican los tipos de regiones del tablero
	private int tamany;											//Entero con el tamaño del tablero (tamany*tamany)
	private int dificultad;
	private static char operaciones[] = {'+','-','x','/'};		//Array estatico con los tipos de operaciones de las zonas del tablero
	
	// Generadores //
	
		// Private recurses //
	
			//TODO Especificación
			private int calculateResultZone(int value, int zone, int imat[][], int izone[][], ArrayList<Character> iop) {
				int result;
				
				if(iop.get(zone) == 'x') result = 1;
				else result = 0;
				
				for(int i = 0; i < imat.length; i++) {
					for(int j = 0; j < imat[i].length; j++) {
						
						if(izone[i][j]==zone && imat[i][j]>0) {
							switch (iop.get(zone)) {
								case '+':
									result += imat[i][j];
									break;
								case 'x':
									result *= imat[i][j];
									break;
								default:
									result = imat[i][j];
							}
						}
						
					}
				}
				
				return result;
			}
			
			//TODO Especificación
			private int calculateSizeZone(int zone, int izone[][], int imat[][]) {
				int cont = 0;
				
				for(int i = 0; i < izone.length; i++) {
					for(int j = 0; j < izone[i].length; j++) {
						if(izone[i][j]==zone && imat[i][j]==0) cont++;
					}
				}
				
				return cont;
			}
	
			private boolean allValidValues(int imat[][]) {
				boolean rmat[][] = new boolean[imat.length][imat.length];
				boolean cmat[][] = new boolean[imat.length][imat.length];
				
				for(int i = 0; i < imat.length; i++) {
					for(int j = 0; j < imat[i].length; j++) {
						if(imat[i][j]>0) {
							if(validValue(imat[i][j]-1, i, j, rmat, cmat)) {
								rmat[i][imat[i][j]-1] = true;
								cmat[j][imat[i][j]-1] = true;
							}
							else return false;
						}
					}
				}
				
				return true;
			}
			
			/*
			 * private boolean validValue(int value, int x, int y, boolean rmat[][], boolean cmat[][])
			 * 		Especificacion: Devuelve true si no existe value en la matriz boleana de filas rmat con fila x
			 * 			y si no existe value en la matriz boleana de columnas cmat con columna y
			 */
			private boolean validValue(int value, int x, int y, boolean rmat[][], boolean cmat[][]) {
				return !(rmat[x][value-1] || cmat[y][value-1]);
			}
			
			private boolean validValue(int value, int x, int y, int imat[][], int izone[][], ArrayList<Character> iop, ArrayList<Long> ires) {
				
				int result = calculateResultZone(value, izone[x][y], imat, izone, iop);
				int sizeZone = calculateSizeZone(izone[x][y], izone, imat);
				
				if(sizeZone>1) {
					switch(iop.get(izone[x][y])) {
						case '+':
							if((result+value)<ires.get(izone[x][y])) return true;
							break;
						case 'x':
							if((result*value)<=ires.get(izone[x][y])) return true;
							break;
						default:
							return true;
					}
				}
				else if(sizeZone == 1) {
					switch(iop.get(izone[x][y])) {
						case '+':
							if((result+value)==ires.get(izone[x][y])) return true;
							break;
						case 'x':
							if((result*value)==ires.get(izone[x][y])) return true;
							break;
						case '-':
							if(result>value) {
								if((result-value)==ires.get(izone[x][y])) return true;
							}
							else {
								if((value-result)==ires.get(izone[x][y])) return true;
							}
							break;
						case '/':
							if(result>value) {
								if((result/value)==ires.get(izone[x][y])) return true;
							}
							else {
								if((value/result)==ires.get(izone[x][y])) return true;
							}
							break;
					}
				}
					
				return false;
			}
			
			/*
			 * private boolean freeCoordenate(int imat[][], int x, int y, int value)
			 * 		Especificacion: Devuelve true si no hay ningun valor en la posicion (x,y) de la matriz imat
			 */
			private boolean freeCoordenate(int imat[][], int x, int y) {
				return (imat[x][y]==0);
			}
			
			/*
			 * private void setResultValues(int imat[][])
			 * 		Especificacion: copia la matriz imat de enteros en los resultados de la matriz de CasillaKenken MatrixKenken
			 */
			private void setResultValues(int imat[][]) {
				for(int i = 0; i < imat.length; i++) {
					for(int j = 0; j < imat[i].length; j++) {
						this.MatrixKenken[i][j].setResultado(imat[i][j]);
					}
				}
			}
			
			/*
			 * private int getNumCandidates(int x, int y, boolean rmat[][], boolean cmat[][])
			 * 		Especificación: indica el número total de valores validos entre [1,rmat.length] valores 
			 * 		que pueden ser utilizados en en la posicion (x,y)
			 */
			private int getNumCandidates(int x, int y, boolean rmat[][], boolean cmat[][]) {
				int candidates = 0;
				
				for(int value = 1; value <= rmat.length; value++) if(validValue(value, x, y, rmat, cmat)) candidates++;
				
				return candidates;
			}
			
			/*
			 * private ArrayList<Pair> getCandidates(int imat[][], boolean rmat[][], boolean cmat[][])
			 * 		Especificación: devuelve un ArrayList<Pair> de posiciones con el mínimo número de posibles valores para esa posición
			 */
			private ArrayList<Pair> getCandidates(int imat[][], boolean rmat[][], boolean cmat[][]) {
				ArrayList<Pair> candidates = new ArrayList<Pair>();
				int minCandidates = imat.length;
				int iCandidates;
				
				for(int x = 0; x < imat.length; x++) {
					for(int y = 0; y < imat[x].length; y++) {
						if(freeCoordenate(imat,x,y)) {
						
							iCandidates = getNumCandidates(x,y,rmat,cmat);
							
							if(minCandidates>iCandidates) {
								candidates = new ArrayList<Pair>();
								minCandidates = iCandidates;
							}
							
							if(iCandidates == minCandidates) candidates.add(new Pair(x,y));
							
						}
					}
				}
				
				return candidates;
			}
			
			/*
			 * private ArrayList<Integer> getCandidates(Pair coordenate, boolean rmat[][], boolean cmat[][])
			 * 		Especificación: devuelve un ArrayList<Integer> con los valores validos para la posición Pair coordenate.
			 */
			private ArrayList<Integer> getCandidates(Pair coordenate, boolean rmat[][], boolean cmat[][]) {
				ArrayList<Integer> candidates = new ArrayList<Integer>();
				
				for(int value = 1; value <= rmat.length; value++) if(validValue(value, coordenate.getFila(), coordenate.getColumna(), rmat, cmat)) candidates.add(value);
				
				return candidates;
			}
			
			/*
			 * private ArrayList<ArrayList<Integer>> getValues(ArrayList<Pair> candidates, boolean rmat[][], boolean cmat[][])
			 * 		Especificación: devuelve una matriz de ArrayList<Integer> con todos los valores validos para las posiciones ArrayList<Pair> candidates.
			 */
			private ArrayList<ArrayList<Integer>> getValues(ArrayList<Pair> candidates, boolean rmat[][], boolean cmat[][]) {
				
				ArrayList<ArrayList<Integer>> values = new ArrayList<ArrayList<Integer>>();
				
				for(int i = 0; i < candidates.size(); i++) {
					values.add(getCandidates(candidates.get(i), rmat, cmat));
				}
				
				return values;
			}
		
		// Private Core //
			
			/*
			 * private boolean _createSudoku(int rfind, int imat[][], boolean rmat[][], boolean cmat[][])
			 * 		Especificacion: trata de generar y resolver un sudoku de tamaño tamany*tamany a partir de una estructura 
			 * 		definida en la matriz de enteros imat de forma aleatoria.
			 * 			int rfind -> El entero rfind define el numero de casillas que quedan por resolver.
			 * 			int imat[][] -> La matriz de enteros imat indica los resultados en (*).
			 * 			boolean rmat[][]/cmat[][] -> las matrices de boleanos rmat y cmat indican los valores,
			 * 			segun las rows y las cols respectivamente, que ya se han utilizado en (*).
			 * 
			 * 			(*) la situacion actual del sudoku que cambia en cada llamada recursiva
			 * 			
			 */
			private boolean _createSudoku(int rfind, int imat[][], boolean rmat[][], boolean cmat[][]) {
					
				if(rfind==0) {
					setResultValues(imat);
					return true;
				}
				
				Utilities u = new Utilities();
				
				int indexC, indexV, value;
				int c_imat[][];
				
				boolean c_rmat[][], c_cmat[][];
				
				Pair coordenates;
				
				ArrayList<Pair> candidates = getCandidates(imat, rmat, cmat); //Filtering the candidates to the next position
				ArrayList<ArrayList<Integer>> values = getValues(candidates, rmat, cmat);
				
				while(candidates.size()>0) {
					
					indexC = u.newRandom(candidates.size());
					coordenates = candidates.get(indexC);
					
					while(values.get(indexC).size()>0) {
						
						indexV = u.newRandom(values.get(indexC).size());
						value = values.get(indexC).get(indexV);
						
						c_imat = new int[tamany][tamany];	//Initial State's copy
						c_rmat = new boolean[tamany][tamany];
						c_cmat = new boolean[tamany][tamany];
						for(int i = 0; i < tamany; i++) for(int j = 0; j < tamany; j++) {
							c_imat[i][j] = imat[i][j];
							
							c_rmat[i][j] = rmat[i][j];
							c_cmat[i][j] = cmat[i][j];
						}
						
						c_rmat[coordenates.getFila()][value-1] = c_cmat[coordenates.getColumna()][value-1] = true;	//setBoolValues
						c_imat[coordenates.getFila()][coordenates.getColumna()] = value;							//setValue
						
						if(_createSudoku((rfind-1), c_imat, c_rmat, c_cmat)) return true;
						
						values.get(indexC).remove(indexV);
					}
					
					candidates.remove(indexC);
					
				}
					
				return false;
			}
			
			private ArrayList<ArrayList<Integer>> filterByZoneValues(ArrayList<Pair> candidates, ArrayList<ArrayList<Integer>> values, int imat[][], int izone[][], ArrayList<Character> iop, ArrayList<Long> ires) {
				int i,j;
				i = 0;
				
				while(i < values.size()) {
					j = 0;
					
					while(j < values.get(i).size()) {
						
						if(!validValue(values.get(i).get(j), candidates.get(i).getFila(), candidates.get(i).getColumna(), imat, izone, iop, ires)) {
							values.get(i).remove(j);
							j--;
						}
						
						j++;
					}
					
					if(values.get(i).size()<1) {
						values.remove(i);
						candidates.remove(i);
						i--;
					}
					
					i++;
				}
				
				return values;
			}
			
			private ArrayList<Pair> filterByZoneCandidates(ArrayList<Pair> candidates, ArrayList<ArrayList<Integer>> values, int imat[][], int izone[][], ArrayList<Character> iop, ArrayList<Long> ires) {
				int i,j;
				i = 0;
				
				while(i < values.size()) {
					j = 0;
					
					while(j < values.get(i).size()) {
						
						if(!validValue(values.get(i).get(j), candidates.get(i).getFila(), candidates.get(i).getColumna(), imat, izone, iop, ires)) {
							values.get(i).remove(j);
							j--;
						}
						
						j++;
					}
					
					if(values.get(i).size()<1) {
						values.remove(i);
						candidates.remove(i);
						i--;
					}
					
					i++;
				}
				
				return candidates;
			}
			
			private ArrayList<ArrayList<Integer>> filterByResultValues(ArrayList<Pair> candidates, ArrayList<ArrayList<Integer>> values) {
				ArrayList<Pair> candidatesFiltered = new ArrayList<Pair>();
				ArrayList<ArrayList<Integer>> valuesFiltered = new ArrayList<ArrayList<Integer>>();
				
				for(int i = 0; i < values.size(); i++) {
					int resultado = this.MatrixKenken[candidates.get(i).getFila()][candidates.get(i).getColumna()].getResultado();
					
					for(int j = 0; j < values.get(i).size(); j++) {
						if(values.get(i).get(j) == resultado) {
							candidatesFiltered.add(candidates.get(i));
							valuesFiltered.add(new ArrayList<Integer>());
							valuesFiltered.get(valuesFiltered.size()-1).add(resultado);
							break;
						}
					}
				}
				
				return valuesFiltered;
			}
			
			private ArrayList<Pair> filterByResultCandidates(ArrayList<Pair> candidates, ArrayList<ArrayList<Integer>> values) {
				ArrayList<Pair> candidatesFiltered = new ArrayList<Pair>();
				ArrayList<ArrayList<Integer>> valuesFiltered = new ArrayList<ArrayList<Integer>>();
				
				for(int i = 0; i < values.size(); i++) {
					int resultado = this.MatrixKenken[candidates.get(i).getFila()][candidates.get(i).getColumna()].getResultado();
					
					for(int j = 0; j < values.get(i).size(); j++) {
						if(values.get(i).get(j) == resultado) {
							candidatesFiltered.add(candidates.get(i));
							valuesFiltered.add(new ArrayList<Integer>());
							valuesFiltered.get(valuesFiltered.size()-1).add(resultado);
							break;
						}
					}
				}
				
				return candidatesFiltered;
			}
			
			//TODO: Especificación
			private boolean _resolveKenken(int rfind, int imat[][], boolean rmat[][], boolean cmat[][], int izone[][], ArrayList<Character> iop, ArrayList<Long> ires) {
				if(rfind==0) {
					setResultValues(imat);
					return true;
				}
				
				Utilities u = new Utilities();
				
				int indexC, indexV, value;
				int c_imat[][];
				
				boolean c_rmat[][], c_cmat[][];
				
				Pair coordenates;
				
				ArrayList<Pair> candidatesByZone = getCandidates(imat, rmat, cmat); //Filtering the candidates to the next position
				ArrayList<ArrayList<Integer>> valuesByZone = getValues(candidatesByZone, rmat, cmat);
				
				//ArrayList<Pair> candidatesByZone = filterByZoneCandidates(candidates, values, imat, izone, iop, ires);
				//ArrayList<ArrayList<Integer>> valuesByZone = filterByZoneValues(candidates, values, imat, izone, iop, ires);
				
				ArrayList<Pair> candidates = filterByResultCandidates(candidatesByZone, valuesByZone);
				ArrayList<ArrayList<Integer>> values = filterByResultValues(candidatesByZone, valuesByZone);
				
				candidatesByZone = null;
				valuesByZone = null;
				
				while(candidates.size()>0) {
					
					indexC = u.newRandom(candidates.size());
					coordenates = candidates.get(indexC);
					
					while(values.get(indexC).size()>0) {
						
						indexV = u.newRandom(values.get(indexC).size());
						value = values.get(indexC).get(indexV);
						
						c_imat = new int[tamany][tamany];	//Initial State's copy
						c_rmat = new boolean[tamany][tamany];
						c_cmat = new boolean[tamany][tamany];
						for(int i = 0; i < tamany; i++) for(int j = 0; j < tamany; j++) {
							c_imat[i][j] = imat[i][j];
							
							c_rmat[i][j] = rmat[i][j];
							c_cmat[i][j] = cmat[i][j];
						}
						
						c_rmat[coordenates.getFila()][value-1] = c_cmat[coordenates.getColumna()][value-1] = true;	//setBoolValues
						c_imat[coordenates.getFila()][coordenates.getColumna()] = value;							//setValue
						
						if(_resolveKenken((rfind-1), c_imat, c_rmat, c_cmat, izone, iop, ires)) return true;
						
						values.get(indexC).remove(indexV);
					}
					
					candidates.remove(indexC);
					
				}
					
				return false;
			}
			
			//TESTING
			public boolean resolveKenken() {
				int imat[][] = this.getiDef();
				int izone[][] = this.getiZone();
				ArrayList<Long> ires = this.getiRes();
				ArrayList<Character> iop = this.getiOp();
				int rfind = calculateRfind(imat);
				boolean rmat[][] = calculateRows(imat);
				boolean cmat[][] = calculateColumns(imat);
				
				if(!allValidValues(imat) || !_resolveKenken(rfind, imat, rmat, cmat, izone, iop, ires)) return false;
				
				return true;
			}
			
			//TODO Especificación
			public boolean resolveKenken(int iMat[][]) {
				int imat[][] = iMat;
				int izone[][] = this.getiZone();
				ArrayList<Long> ires = this.getiRes();
				ArrayList<Character> iop = this.getiOp();
				int rfind = calculateRfind(imat);
				boolean rmat[][] = calculateRows(imat);
				boolean cmat[][] = calculateColumns(imat);
				
				Utilities u = new Utilities();
				
				if(!u.allTrue(imat) || !allValidValues(imat) || !_resolveKenken(rfind, imat, rmat, cmat, izone, iop, ires)) return false;
				
				return true;
			}
			
			//TODO Especifiación
			public Pair sugerenciaKenken(int iMat[][]) {
				int imat[][] = iMat;
				int izone[][] = this.getiZone();
				ArrayList<Long> ires = this.getiRes();
				ArrayList<Character> iop = this.getiOp();
				int rfind = calculateRfind(iMat);
				boolean rmat[][] = calculateRows(iMat);
				boolean cmat[][] = calculateColumns(iMat);
				
				Utilities u = new Utilities();
				Pair p = new Pair();
				
				if(allValidValues(imat) && _resolveKenken(rfind, imat, rmat, cmat, izone, iop, ires)) {
					ArrayList<Pair> candidatesByZone = getCandidates(imat, rmat, cmat); //Filtering the candidates to the next position
					ArrayList<ArrayList<Integer>> valuesByZone = getValues(candidatesByZone, rmat, cmat);
					
					//ArrayList<Pair> candidatesByZone = filterByZoneCandidates(candidates, values, imat, izone, iop, ires);
					//ArrayList<ArrayList<Integer>> valuesByZone = filterByZoneValues(candidates, values, imat, izone, iop, ires);
					
					ArrayList<Pair> candidates = filterByResultCandidates(candidatesByZone, valuesByZone);
					
					candidatesByZone = null;
					valuesByZone = null;
					
					p = candidates.get(u.newRandom(candidates.size()));
				}
				
				return p;
			}
			
			/*
			 * private int calculateRfind(int def[][])
			 * 		Especificacion: dependiendo la estructura inicial del tablero, es decir de la matriz de entero def
			 * 		calcula los valores que restan por encontrar.
			 */
			private int calculateRfind(int def[][]) {
				int rfind = 0;
				
				for(int x = 0; x < def.length; x++) {
					for(int y = 0; y < def[x].length; y++) {
						if(def[x][y]==0) rfind++;
					}
				}
				
				return rfind;
			}
			
			/*
			 * private boolean[][] calculateRows(int def[][])
			 * 		Especificacion: dependiendo la estructura inicial del tablero, es decir de la matriz de entero def
			 * 		calcula los valores ocupados según las filas.
			 */
			private boolean[][] calculateRows(int def[][]) {
				boolean rmat[][] = new boolean[def.length][def.length];
				
				for(int x = 0; x < def.length; x++) {
					for(int y = 0; y < def[x].length; y++) {
						if(def[x][y]>0) rmat[x][def[x][y]-1] = true;
					}
				}
				
				return rmat;
			}
			
			/*
			 * private boolean[][] calculateColumns(int def[][])
			 * 		Especificacion: dependiendo la estructura inicial del tablero, es decir de la matriz de entero def
			 * 		calcula los valores ocupados según las columnas.
			 */
			private boolean[][] calculateColumns(int def[][]) {
				boolean cmat[][] = new boolean[def.length][def.length];
				
				for(int x = 0; x < def.length; x++) {
					for(int y = 0; y < def[x].length; y++) {
						if(def[x][y]>0) cmat[y][def[x][y]-1] = true;
					}
				}
				
				return cmat;
			}
			
			/*
			 * public void createSudoku(int def[][])
			 * 		Especificacion: genera y resuelve un sudoku que viene definido por la matriz de enteros def[][] y a través de la
			 * 		llamada recursiva _createSudoku(...)
			 */	
			public void createSudoku() {				
				int imat[][] = new int[tamany][tamany];
				int rfind = tamany*tamany;
				boolean rmat[][] = new boolean[tamany][tamany];
				boolean cmat[][] = new boolean[tamany][tamany];
				
				if(!_createSudoku(rfind, imat, rmat, cmat)) System.out.println("ALERT: This Sudoku have not got solution.");
			}
			
			/*
			 * private void createSudoku(int def[][])
			 * 		Especificacion: genera y resuelve un sudoku que viene definido por la matriz de enteros def[][] y a través de la
			 * 		llamada recursiva _createSudoku(...)
			 */	
			private void createSudoku(int iMat[][]) {				
				int imat[][] = iMat;
				int rfind = calculateRfind(imat);
				boolean rmat[][] = calculateRows(imat);
				boolean cmat[][] = calculateColumns(imat);
				
				if(!allValidValues(imat) || !_createSudoku(rfind, imat, rmat, cmat)) this.tamany = -1;
			}
			
			/*
			 * private void setDefaults()
			 * 		Especificacion: recorre la matriz de CasillaKenken MatrixKenken comprabando que todas las casillas tengan una Zona asiganada
			 * 			en caso contrario crea una zona nueva y las asigna como casillas por defecto, las cuales un usuario no puede modificar
			 * 			y siempre muestran su valor al iniciarse la partida
			 */
			private void setDefaults() {
				for(int i = 0; i < tamany; i++) {
					for(int j = 0; j < tamany; j++) {
						if(MatrixKenken[i][j].getIndexZona()==-1) {
							Zona z = new Zona(operaciones[0],MatrixKenken[i][j].getResultado());
							MatrixKenken[i][j].setIndexZona(zonas.size());
							MatrixKenken[i][j].setEditable(false);
							zonas.add(z);
						}
					}
				}
			}
			
			private void setDefaultOperations() {
				operaciones = new char[4];
				operaciones[0] = '+';
				operaciones[1] = '-';
				operaciones[2] = 'x';
				operaciones[3] = '/';
			}
			
			private void setOptions(int options) {
				/* Options */
				/*
				 *	0 >> AllRandom
				 *
				 *	1 >> AllSuma
				 *	2 >> AllResta
				 *	3 >> AllDiv
				 *	4 >> AllMul
				 *
				 *	5 >> Suma&Resta
				 *	6 >> Suma&Div
				 *	7 >> Suma&Mul
				 *
				 *	8 >> Resta&Div
				 *	9 >> Resta&Mul
				 *
				 *	10 >> Div&Mul
				 *
				 *	11 >> Suma&Resta&Div
				 *	12 >> Suma&&Resta&Mul
				 *	13 >> Resta&Div&Mul
				 *
				 *******************************
				 *
				 *	operaciones[] = {'+','-','x','/'};
				 */
				
				if(options>0) {
					switch(options) {
						case 1:
							operaciones = new char[1];
							operaciones[0] = '+';
							break;
						case 2:
							operaciones = new char[1];
							operaciones[0] = '-';
							break;
						case 3:
							operaciones = new char[1];
							operaciones[0] = '/';
							break;
						case 4:
							operaciones = new char[1];
							operaciones[0] = 'x';
							break;
						case 5:
							operaciones = new char[2];
							operaciones[0] = '+';
							operaciones[1] = '-';
							break;
						case 6:
							operaciones = new char[2];
							operaciones[0] = '+';
							operaciones[1] = '/';
							break;
						case 7:
							operaciones = new char[2];
							operaciones[0] = '+';
							operaciones[1] = 'x';
							break;
						case 8:
							operaciones = new char[2];
							operaciones[0] = '-';
							operaciones[1] = '/';
							break;
						case 9:
							operaciones = new char[2];
							operaciones[0] = '-';
							operaciones[1] = 'x';
							break;
						case 10:
							operaciones = new char[2];
							operaciones[0] = '/';
							operaciones[1] = 'x';
							break;
						case 11:
							operaciones = new char[3];
							operaciones[0] = '+';
							operaciones[1] = '-';
							operaciones[2] = '/';
							break;
						case 12:
							operaciones = new char[3];
							operaciones[0] = '+';
							operaciones[1] = '-';
							operaciones[2] = 'x';
							break;
						case 13:
							operaciones = new char[3];
							operaciones[0] = '-';
							operaciones[1] = '/';
							operaciones[2] = 'x';
							break;
					}
				}
			}
			
			/*
			 * private void createZonas()
			 * 		Especificacion: crea zonas completamente random, y mientras haya una region libre, es decir dos casillas
			 * 			sin zona asignada consecutivas, define estas zonas a través de defineZona("...") sino a través de setDefaults()
			 * 			y las va guardando en el ArrayList<Zona> zonas
			 */
			private void createZonas(int options) {
				Utilities u = new Utilities();
				
				setOptions(options);
				
				Zona z = new Zona(operaciones[u.newRandom(operaciones.length)],-1);
				Pair p = z.freeRegion(MatrixKenken);
				
				while(!p.empty()) {
					z = new Zona(operaciones[u.newRandom(operaciones.length)],-1);
					
					z.defineZona(MatrixKenken,p,zonas);
					zonas.add(z);
					p = z.freeRegion(MatrixKenken);
				}
				
				//Set default operations.
				setDefaultOperations();
				
				setDefaults();
			}
			
			private void createZonas(int iZone[][], int iDefault[][], ArrayList<Character> iOp) {
				int resultados[] = new int[iOp.size()];	
				
				for(int x = 0; x < iZone.length; x++) {
					for(int y = 0; y < iZone[x].length; y++) {
						
						if(resultados[iZone[x][y]] == 0) {
							resultados[iZone[x][y]] = this.MatrixKenken[x][y].getResultado();
						}
						else {
							switch(iOp.get(iZone[x][y])) {
								case '+':
									resultados[iZone[x][y]] += this.MatrixKenken[x][y].getResultado();
									break;
								case '-':
									if(resultados[iZone[x][y]]<this.MatrixKenken[x][y].getResultado()) {
										resultados[iZone[x][y]] = this.MatrixKenken[x][y].getResultado()-resultados[iZone[x][y]];
									}
									else {
										resultados[iZone[x][y]] -= this.MatrixKenken[x][y].getResultado();
									}
									break;
								case 'x':
									resultados[iZone[x][y]] *= this.MatrixKenken[x][y].getResultado();
									break;
								case '/':
									if(resultados[iZone[x][y]]<this.MatrixKenken[x][y].getResultado()) {
										resultados[iZone[x][y]] = this.MatrixKenken[x][y].getResultado()/resultados[iZone[x][y]];
									}
									else {
										resultados[iZone[x][y]] /= this.MatrixKenken[x][y].getResultado();
									}
									break;
							}
						}
						
						if(iDefault[x][y]>0) this.MatrixKenken[x][y].setEditable(false);
						else this.MatrixKenken[x][y].setEditable(true);
							
						this.MatrixKenken[x][y].setIndexZona(iZone[x][y]);
					}
				}
				
				for(int i = 0; i < iOp.size(); i++) {
					this.zonas.add(new Zona(iOp.get(i), resultados[i]));
				}
			}
			
			/*
			 * private void createKenken()
			 * 		Especificacion: crea un tablero Kenken completamente random de tamany*tamany
			 */
			private void createKenken(int options) {
				createSudoku();
				createZonas(options);
				
				if(this.dificultad == 0) this.dificultad = calcularDificultad();
			}
			
			/*
			 * private void createKenken()
			 * 		Especificacion: crea un tablero Kenken completamente random de tamany*tamany
			 */
			private void createKenken(int imat[][], int izone[][], int idefault[][], ArrayList<Character> iop) {
				createSudoku(imat);
				createZonas(izone, idefault, iop);
				this.dificultad = calcularDificultad();
			}
			
			// PRINT OUT //
				
				/*
				 * private void printZonas()
				 * 		Especificacion: muestra por consola el ArrayList<Zona> zonas
				 */
				private void printZonas() {
					System.out.println("Zonas->");
					
					for(int z = 0; z < zonas.size(); z++) System.out.println("(Zona: "+z+", Operacion: "+zonas.get(z).getOperacion()+", Resultado: "+zonas.get(z).getResultado());
					
					System.out.println();
				}
				
				/*
				 * private void printSudoku()
				 * 		Especificacion: muestra por consola los resultados de la matriz CasillaKenken MatrixKenken
				 */
				private void printSudoku() {
					
					System.out.println("Sudoku->");
					
					for(int i = 0; i < MatrixKenken.length; i++) {
						System.out.print("|");
						for(int j = 0; j < MatrixKenken[i].length; j++) {
							System.out.print(MatrixKenken[i][j].getResultado()+"|");
						}
						System.out.println();
					}
				}
				
				private void printiZone() {
					
					System.out.println("iZone->");
					
					for(int i = 0; i < MatrixKenken.length; i++) {
						System.out.print("|");
						for(int j = 0; j < MatrixKenken[i].length; j++) {
							System.out.print(MatrixKenken[i][j].getIndexZona()+"|");
						}
						System.out.println();
					}
				}
				/*----------------------------------------------------------------------------------------------------------------------------------*/		
	// Constructoras //
	
		/*
		 * private CasillaKenken[][] initCasillaKenkenMatrix()
		 * 		Especificacion: Crea y devuelve una matriz CasillaKenken vacia, con los valores por defecto
		 */
		private CasillaKenken[][] initCasillaKenkenMatrix() {
			CasillaKenken[][] ck = new CasillaKenken[tamany][tamany];
			
			for(int i = 0; i < tamany; i++) {
				for(int j = 0; j < tamany; j++) {
					ck[i][j] = new CasillaKenken();
				}
			}
			
			return ck;
		}
		
		private int calcularDificultad() {
			float pointsTotal = 0.0f;
			float pointsCasilla = 20.0f;
			float ratioSuma = 1.0f;
			float ratioResta = 1.5f;
			float ratioDiv = 1.75f;
			float ratioMul = 2.0f;
			
			for(int i = 0; i < this.MatrixKenken.length; i++) {
				for(int j = 0; j < this.MatrixKenken[i].length; j++) {
					switch(this.zonas.get(this.MatrixKenken[i][j].getIndexZona()).getOperacion()) {
						case '+':
							pointsTotal += (pointsCasilla*ratioSuma);
							break;
						case '-':
							pointsTotal += (pointsCasilla*ratioResta);
							break;
						case '/':
							pointsTotal += (pointsCasilla*ratioDiv);
							break;
						case 'x':
							pointsTotal += (pointsCasilla*ratioMul);
							break;
					}
				}
			}
			
			if(pointsTotal<=200) return 1;
			else if(pointsTotal<=300) return 2;
			else return 3;
		}
		
		private int asignarRangoDificultad() {
			Utilities u = new Utilities();
			int n = 1;
			
			switch(this.dificultad) {
				case 1:
					n += u.newRandom(5);
					break;
				case 2:
					while(n<6) {
						n = 1;
						n += u.newRandom(15);
					}
					break;
				case 3:
					while(n<16) {
						n = 1;
						n += u.newRandom(25);
					}
					break;
			}
			
			return n;
		}
		

		/* TODO: Especificación */
		/*
		 * public TaulellKenken(int tamany, boolean create)
		 * 		Especificacion: Si create es false crea un taulellKenken vacio con los valores por defecto
		 * 			y si create es true lo genera de forma automatica
		 */
		//public TaulellKenken(int tamany, boolean create, int def[][]) {
		public TaulellKenken(int tamany, int id_user, int id_kenken, boolean create, int dif, int options) {
			super(tamany);
			this.id_usuario = id_user;
			this.id_tablero = id_kenken;
			this.zonas = new ArrayList<Zona>();
			
			this.dificultad = dif;
			this.tamany = tamany;
			
			if(dif>0 && tamany==0) this.tamany = asignarRangoDificultad();
			
			this.MatrixKenken = initCasillaKenkenMatrix();
			
			if(create) createKenken(options);
		}
		
		/*
		 * public TaulellKenken(int tamany, Zona z, boolean create)
		 * 		Especificacion: Si create es false crea un taulellKenken vacio con los valores por defecto
		 * 			y si create es true lo genera de forma automatica y en ambos casos se añade la Zona z
		 */
		//public TaulellKenken(int tamany, Zona z, boolean create, int def[][]) {
		public TaulellKenken(int tamany, int id_user, int id_kenken, int imat[][], int izone[][], int idefault[][], ArrayList<Character> iop) {
			super(tamany);
			this.id_usuario = id_user;
			this.id_tablero = id_kenken;
			this.tamany = tamany;
			this.MatrixKenken = initCasillaKenkenMatrix();
			this.zonas = new ArrayList<Zona>();
			
			createKenken(imat, izone, idefault, iop);
		}
		
		public TaulellKenken() {
			super(0);
			this.id_usuario = -1;
			this.id_tablero = -1;
			this.tamany = 0;
			this.dificultad = 0;
			this.MatrixKenken = initCasillaKenkenMatrix();
			this.zonas = new ArrayList<Zona>();
		}
		/*----------------------------------------------------------------------------------------------------------------------------------*/	
		// Consultoras //
	
		/*
		 * public int[][] getiMat()
		 * 		Especificacion: inizializa una matriz de enteros imat con la copia de los valores de MatrixKenken, 
		 * 		y la devuelve.
		 */
		public int[][] getiMat() {
			int imat[][] = new int[tamany][tamany];
			
			for(int x = 0; x < tamany; x++) {
				for(int y = 0; y < tamany; y++) {
					imat[x][y] = this.MatrixKenken[x][y].getValor();
				}
			}
			
			return imat;
		}
		
		//TODO Especificación
		public int[][] getiDef() {
			int idef[][] = new int[tamany][tamany];
			
			for(int x = 0; x < tamany; x++) {
				for(int y = 0; y < tamany; y++) {
					if(!this.MatrixKenken[x][y].getEditable()) idef[x][y] = this.MatrixKenken[x][y].getResultado();
					else idef[x][y] = 0;
				}
			}
			
			return idef;
		}
		
		//TODO Especificación
		public int[][] getiZone() {
			int izone[][] = new int[tamany][tamany];
			
			for(int x = 0; x < tamany; x++) {
				for(int y = 0; y < tamany; y++) {
					izone[x][y] = this.MatrixKenken[x][y].getIndexZona();
				}
			}
			
			return izone;
		}
		
		//TODO Especificación
		public ArrayList<Character> getiOp() {
			ArrayList<Character> iOp = new ArrayList<Character>();
			
			for(int i = 0; i < this.zonas.size(); i++) {
				iOp.add(zonas.get(i).getOperacion());
			}
			
			return iOp;
		}
		
		public ArrayList<Long> getiRes() {
			ArrayList<Long> iRes = new ArrayList<Long>();
			
			for(int i = 0; i < this.zonas.size(); i++) {
				iRes.add(zonas.get(i).getResultado());
			}
			
			return iRes;
		}
		
		public int[][] getiSol() {
			int iSol[][] = new int[tamany][tamany];
			
			for(int i = 0; i < this.MatrixKenken.length; i++) {
				for(int j = 0; j < this.MatrixKenken[i].length; j++) {
					iSol[i][j] = this.MatrixKenken[i][j].getResultado();
				}
			}
			
			return iSol;
		}
		
		/*
		 * public Zona getZonaByIndex(int index)
		 * 		Especificacion: Devuelve la Zona ubicada en la posicion index del ArrayList<Zona> zonas
		 */
		public Zona getZonaByIndex(int index) {
			return this.zonas.get(index);
		}
		
		/*
		 * public int getNumZonas()
		 * 		Especificacion: Devuelve el numero de zonas que hay en el ArrayList<Zona> zonas
		 */
		public int getNumZonas() {
			return this.zonas.size();
		}
		
		/*
		 * public int getTamTab()
		 * 		Especificacion: Devuelve el entero tamany
		 */
		public int getSizeTab() {
			return this.tamany;
		}
		
		public int getDif() {
			return this.dificultad;
		}
		
		public int getUsuario(){
			return this.id_usuario; 
		}
		
		public int getIdTablero(){
			return this.id_tablero;
		}
		
		public String getName(){
			return this.KenkenName;
		}
		
		
		/*
		 * public CasillaKenken getCasillaK(int row, int col)
		 * 		Especificacion: Devuelve la CasillaKenken en la posicion (row,col) de la matriz CasillaKenken MatrixKenken
		 */
		public CasillaKenken getCasillaK(int row, int col) {
			return this.MatrixKenken[row][col];
		}
		
		public boolean getIfEditable(int i, int j){
			return this.MatrixKenken[i][j].getEditable();
		}
		
		public int getResultCasilla(Pair p) {
			return this.MatrixKenken[p.getFila()][p.getColumna()].getResultado();
		}
		
		public void copyTaulell(TaulellKenken t){
			this.id_usuario = t.id_usuario;
			this.id_tablero = t.id_tablero;
			this.tamany = t.tamany;
			this.dificultad = t.dificultad; 
			this.MatrixKenken = initCasillaKenkenMatrix();
			for(int i = 0; i < this.MatrixKenken.length; ++i){
				for(int j = 0; j < this.MatrixKenken.length; ++j){
					this.MatrixKenken[i][j].copyCasilla(t.MatrixKenken[i][j]);
				}
				
			}
			this.zonas = new ArrayList<Zona>();
			for(int i = 0; i < t.zonas.size(); ++i){
				this.zonas.add(t.zonas.get(i));
			}
		}
	
	/*----------------------------------------------------------------------------------------------------------------------------------*/	
	// Modificadoras //
		
		public void setIdTablero(int idK){
			this.id_tablero = idK;
		}
		
		public void setIdUser(int idU){
			this.id_usuario = idU;
		}
		
		public void setName(String name){
			this.KenkenName = name;
		}
		
		/*
		 * public void setCasillaKenken(CasillaKenken ck, int x, int y)
		 * 		Especificacion: Reemplaza la CasillaKenken en la posicion (x,y) de la matriz CasillaKenken MatrixKenken
		 * 		por ck
		 */
		public void setCasillaKenken(CasillaKenken ck, int x, int y) {
			this.MatrixKenken[x][y].copyCasilla(ck);
		}
		
		/* public void modifyCasillaKenken(int f, int c, int nwValue) throws MyOwnException
		 * 		Especificacion: modifica la casilla con la posición [f][c] del tablero si es posible, en caso negativo
		 * 		lanza una excepcion.
		 */
		public void modifyCurrentValueCasilla(int f, int c, int nwValue){
			this.MatrixKenken[f][c].setValor(nwValue);
		}
		
		public void setDefaultCasillaKenken(int f, int c, int val){
			this.MatrixKenken[f][c].setResultado(val);
			this.MatrixKenken[f][c].setEditable(true);
		}

		/* Push Zona */
			
			/*
			 * private void insertZona(Zona z)
			 * 		Especificacion: Inserta la Zona z en la ultima posicion del ArrayList<Zona> zonas
			 */
			private void insertZona(Zona z) {
				this.zonas.add(z);
			}
			
			/*
			 * public void addZona(Zona z)
			 * 		Especificacion: Inserta la Zona z en la ultima posicion del ArrayList<Zona> zonas
			 */
			public void addZona(Zona z) {
				insertZona(z);
			}
			
			/*
			 * public void addZona(char operacion, int resultado)
			 * 		Especificacion: Crea y inserta una Zona con operacion y resultado en la ultima posicion del ArrayList<Zona> zonas
			 */
			public void addZona(char operacion, int resultado) {
				Zona z = new Zona(operacion,resultado);
				insertZona(z);
			}
	
		/* Add Zona by Index */
			
			/*
			 * private void insertZonaByIndex(Zona z, int index)
			 * 		Especificacion: Inserta la Zona z en la posicion index del ArrayList<Zona> zonas
			 */
			private void insertZonaByIndex(Zona z, int index) {
				this.zonas.add(index,z);
			}
			
			/*
			 * public void addZonaByIndex(Zona z, int index)
			 * 		Especificacion: Inserta la Zona z en la posicion index del ArrayList<Zona> zonas
			 */
			public void addZonaByIndex(Zona z, int index) {
				insertZonaByIndex(z,index);
			}
			
			/*
			 * public void addZonaByIndex(char operacion, int resultado, int index)
			 * 		Especificacion: Crea y inserta una Zona con operacion y resultado en la posicion index del ArrayList<Zona> zonas
			 */
			public void addZonaByIndex(char operacion, int resultado, int index) {
				Zona z = new Zona(operacion,resultado);
				insertZonaByIndex(z,index);
			}
			/*----------------------------------------------------------------------------------------------------------------------------------*/	
	// Escrituras //
		
		/*
		 * public void print()
		 * 		Especificacion: Escribe por consola el Sudoku y el Kenken del TaulellKenken
		 */
		public void print() {
			this.printSudoku();
			this.printiZone();
			this.printZonas();
		}
	
}