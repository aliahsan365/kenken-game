/*
 * Moises Diaz
 */
package datos;

import java.util.*;

import domini.Ranking;

public class RankingGestor {
	private DatosGestor dg;
	
	public RankingGestor(){
		dg = DatosGestor.getInstance();
	}
	
	public void loadRanking(Ranking r) {
		//pre: Ranking está vacío. 
		String path = "datos/BDRanking.txt";
		ArrayList<String> aux = dg.leerTodo(path);
		ListIterator<String> it = aux.listIterator();
		while(it.hasNext()){
			r.plainAdd(it.next(), Integer.parseInt(it.next()));
		}
		
	}
	public void storeRanking(Ranking r){
		//pre: Ranking no está vacío. 
		int n = r.getN();
		String path = "datos/BDRanking.txt";
		ArrayList<String> aux = new ArrayList<String>();
		ListIterator<String> it = r.getTopTen().listIterator();
		for(int i = 0; i < n  ; ++i){
			aux.add(it.next());
			aux.add(it.next());
			aux.add("%n");
		}
		dg.escribirTodo(path, aux);
	}
	
}
