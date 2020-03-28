/*
 * Toni Palacios
 */

package domini;

import java.util.*;

public class Ranking {
	private int N;
	private ArrayList<Integer> puntuaciones;
	private ArrayList<String> userNames;
	
	//Constructoras
	
		public Ranking() {
			this.N = 10;
			this.puntuaciones = new ArrayList<Integer>();
			this.userNames = new ArrayList<String>();
		}
		
	//Modificadoras
		public void plainAdd(String userN, int puntuacion){
			this.puntuaciones.add(puntuacion);
			this.userNames.add(userN);
		}
		public void addPuntuacion(String userN, int puntuacion) {
			boolean insert = false;
			int i = 0;
			while(!insert && i<this.puntuaciones.size()) {
				if(this.puntuaciones.get(i) < puntuacion){
					this.puntuaciones.add(i, puntuacion);
					this.userNames.add(i, userN);
					insert = true;
				}
				++i;
			}
			if(!insert){
				this.puntuaciones.add(puntuacion);
				this.userNames.add(userN);
			}
			if(this.puntuaciones.size() > 10){
				this.puntuaciones.remove(10);
				this.userNames.remove(10);
			}
		}
		
		
	//Consultoras
		private int getMinSize(){
			if (puntuaciones.size() < N) return puntuaciones.size();
			else return N;
		}
		
		public int getPuntuacion(int index) {
			return puntuaciones.get(index);
		}
		public int getN(){
			return getMinSize();
		}
		
		public ArrayList<String> getTopTen(){
			ArrayList<String> r = new ArrayList<String>();
			//ListIterator<Integer> it = this.puntuaciones.listIterator();
			//ListIterator<String> it2 = this.userNames.listIterator();
			
			
			for(int i = 0; i < this.puntuaciones.size(); ++i){
				r.add(this.userNames.get(i));
				r.add(String.valueOf(this.puntuaciones.get(i)));
			}
			return r;
		}
		
	//PRINT OUT
		
		public void printPuntuaciones() {
			for(int i = 0; i<this.puntuaciones.size(); i++) {
				
				System.out.println("Posicion "+(i+1));
				System.out.println("  UserID: "+this.puntuaciones.get(i));
				System.out.println("  Puntuacion: "+this.puntuaciones.get(i));
				System.out.println();
			}
		}
}
