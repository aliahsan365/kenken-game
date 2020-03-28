/*
 * Jonathan Nebot
 */

package domini;

import java.util.ArrayList;

import datos.EstadisticasGestor;

public class EstadisticasController {
	private static EstadisticasController instance = null;
	private Estadisticas e;
	private EstadisticasGestor eg;
	
	/*
	 * Creadora vacia de EstadisticasController 
	 */
	protected EstadisticasController() {
		e = null;
		eg = null;
	}
	
	public static EstadisticasController getInstance() {
		if(instance == null) {
			instance = new EstadisticasController();
		}
		
		return instance;
	}
	
	/*
	 * Actualiza las estadisticas del usuario "idUser"
	 */
	public void actualizarEstadisticas(int idUser, boolean win, long time, int setCasillaHelpCont, boolean sugerenciaHelpBool, int setSolucionHelpCont, int puntuacion, int dificultad, String username) {
		cargarEstadisticas(dificultad, idUser);
		setEstadisticas(win, time, setCasillaHelpCont, sugerenciaHelpBool, setSolucionHelpCont, puntuacion, dificultad, username);
		guardarEstadisticas(dificultad, idUser);
	}
	
	/*
	 * Envia las nuevas estadisticas para que se actualizen
	 */
	private void setEstadisticas(boolean win, long time, int setCasillaHelpCont, boolean sugerenciaHelpBool, int setSolucionHelpCont, int puntuacion, int dificultad, String username) {
			
			e.calculateGames(win);
			
			if(win) {
				e.calculateTimes(time);
				e.calculateHelps(setCasillaHelpCont,sugerenciaHelpBool,setSolucionHelpCont);
				e.calculatePuntuacion(puntuacion, dificultad, setCasillaHelpCont, sugerenciaHelpBool, setSolucionHelpCont, username);
			}
		}
		
	/*
	 * Guarda las estadisticas del usuario "idUser" i la dificultad "dif"
	 */
	private void guardarEstadisticas(int dif, int idUser) {
		eg = new EstadisticasGestor(idUser);
		eg.guardarEstadisticas(dif, e.datosEstadisticasGestor(), e.getnElementos());
		eg = null;
	}
	
	/*
	 *  Carga las estadisticas del usuario "idUser" i la dificultad "dif"
	 */
	private void cargarEstadisticas(int dif, int idUser) {
		eg = new EstadisticasGestor(idUser);
		e = new Estadisticas(eg.cargarEstadisticas(dif, e.getnElementos()));
		eg = null;
	}
		
	/*
	 * Crea la estructura de las estadisticas para el usuario "idUser"
	 */
	public void crearEstadisticas(int idUser) {
		e = new Estadisticas();
		eg = new EstadisticasGestor(idUser);
		guardarEstadisticas(1, idUser);
		guardarEstadisticas(2, idUser);
		guardarEstadisticas(3, idUser);
		eg = null;
	}
	
	/*
	 * Devuelve las estadisticas del usuario "idUser" i la dificultad "dif"
	 */
	public ArrayList<String> getEstadisticasPresentacion(int idUser, int dif) {
		eg = new EstadisticasGestor(idUser);
		e = new Estadisticas(eg.cargarEstadisticas(dif, e.getnElementos()));
		eg = null;
		return e.datosEstadisticasPresentacion();
	}
}
