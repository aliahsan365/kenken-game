/*
 * Toni Palacios
 */

package domini;

import java.util.ArrayList;
import java.util.Stack;

public class Zona {
   
   // Atributos //
	
	   private char operacion;								//Caracter que indica la operacion que se le aplica a las casillas de la Zona
	   private long resultado;								//Entero que indica el resultado dado entre las casillas de la Zona
	   														//tras aplicar la operacion
	   private static int directionsX[] = {1, 0, -1, 0};	//Direcciones de desplazamiento por el TaulellKenken a la hora de definir 
	   private static int directionsY[] = {0, 1, 0, -1};	//las zonas (verticales y horizontales respectivamente)

   // Constructoras //
   
	   /*
	    * public Zona()
	    * 	Especificación: Crea una zona vacia, con los valores por defecto
	    */
	   public Zona() {
	      this.operacion = '.';
	      this.resultado = -1;
	   }
	   
	   /*
	    * public Zona(char operacion, int resultado)
	    * 	Especificación: Crea una zona con los valores introducidos
	    */
	   public Zona(char operacion, long resultado) {
	      this.operacion = operacion;
	      this.resultado = resultado;
	   }
   
   // Modificadores //
   
	   /*
	    * private void applyOperacion(int number)
	    * 	Especificación: Modifica el resultado aplicando la operacion definida en la zona al entero number
	    */
	   private void applyOperacion(int number) {
		   if(operacion == '+') resultado += number;
		   else if(operacion == '-') {
			   if(resultado<number) resultado = number-resultado;
			   else resultado -= number;
		   }
		   else if(operacion == 'x') resultado *= number;
		   else {
			   if(resultado<number) resultado = number/resultado;
			   else resultado /= number;
		   }
	   }
	   
	   private void setZonaToSuma(CasillaKenken ck[][], int index) {
		   for(int x = 0; x < ck.length; x++) {
			   for(int y = 0; y < ck.length; y++) {
				   if(ck[x][y].getIndexZona()==index) this.resultado += ck[x][y].getResultado();
			   }
		   }
	   }
	   
	   /*
	    * public void defineZona(CasillaKenken ck[][], Pair p, ArrayList<Zona> zonas)
	    * 	Especificación: Dependiendo la operacion se asigna el número de casillas de la Zona
	    * 	y se utiliza un algoritmo similar al BFS (reducido por la condición quantity>0 en el while)
	    * 	para definir la zona alrededor de la posicion inicial indicada en el Pair p.
	    */
	   public void defineZona(CasillaKenken ck[][], Pair p, ArrayList<Zona> zonas) {
		   Utilities u = new Utilities();
		   int quantity = 0;
		   boolean first = true;
		   
		   if(this.operacion == '+' || this.operacion == 'x') quantity = u.newRandom(ck.length)+1;
		   if(quantity<2) quantity = 2;
		   
		   Stack<Pair> s = new Stack<Pair>();
		   s.push(p);
		   
		   this.resultado = ck[p.getFila()][p.getColumna()].getResultado();
		   
		   while(!s.empty() && quantity>0) {
			   
			   Pair ap = s.pop();
			   
			   if(!first) applyOperacion(ck[ap.getFila()][ap.getColumna()].getResultado());
			   
			   ck[ap.getFila()][ap.getColumna()].setIndexZona(zonas.size());
			   
			   for(int i = 0; i < directionsX.length; i++) {
					int nx = ap.getFila()+directionsX[i];
					int ny = ap.getColumna()+directionsY[i];
					
					if(insideTaulell(nx,ny,ck.length) && ck[nx][ny].getIndexZona()==-1) {
						Pair np = new Pair(nx,ny);
						s.push(np);
					}		
			   }
			   
			   first = false;
			   quantity--;
		   }
		   
		   //Overflow exception
		   if(this.resultado<0) {
			   this.operacion = '+';
			   this.resultado = 0;
			   
			   setZonaToSuma(ck, zonas.size());
		   }
	   }
	   
	   /*
	    * public void setOperacion(char operacion)
	    * 	Especificación: Modifica el caracter operacion con el introducido
	    */
	   public void setOperacion(char operacion) {
	      this.operacion = operacion;
	   }
	   
	   /*
	    * public void setResultado(char resultado)
	    * 	Especificación: Modifica el entero resultado con el introducido
	    */
	   public void setResultado(int resultado) {
	      this.resultado = resultado;
	   }
   
   // Consultoras //
   
	   /*
	    * private boolean insideTaulell(int x, int y, int tamany)
	    * 	Especificación: Devuelve true si la posicion (x,y) esta dentro de los limites de la matriz con tamaño tamany
	    */
	   private boolean insideTaulell(int x, int y, int tamany) {
		   return (x>-1 && y>-1 && x<tamany && y<tamany);
	   }
	   
	   /*
	    * private boolean anyAdjFree(int x, int y, CasillaKenken ck[][])
	    * 	Especificación: Devuelve true si encuentra alguna casilla adjacente a la posicion (x,y) que no tiene una zona asignada
	    */
	   private boolean anyAdjFree(int x, int y, CasillaKenken ck[][]) {		
			for(int i = 0; i < directionsX.length; i++) {
				int nx = x+directionsX[i];
				int ny = y+directionsY[i];
				
				if(insideTaulell(nx,ny,ck.length) && ck[nx][ny].getIndexZona()==-1) return true;
			}
			
			return false;
		}
		
	   /*
	    * public Pair freeRegion(CasillaKenken ck[][])
	    * 	Especificación: Busca y devuelve la primera casilla que no tiene una zona asignada y 
	    * 	almenos tiene una más adyacente a ella que tampoco tiene una zona asignada dentro de
	    * 	la matriz ck.
	    */
		public Pair freeRegion(CasillaKenken ck[][]) {
			Utilities u = new Utilities();
			
			Pair p = new Pair(-1,-1);
			boolean bmat[][] = new boolean[ck.length][ck.length];
			
			while(!u.allTrue(bmat)) {
				int x = u.newRandom(ck.length);
				int y = u.newRandom(ck.length);
				
				if(ck[x][y].getIndexZona()==-1 && anyAdjFree(x,y,ck)) {
					p.setFila(x);
					p.setColumna(y);
					
					return p;
				}
				
				bmat[x][y] = true;
			}
			
			return p;
		}
	   
		/*
	    * public char getOperacion()
	    * 	Especificación: Devuelve el caracter operacion
	    */
	   public char getOperacion() {
	      return this.operacion;
	   }
	   
	   /*
	    * public int getResultado()
	    * 	Especificación: Devuelve el entero resultado
	    */
	   public long getResultado() {
	      return this.resultado;
	   }
	   
	   /*
	    * public boolean isEqual(Zona z)
	    * 	Especificación: Devuelve true si la Zona z es igual a la actual, comparando su contenido
	    */
	   public boolean isEqual(Zona z) {
		   return (this.resultado==z.getResultado() && this.operacion == z.getOperacion());
	   }
} 
