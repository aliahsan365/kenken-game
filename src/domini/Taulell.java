package domini;

import java.io.IOException;

public class Taulell {
	
	public Casilla Matriu_joc [][];
	private int Matriu_solucio [][];
	
	//Constructores
	// Inicialitza matrius amb mida "tamany x tamany"
	public Taulell(int tamany) {
		Matriu_joc = new Casilla [tamany][tamany];
		Matriu_solucio = new int [tamany][tamany];
	}
	
	//Modificadores
	//Retorna el valor de la casella de la matriu joc 
	//que es troba a la fila fila i a la columna columna.
	public int GetValor_joc (int fila, int columna) {
		return Matriu_joc[fila][columna].getValor();
	}
	
	//Retorna el valor de la casella de la matriu solució
	//que es troba a la fila fila i a la columna columna.
	public int GetValor_sol (int fila, int columna) {
		return Matriu_solucio [fila][columna];
	}
	
	//Crida a getValor_x per comparar el valor que es troba a la matriu joc
	//a la casella amb fila fila i columna col, amb el valor de la matriu solucio 
	//de la casella que es troba a la fila fila i columna col.
	public boolean compara_solucio (int fila, int columna){
		return GetValor_joc (fila,columna) == GetValor_sol(fila, columna);
	}
	
	//Com a precondició, la casella modificada no pot ser casella predefinida. 
	//Modifica el valor de la casella situada a la fila fila i la columna col de la matriu joc. 
	//Si es deixa en blanc, el valor de la casella será zero.
	public void modifica_valor (int new_valor, int fila, int columna) {
		if (!Matriu_joc[fila][columna].getEditable()){
			//throw new IOException ("Error:Casella no editable"); PETA NO SE PERQUEE
		}
		else Matriu_joc[fila][columna].setValor(new_valor);	
	}

}
