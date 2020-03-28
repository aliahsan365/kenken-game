/*
 * Jonathan Nebot
 */

package domini;

import java.util.Objects;

public class Pair {
	
	// Atributos //
	
	    private int fila;		//Entero fila
	    private int columna;	//Entero columna

    // Constructoras //
    
	    /*
	     * public Pair()
	     * 	Especificacion: Crea un pair vacio con los valores por defecto
	     */
	    public Pair() {
	    	super();
	    	this.fila = -1;
	    	this.columna = -1;
	    }
	    
	    /*
	     * Pair(fila,columna)
	     * 	Especificacion: Crea un pair con los valores introducidos
	     */
	    public Pair(int fila, int columna) {
	    	super();
	    	this.fila = fila;
	    	this.columna = columna;
	    }

    /* Condicion: El pair debe ser inicializado previamente para poder ser modificado, consultado o generar el hashCode */
    
	    /*
	     * public int hashCode()
	     * 	Especificacion: Crea y devuelve un hashCode con los valores de pair
	     */
	    public int hashCode() {
	    	return Objects.hash(fila, columna);
	    }

    // Consultoras //
    
	    /*
	     * public boolean equals(Object other)
	     * 	Especificacion: Compara si el objeto introducido es igual a este,
	     * 		en caso de fallar la comparacion directa comprueba
	     *  	si el objeto es una instancia de pair y si es así
	     *  	vuelve a hacer la comparacion con una copia,
	     *  	elemento por elemento
	     */
	    public boolean equals(Object other) {
	    	if(other == this) return true;
	    	
	    	if (other instanceof Pair) {
	    		Pair otherPair = (Pair) other;
	    		
	    		return (Objects.equals(columna, otherPair.columna) && Objects.equals(fila, otherPair.fila));
	    	}
	
	    	return false;
	    }
	
	    /*
	     * public String toString()
	     * 	Especificacion: Parsea el contenido de pair a un string y lo devuelve
	     */
	    public String toString() { 
	           return "(" + fila + ", " + columna + ")"; 
	    }
	    
	    /*
	     * public int getFila()
	     * 	Especificacion: Devuelve el entero fila
	     */
	    public int getFila() {
	    	return fila;
	    }
	
	    /*
	     * public int getColumna()
	     * 	Especificacion: Devuelve el entero columna
	     */
	    public int getColumna() {
	    	return columna;
	    }
	    
	    /*
	     * public boolean empty()
	     * 	Especificacion: Devuelve un boleano indicando si la fila o la columna tienen valor -1
	     */
	    public boolean empty() {
	    	if(fila==-1 || columna == -1) return true;
	    	else return false;
	    }
    
    // Modificadoras //
    
	    /*
	     * public void setFila(int fila)
	     * 	Especificacion: Modifica el entero fila del pair
	     */
	    public void setFila(int fila) {
	    	this.fila = fila;
	    }
	
	    /*
	     * public void setColumna(int columna)
	     * 	Especificacion: Modifica el entero columna del pair
	     */
	    public void setColumna(int columna) {
	    	this.columna = columna;
	    }
}