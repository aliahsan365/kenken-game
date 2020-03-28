/*
 * Toni Palacios
 */

package domini;


public class CasillaKenken extends Casilla {
	
	// Atributos //
	
		private int resultado;	//Entero con el resultado por defecto de la casilla
		private int indexZona;	//Entero con el indice de la zona que indica su posicion en el ArrayList<Zona> de TaulellKenken
	
	// Contructoras //
	
		/*
		 * public CasillaKenken()
		 * 	Especificacion: Crea una casilla kenken vacía con los valores por defecto
		 */
		public CasillaKenken() {
			super();
			this.resultado = -1;
			this.indexZona = -1;
		}
		
		/*
	    * public CasillaKenken(int resultado, int indexZona, int fila, int columna, boolean editable, int valor)
	    * 	Especificacion: Crea una casilla con los valores introducidos
	    */
		public CasillaKenken(int resultado, int indexZona, int fila, int columna, boolean editable, int valor) {
			super(fila, columna, editable, valor);
			this.resultado = resultado;
			this.indexZona = indexZona;
		}
	
	/* Condicion: La casilla kenken debe ser inicializado previamente para poder ser modificada o consultada */
		
	// Consultoras //
		
		/*
	    * public int getResultado()
	    * 	Especificacion: Devuelve el entero resultado
	    */
		public int getResultado() {
			return this.resultado;
		}
		
		/*
	    * public int getIndexZona()
	    * 	Especificacion: Devuelve el entero indexZona
	    */
		public int getIndexZona() {
			return this.indexZona;
		}
		
		/*
	    * public boolean isSolucion()
	    * 	Especificacion: Devuelve un boleano indicando si el valor introducido por el usuario es igual al resultado
	    */
		public boolean isSolucion() {
			return (this.getValor()==this.resultado);
		}
	
	// Modificadoras //
	
		/*
	    * public void setResultado(int resultado)
	    * 	Especificacion: Modifica resultado de la casilla kenken
	    */
		public void setResultado(int resultado) {
			this.resultado = resultado;
		}
		
		/*
	    * public void setIndexZona(int indexZona)
	    * 	Especificacion: Modifica indexZona de la casilla kenken
	    */
		public void setIndexZona(int indexZona) {
			this.indexZona = indexZona;
		}
		
		/*
	    * public void copyCasilla(CasillaKenken ck)
	    * 	Especificacion: hace una copia de ck en la casilla kenken modificando todo su contenido
	    */
		public void copyCasilla(CasillaKenken ck){
			this.setValor(ck.getValor());
			this.setResultado(ck.getResultado());
			this.setEditable(ck.getEditable());
			this.setIndexZona(ck.getIndexZona());
			this.setPosicion(ck.getPosicion().getFila(), ck.getPosicion().getColumna());
		}
}