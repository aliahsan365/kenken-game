/*
 * Moises Diaz
 */

package domini;

import datos.RankingGestor;
import java.util.*;

public class RankingController {
	private static RankingController instance = null;
	private Ranking r;
	private RankingGestor rg;
	
	protected RankingController() {
		rg = new RankingGestor();
		r = new Ranking();
		createRanking();
	}
	public static RankingController getInstance() {
		if(instance == null) {
			instance = new RankingController();
		}
		
		return instance;
	}
	
	public void actualizarRanking(String userName, int punt){
		r.addPuntuacion(userName, punt);
		writeRanking();
	}
	public void createRanking(){
		rg.loadRanking(r);
	}
	public void writeRanking(){
		rg.storeRanking(r);
	}
	
	public ArrayList<String> getTop(){
		return this.r.getTopTen();

	}
	
	public void printRanking() {
		r.printPuntuaciones();
	}
}
