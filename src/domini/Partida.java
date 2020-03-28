/*
 * Ali Muhammmad
 */

package domini;

import java.util.ArrayList;

public class Partida {
	private int idTablero;
	private int idUser;
	private long timeInc;
	private int puntuacion;
	private int fallos; 
	private int aciertos;
	private boolean esAyudaNextNum;
	private boolean sugerencias;
	private boolean esAyudaMarcarCasilla;
	private int ayudaNextNum;
	private int ayudaMarcarCasilla;
	private long initTime;
	private long finishTime;
	
	
	public Partida(int idU, int dif, int idT, boolean aSug, boolean aMarcar, boolean aNext){
		this.idTablero = idT;
		this.idUser = idU;
		this.timeInc = 0;
		this.puntuacion = 0;
		this.fallos = 0;
		this.aciertos = 0;
		this.esAyudaNextNum = aNext;
		this.sugerencias = aSug;
		this.esAyudaMarcarCasilla = aMarcar;
		this.ayudaNextNum = 0;
		this.ayudaMarcarCasilla = 0;
		this.initTime = System.currentTimeMillis()/1000;
	}
	
	/*
    private int idTablero;+0
	private int idUser;+1
	private long timeInc;+2
	private int dificultad;
	private int puntuacion;+3
	private int fallos; +4
	private int aciertos;+5
	private boolean esAyudaNextNum;+6
	private boolean sugerencias;+7
	private boolean esAyudaMarcarCasilla;+8
	private int ayudaNextNum;+9
	private int ayudaMarcarCasilla;+10
    */
	public Partida(ArrayList<String> l){
		this.idTablero = Integer.parseInt(l.get(0));
		this.idUser = Integer.parseInt(l.get(1));
		this.timeInc = Long.parseLong(l.get(2));
		this.puntuacion = Integer.parseInt(l.get(3));
		this.fallos = Integer.parseInt(l.get(4));
		this.aciertos = Integer.parseInt(l.get(5));
		this.esAyudaNextNum = Boolean.parseBoolean(l.get(6));
		this.sugerencias = Boolean.parseBoolean(l.get(7));
		this.esAyudaMarcarCasilla = Boolean.parseBoolean(l.get(8));
		this.ayudaNextNum = Integer.parseInt(l.get(9));
		this.ayudaMarcarCasilla = Integer.parseInt(l.get(10));
		this.initTime = System.currentTimeMillis()/1000;
	}
	
	public ArrayList<String> datosPartida() {
		   ArrayList<String> l = new ArrayList<String>();
		   l.add(Integer.toString(getIdTablero()));
		   l.add(Integer.toString(getIdUser()));
		   l.add(Long.toString(getTime()));
		   l.add(Integer.toString(getPuntuacion()));
		   l.add(Integer.toString(getFallos()));
		   l.add(Integer.toString(getAciertos()));
		   l.add(Boolean.toString(isEsAyudaNextNum()));
		   l.add(Boolean.toString(isSugerencias()));
		   l.add(Boolean.toString(isEsAyudaMarcarCasilla()));
		   l.add(Integer.toString(getAyudaNextNum()));
		   l.add(Integer.toString(getAyudaMarcarCasilla()));
		   return l;
	   }
	
	//getters
	
	public long getTime() {
		setFinishTime();
		return finishTime - initTime + timeInc;
	}
	
	public int getIdTablero() {
		return idTablero;
		}
	
    public int getPuntuacion() {
		return puntuacion;
		}
	
	public int getFallos() {
		return fallos;
	} 
	
	public int getAciertos() {
		return aciertos;
	}
	
	public boolean isSugerencias() {
		return sugerencias;
	}
	
	public int getAyudaNextNum() {
		return ayudaNextNum;
		
	}
	public int getAyudaMarcarCasilla() {
		return ayudaMarcarCasilla;
		
	}
	
	public long getTimeInc() {
		return timeInc;
	}
	
	
	//setters
	
	public void setInitTime() {
		this.initTime = System.currentTimeMillis()/1000;
	}
	
	public void setFinishTime() {
		this.finishTime = System.currentTimeMillis()/1000;
	}
	
	public void setTimeInc(long time){
		this.timeInc = time;
	}
	
	public void setIdTablero(int idTablero){
		this.idTablero = idTablero;
	}
	
	public void setAyudaNextNum(int num){
		this.ayudaNextNum = num;
	}
	
	public void incAyudaNextNum(){
		this.ayudaNextNum++;
	}
	
	public void setAyudaMarcarCasilla(int num){
		this.ayudaMarcarCasilla = num;
	}
	
	public void incAyudaMarcarCasilla(){
		this.ayudaMarcarCasilla++;
	}
	
	public void decAyudaMarcarCasilla(){
		this.ayudaMarcarCasilla--;
	}
	
	public void setSugerencias(boolean sugerencias){
		this.sugerencias = sugerencias;
	}
	
	public void setFallos(int fallos){
		this.fallos += fallos;
	}
	
	
	public void setAciertos(int aciertos){
		this.aciertos += aciertos;
	}
	
	/*public void setDificultad(int dificultad){
		this.dificultad = dificultad;
	}*/
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void incMarPuntuacion(int dif) {
		if (this.puntuacion > 0) {
			switch (dif) {
			case 1:
				this.puntuacion += 5;
				break;
			case 2:
				this.puntuacion += 10;
				break;
			case 3:
				this.puntuacion += 50;
				break;
			}
		}
	}
	
	public void decMarPuntuacion(int dif) {
		if (this.puntuacion > 0) {
			switch (dif) {
			case 1:
				this.puntuacion -= 5;
				break;
			case 2:
				this.puntuacion -= 10;
				break;
			case 3:
				this.puntuacion -= 50;
				break;
			}
		}
	}
	
	public void decResPuntuacion(int dif) {
		if (this.puntuacion > 0) {
			switch (dif) {
			case 1:
				this.puntuacion -= 20;
				break;
			case 2:
				this.puntuacion -= 50;
				break;
			case 3:
				this.puntuacion -= 100;
				break;
			}
		}
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int id) {
		this.idUser = id;
	}

	public boolean isEsAyudaMarcarCasilla() {
		return esAyudaMarcarCasilla;
	}

	public void setEsAyudaMarcarCasilla(boolean es) {
		this.esAyudaMarcarCasilla = es;
	}

	public boolean isEsAyudaNextNum() {
		return esAyudaNextNum;
	}

	public void setEsAyudaNextNum(boolean es) {
		this.esAyudaNextNum = es;
	}
	
}
