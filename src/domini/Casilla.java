/*
 * Toni Palacios
 */

package domini;

public class Casilla {
   
   // Atributos //
	
	   private Pair posicion;		//Pair con la fila y la columna de la casilla en el tablero
	   private boolean editable;	//Boolean que indica si esta casilla puede ser editada por el usuario
	   private int valor;			//Entero con el valor actual introducido por el usuario

   // Constructoras //
   
	   /*
	    * public Casilla()
	    * 	Especificacion: Crea una casilla vacía con los valores por defecto
	    */
	   public Casilla() {
		   this.posicion = new Pair(-1,-1);
		   this.editable = true;
		   this.valor = 0;
	   }
	   
	   /*
	    * public Casilla(int fila, int columna, boolean editable, int valor)
	    * 	Especificacion: Crea una casilla con los valores introducidos
	    */
	   public Casilla(int fila, int columna, boolean editable, int valor) {
			  this.posicion = new Pair(fila, columna);
		      this.editable = editable;
		      this.valor = valor;
	   }
   
   
   /* Condicion: La casilla debe ser inicializada previamente para poder ser modificada o consultada */
   
   
   // Modificadores //

	   /*
	    * public void setPosicion(int columna, int fila)
	    * 	Especificacion: Modifica la columna y la fila del objeto Pair de la casilla
	    */
	   public void setPosicion(int columna, int fila) {
	      this.posicion.setColumna(columna);
		  this.posicion.setFila(fila);
	   }
	   
	   /*
	    * public void setFila(int fila)
	    * 	Especificacion: Modifica la fila del objeto Pair de la casilla
	    */
	   public void setFila(int fila) {
		  this.posicion.setFila(fila);
	   }
	   
	   /*
	    * public void setColumna(int columna)
	    * 	Especificacion: Modifica la columna del objeto Pair de la casilla
	    */
	   public void setColumna(int columna) {
	      this.posicion.setColumna(columna);
	   }
	   
	   /*
	    * public void setEditable(boolean editable)
	    * 	Especificacion: Modifica el boleano editable de la casilla
	    */
	   public void setEditable(boolean editable) {
	      this.editable = editable;
	   }
	   
	   /*
	    * public void setValor(int valor)
	    * 	Especificacion: Modifica el valor actual de la casilla
	    */
	   public void setValor(int valor) {
	      this.valor = valor;
	   }
   
   // Consultoras //

	   /*
	    * public Pair getPosicion()
	    * 	Especificacion: Devuelve el objeto Pair de la casilla
	    */
	   public Pair getPosicion() {
	      return this.posicion;
	   }
	   
	   /*
	    * public int getFila()
	    * 	Especificacion: Devuelve el entero fila del objeto Pair de la casilla
	    */
	   public int getFila() {
		  return this.posicion.getFila();
	   }
	   
	   /*
	    * public int getColumna()
	    * 	Especificacion: Devuelve el entero columna del objeto Pair de la casilla
	    */
	   public int getColumna() {
	      return this.posicion.getColumna();
	   }
	   
	   /*
	    * public boolean getEditable()
	    * 	Especificacion: Devuelve el boleano editable de la casilla
	    */
	   public boolean getEditable() {
	      return this.editable;
	   }
	   
	   /*
	    * public int getValor()
	    * 	Especificacion: Devuelve el entero valor de la casilla
	    */
	   public int getValor() {
	      return this.valor;
	   }
} 
