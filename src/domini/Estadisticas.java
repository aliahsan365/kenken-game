/*
 * Jonathan Nebot
 */

package domini;

import java.util.ArrayList;

public class Estadisticas {
	
	private static final int NELEMENTOS = 11;
	
	private RankingController rc;
	
	private int totalGames;
	private int lostGames;
	private int winGames;
	
	private long averageTime;
	private long fastestTime;
	private long slowestTime;
	
	private int setCasillaHelp;
	private int sugerenciaHelp;
	private int setSolucionHelp;
	
	private int bestPuntuacion;
	private int totalPuntuacion;
	
	//Constructoras
	
	/*
	 * Creadora de estadisticas vacia:
	 * 
	 * Crea la estructura del fichero de estadisticas para su primera vez
	 */
	public Estadisticas() {
		
		this.totalGames = 0;
		this.lostGames = 0;
		this.winGames = 0;
		
		this.averageTime = 0;
		this.fastestTime = -1;
		this.slowestTime = -1;
		
		this.setCasillaHelp = 0;
		this.sugerenciaHelp = 0;
		this.setSolucionHelp = 0;
		
		this.bestPuntuacion = -1;
		this.totalPuntuacion = 0;
	}
	
	/*
	 * Creadora de Estadisticas con elementos
	 * 
	 * Crea unas estadisticas ya inicializada con los valores del usuario
	 */
	public Estadisticas(ArrayList<String> l) {
		
		this.totalGames = Integer.parseInt(l.get(0));
		this.lostGames = Integer.parseInt(l.get(1));
		this.winGames = Integer.parseInt(l.get(2));
		
		this.averageTime = Integer.parseInt(l.get(3));
		this.fastestTime = Integer.parseInt(l.get(4));
		this.slowestTime = Integer.parseInt(l.get(5));
		
		this.setCasillaHelp = Integer.parseInt(l.get(6));
		this.sugerenciaHelp = Integer.parseInt(l.get(7));
		this.setSolucionHelp = Integer.parseInt(l.get(8));
		
		this.bestPuntuacion = Integer.parseInt(l.get(9));
		this.totalPuntuacion = Integer.parseInt(l.get(10));
	}
	
	/*
	 * Devuelve los datos necesarios de las estadisticas ya preparadas para guardar en el gestor
	 */
	public ArrayList<String> datosEstadisticasGestor() {
		ArrayList<String> l = new ArrayList<String>();		
		l.add(Integer.toString(totalGames)); 
		l.add(Integer.toString(lostGames));
		l.add(Integer.toString(winGames));
		l.add(Long.toString(averageTime));
		l.add(Long.toString(fastestTime));
		l.add(Long.toString(slowestTime));
		l.add(Integer.toString(setCasillaHelp));
		l.add(Integer.toString(sugerenciaHelp));
		l.add(Integer.toString(setSolucionHelp));
		l.add(Integer.toString(bestPuntuacion));
		l.add(Integer.toString(totalPuntuacion));
		return l;
	}
	
	/*
	 * Devuelve los datos necesarios de las estadisticas ya preparadas para presentar en la vista
	 */
	public ArrayList<String> datosEstadisticasPresentacion() {
		ArrayList<String> l = new ArrayList<String>();	
		if (totalGames == 0) {
			l.add("Necesitas jugar partidas antes de tener estadisticas =)");
		} else {
			l.add(Integer.toString(totalGames)); 
			l.add(Integer.toString(lostGames));
			l.add(Integer.toString(winGames));
			l.add(Long.toString(averageTime));
			l.add(Long.toString(fastestTime));
			l.add(Long.toString(slowestTime));
			l.add(Integer.toString(bestPuntuacion));
			l.add(Integer.toString(totalPuntuacion));
			l.add(Double.toString(getHelpRatioCasilla()));
			l.add(Double.toString(getHelpRatioSugest()));
			l.add(Double.toString(getHelpRatioSolucion()));
		}
		return l;
	}
	
	//Modificadores de Game
	
	    /*
	     * Actualiza el numero total de partidas y las ganadas o perdidas
	     */
		public void calculateGames(boolean win) {
			this.totalGames++;
			
			if(win) this.winGames++;
			else this.lostGames++;
		}
		
		//Modificadores de Time
		
		/*
		 * Calcula el tiempo medio de partidas
		 */
		public void calculateAverageTime(long time) {		
			this.averageTime = (this.averageTime+time)/this.totalGames;
		}
		
		/*
		 * Calcula el tiempo mas rapido de partida
		 */
		public void calculateFastestTime(long time) {
			if(this.fastestTime>time || this.fastestTime == -1) this.fastestTime = time;
		}
		/*
		 * Calcula el tiempo mas lento de partida
		 */
		public void calculateSlowestTime(long time) {
			if(this.slowestTime<time || this.slowestTime == -1) this.slowestTime = time;
		}
		
		/*
		 * Calcula los diferentes tiempos de partidas
		 */
		public void calculateTimes(long time) {
			calculateAverageTime(time);
			calculateFastestTime(time);
			calculateSlowestTime(time);
		}
		
		//Modificadores de Help
		
		/*
		 * Calcula los diferentes ratios de ayuda
		 */
		public void calculateHelps(int setCasillaHelpCont, boolean sugerenciaHelpBool, int setSolucionHelpCont) {
			this.setCasillaHelp += setCasillaHelpCont;
			if(sugerenciaHelpBool) this.sugerenciaHelp++;
			this.setSolucionHelp += setSolucionHelpCont;
		}
		
		/*
		 * Calcula la puntuación final y actualiza la mejor puntuacion en caso de serlo
		 */
		public void calculatePuntuacion(int puntuacion, int dificultad, int setCasillaHelpCont, boolean sugerenciaHelpBool, int setSolucionHelpCont, String user) {
			
			if(sugerenciaHelpBool) puntuacion = puntuacion-(puntuacion/5);
			if(setCasillaHelpCont>0) puntuacion = puntuacion-(5*setCasillaHelpCont);
			if(setSolucionHelpCont>0) puntuacion = puntuacion-(10*setSolucionHelpCont);
			
			if(dificultad == 2) puntuacion = puntuacion+(puntuacion/20);
			else if(dificultad == 3) puntuacion = puntuacion+(puntuacion/10);
			
			if(this.bestPuntuacion< puntuacion) this.bestPuntuacion = puntuacion;
			
			if (puntuacion>0) {
				this.totalPuntuacion += puntuacion;
				rc = RankingController.getInstance();
				rc.actualizarRanking(user, puntuacion);
			}
		}
		
	//Consultoras Game
		
		
		public int getTotalGames() {
			return this.totalGames;
		}
	
		public int getLostGames() {
			return this.lostGames;
		}
		
		public int getWinGames() {
			return this.winGames;
		}
		
	//Consultoras Time
	
		public long getAverageTime() {
			return this.averageTime;
		}
		
		public long getFastestTime() {
			return this.fastestTime;
		}
	
		public long getSlowestTime() {
			return this.slowestTime;
		}
		
	//Consultoras Help Ratios
		
		public int getSetCasillaHelp() {
			return this.setCasillaHelp;
		}
		
		public int getSugerenciaHelp() {
			return this.sugerenciaHelp;
		}
		
		public int getSetSolucionHelp() {
			return this.setSolucionHelp;
		}
	
		public double getHelpRatioCasilla() {
			return (this.setCasillaHelp/(double)this.totalGames);
		}
		
		public double getHelpRatioSugest() {
			return (this.sugerenciaHelp/(double)this.totalGames);
		}
		
		public double getHelpRatioSolucion() {
			return (this.setSolucionHelp/(double)this.totalGames);
		}
	
	//Consultoras Puntuacion
		
		public int getBestPuntuacion() {
			return this.bestPuntuacion;
		}
		
		public int getTotalPuntuacion() {
			return this.totalPuntuacion;
		}
		//Modificadoras Game
				
		public void setTotalGames(int tt) {
			this.totalGames = tt;
		}
	
		public void setLostGames(int lg) {
			this.lostGames = lg;
		}
		
		public void setWinGames(int wg) {
			this.winGames = wg;
		}
		
	//Modificadoras Time
	
		public void setAverageTime(int avg) {
			this.averageTime = avg;
		}
		
		public void setFastestTime(int fst) {
			this.fastestTime = fst;
		}
	
		public void setSlowestTime(int slw) {
			this.slowestTime = slw;
		}
	
	// Modificadoras Puntuacion
		
		public void setBestPuntuacion(int bt) {
			this.bestPuntuacion = bt;
		}
		
		public void setTotalPuntuacion(int totP) {
			this.totalPuntuacion = totP;
		}

		public void setSetCasillaHelp(int casilla) {
			this.setCasillaHelp = casilla;
		}

		public void setSugerenciaHelp(int sugerencia) {
			this.sugerenciaHelp = sugerencia;
			
		}

		public void setSetSolucionHelp(int solucion) {
			this.setSolucionHelp = solucion;
		}
		
		public static int getnElementos() {
			return NELEMENTOS;
		}

}
