/*
 * Toni Palacios
 */

package domini;

import java.util.Random;

public class Utilities {
	
	public Utilities() {
		
	}
	
	public int newRandom(int tamany) {
		Random rand = new Random();
		return rand.nextInt(tamany);
	}
	
	public float newRandom() {
		Random rand = new Random();
		return rand.nextFloat();
	}
	
	public boolean allTrue(boolean bpos[]) {
		for(int i = 0; i < bpos.length; i++) if(!bpos[i]) return false;
		return true;
	}
	
	public boolean allTrue(boolean bmat[][]) {
		for(int i = 0; i < bmat.length; i++) {
			if(!allTrue(bmat[i])) return false;
		}
		
		return true;
	}

	public boolean allTrue(int is[]) {
		for(int i = 0; i < is.length; i++) if(is[i]==0) return false;
		return true;
	}
	
	public boolean allTrue(int imat[][]) {
		for(int i = 0; i < imat.length; i++) {
			if(!allTrue(imat[i])) return false;
		}
		
		return true;
	}
}
