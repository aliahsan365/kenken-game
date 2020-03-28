/*
 * Jonathan Nebot
 */

package domini;


import java.io.FileNotFoundException;
import java.util.ArrayList;
//import datos.GestorPartida;

import datos.GestorPartida;

public class ControladorPartida {
	
	private ControllerTablero ct;
	private Partida p;
    private GestorPartida gp;
	private EstadisticasController ec;
	private static ControladorPartida instance;
	private int idUser;
	private boolean eraGuardada;
	private static boolean pAnonimo;
	private static boolean pUser;
	private static boolean pCrearKenken;
	private boolean esGuardada;
	private int nElementos;
	
	
	/*
	 * 
	 * CREADORAS:
	 * 
	 * ControladorPartida.getInstance() = Partida anonimo
	 * ControladorPartida.getInstance(int idUser) = Partida usuario logueado
	 * ControladorPartida.getInstance(int id, ControllerTablero c) = Partida lanzada desde crear kenken pers.
	 * 
	 */
	protected ControladorPartida () {
		idUser = -1;
		p = null;
		gp = null;
		ct = null;
		eraGuardada = false;
		pAnonimo = true;
		pUser = false;
		pCrearKenken = false;
		nElementos = 11;
		esGuardada = false;
    }
	
	protected ControladorPartida (int id) {
		idUser = id;
		p = null;
		gp = null;
		ct = null;
		eraGuardada = false;
		pAnonimo = false;
		pUser = true;
		pCrearKenken = false;
		nElementos = 11;
		esGuardada = false;
    }
	
	protected ControladorPartida (int id, ControllerTablero c) {
		idUser = id;
		ct = c;
		p = null;
		gp = null;
		eraGuardada = false;
		pAnonimo = false;
		pUser = false;
		pCrearKenken = true;
		nElementos = 11;
		esGuardada = false;
    }
	
	public static ControladorPartida getInstance() {
		if(instance == null || !pAnonimo) {
			instance = new ControladorPartida();
		}
		
		return instance;
	}
	
	public static ControladorPartida getInstance(int idUser) {
		if(instance == null || !pUser) {
			instance = new ControladorPartida(idUser);
		}
		
		return instance;
	}
	
	public static ControladorPartida getInstance(int id, ControllerTablero c) {
		if(instance == null || !pCrearKenken) {
			instance = new ControladorPartida(id, c);
		}
		
		return instance;
	}
	
	/*
	 * Comunicacion de partida con vista y tablero
	 */
	//////////////////////////
	/////////////////////////
   public void setDataZone(int dataZone[][]) {
	   ct.setZonasCasillas(dataZone);
   }
   
   public void setDataDefault(int dataDefault[][]) {
	   ct.setDefaults(dataDefault);
   }
  
   public void setOperaciones(ArrayList<String> op) {
	   ct.setZonasTablero(op);
   }
   
   public void setDataValues(int dataValues[][]) {
	   ct.setCurrentValues(dataValues);
   }
   
   public  int[][] getDataZone() {
	   return ct.getDataZone();
   }
   
   public int[][] getDataDefault() {
	   return ct.getDefaults();
   }
  
   public ArrayList<String> getOperaciones () {
	   return ct.getOperaciones();
   }
   
   public int[][] getDataValues() {
	   return ct.getCurrentValues();
   }
   
   public int[][] getResultado() {
	   return ct.getResultado();
   }
   /////////////////////////
   ////////////////////////
   
   /*
	* Guarda la partida cargada en p en el txt
	*/
   public void guardarPartida(String nPartida, int dataValue[][]) throws FileNotFoundException {
	   gp = new GestorPartida(p.getIdUser(), nElementos);
	   gp.newPartida(p.datosPartida());
	   ct.setKenkenName(nPartida);
	   ct.setCurrentValues(dataValue);
	   ct.addToConjunto();
	   gp = null;
   }
   
   /*
    * Carga lista de partidas dónde 
    * 
    * get(i) = "Nombre Partida"
    * get(i+1) = idTablero
    * 
    */
   public ArrayList<String> cargarListaPartidas() {
	   gp = new GestorPartida(idUser, nElementos);
	   ArrayList<Integer> l = gp.cargarListaPartidas();
	   gp = null;
	   ct = ControllerTablero.getInstance();
	   return ct.getKenkenList(l);
   }
   
   /*
    * Carga la partida guardada con id = idTablero
    * 
    */
   public void cargarPartida(int idTablero)  {
	   gp = new GestorPartida(idUser, nElementos);
	   ArrayList<String> l = gp.cargarPartida(idTablero);
	   p = new Partida(l);
	   gp = null;
	   esGuardada = true;
	   ct.setTablero(idTablero);
   }
   
   public boolean resolverKenken(int dataValues[][]) {
	   return ct.resolverKenken(dataValues);
   }
   
   /*
    * Crea una nueva partida rápida
    * 
    * nuevaPartidaRapida(int dif, int n, int idT, boolean aSug, boolean aMarcar, boolean aNext):
    * 
    * dif = dificultat
    * n = NxN
    * idT = idTablero
    * ayuda Sugerencia = si la tiene activada o no
    * Ayuda Marcar casilla = si la tiene activada o no
    * ayuda next num = si la tiene activada o no
    * 
    * 
    */
   
   protected void setPuntuacionIniPar(int dif) {
	   switch (dif) {
	   case 1:
		   p.setPuntuacion(200);
		   break;
	   case 2:
		   p.setPuntuacion(300);
		   break;
	   case 3:
		   p.setPuntuacion(500);
		   break;
	   }
   }
   
   /*
    * Crea una nueva partida rapida
    * 
    * nuevaPartidaRapida(int dif, int n, boolean aSug, boolean aMarcar, boolean aNext)
    * 
    * dif = dificultad
    * n = tamaño kenken NxN
    * aSug = true si tiene la ayuda de sugerencia activada
    * aMarcar = true si tiene la ayuda de marcar casilla activada
    * aNext = true si tiene la ayuda de poner siguiente nuemro activada
    * 
    */
   public void nuevaPartidaRapida(int dif, int n, boolean aSug, boolean aMarcar, boolean aNext) {
	   ct = ControllerTablero.getInstance(n, -1, dif, true);
	   p = new Partida(idUser, 0, ct.getIdKenken(), aSug, aMarcar, aNext);
	   setPuntuacionIniPar(dif);
	}
   
   /*
    * Crea una nueva partida a partir de un tablero creado
    * 
    * nuevaPartidaTablero(int idT, boolean aSug, boolean aMarcar, boolean aNext)
    * 
    * idT = idTablero
    * aSug = true si tiene la ayuda de sugerencia activada
    * aMarcar = true si tiene la ayuda de marcar casilla activada
    * aNext = true si tiene la ayuda de poner siguiente nuemro activada
    * 
    */
   public void nuevaPartidaTablero(int idT, boolean aSug, boolean aMarcar, boolean aNext) {
	   ct = ControllerTablero.getInstance(idT);
	   p = new Partida(idUser, 0, ct.getIdKenken(), aSug, aMarcar, aNext);
	   setPuntuacionIniPar(ct.getDificultat());
	}
   
   /*
    * Crea una nueva partida personalizada
    * 
    * nuevaPartidaRapida(int dif, int n, boolean aSug, boolean aMarcar, boolean aNext)
    * 
    * dif = dificultad
    * n = tamaño kenken NxN
    * opciones = tipo de zonas que quiere el usuario
    * aSug = true si tiene la ayuda de sugerencia activada
    * aMarcar = true si tiene la ayuda de marcar casilla activada
    * aNext = true si tiene la ayuda de poner siguiente nuemro activada
    * 
    */
   public void nuevaPartidaPersonalizada(int dif, int n, int opciones, boolean aSug, boolean aMarcar, boolean aNext) {
	   ct = ControllerTablero.getInstance(n, -1, true, dif, opciones, true);
	   p = new Partida(idUser, 0, ct.getIdKenken(), aSug, aMarcar, aNext);
	   setPuntuacionIniPar(dif);
	}
   
   /*
    * Finaliza una partida
    * Si era una partida guardada y no eras anonimo la borra de persistencia
    *  
    *  finPartida(boolean win)
    *  
    *  win = true si ha ganado la partida
    *  
    */
   public  void finPartida(boolean win, String username) {
	   if (idUser != -1) {
		   if (esGuardada){
			   gp = new GestorPartida(idUser, nElementos);
			   gp.borrarPartida(p.getIdTablero());
		   }
		   ec = EstadisticasController.getInstance();
		   ec.actualizarEstadisticas(idUser, win, p.getTime(), p.getAyudaMarcarCasilla(), p.isSugerencias(), 
				   p.getAyudaNextNum(), p.getPuntuacion(), ct.getDificultat(), username);
		   ec = null;
	   }
	   ct.removeKenken(esGuardada);
   }
   
   /*
    * Ejecuta la acción de Poner un numero, incrementa en 1 la ayuda y decrementa la puntuacion segun la dificultad.
    * 
    */
   public int ayudaNextNum(Pair pair) {
	   if(idUser != -1) {
		   p.incAyudaNextNum();
		   p.decAyudaMarcarCasilla();
		   p.incMarPuntuacion(ct.getDificultat());
		   p.incMarPuntuacion(1);
		   p.decResPuntuacion(ct.getDificultat());
		   p.decResPuntuacion(1);
	   }
	   
	   return ct.ayudaNextNum(pair);
   }
   
   /*
    * Ejecuta la acción de Marcar una casilla, incrementa en 1 la ayuda y decrementa la puntuacion segun la dificultad.
    * 
    */
   public Pair ayudaMarcarCasilla(int dataValues[][]) {
	   if(idUser != -1) {
		   p.incAyudaMarcarCasilla();
		   p.decMarPuntuacion(ct.getDificultat());
		   p.decMarPuntuacion(1);
	   }
	   return ct.ayudaMarcarCasilla(dataValues);
   }
   
   /*
    * Si pierde la partida borra el tablero
    * 
    */
   public void gameOver() {
	   ct.removeKenken(esGuardada);
   }
	   
	
   //A partir de aqui esta por implementar (partidas diversas, etc)
   
   ////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////
   
   /*public void nuevaPartidaPersonalizada(int n, int nZonas, ArrayList<String> tipoZonas, int dificultad) {//lo que este definido como decidamos es random
	   gp = new GestorPartida(p.getIdUser());
	   ct = new ControllerTablero(n, idUser);
	   ct.newTablero(int n, int nZonas,  String tipoZonas, dificultad); //crea un tablero total Random
 	   p = new Partida(gp.newPartida(ct.getIdTablero()), dificultad);
	   gp = null;
   }
   
   public void nuevaPartidaTablero() {
	   gp = new GestorPartida();
 	   p = new Partida(gp.newPartida(ct.getIdTablero()), ct.getDificultad);
	   gp = null;
   }
	   
   }*/
   }
