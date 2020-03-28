/*
 * Jonathan Nebot
 */

package datos;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GestorPartida {
	private DatosGestor dg;
	private String path;
	private int nElementos;
	
	public GestorPartida(int idUser, int n) {
		   path = "datos/"+idUser+"/partidas.txt";
		   nElementos = n;
		   dg = null;
	   }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	/*
	private int idPartida;
	private long initTime;
	private long finishTime;
	private int dificultad;
	private int puntuacion;
	private boolean resuelto;
	private int idTablero; 
	private int fallos; 
	private int aciertos;
	private int ayudaNextNum;
	private boolean esAyudaNextNum;
	private boolean sugerencias;
	private boolean esAyudaMarcarCasilla;
	private int ayudaMarcarCasilla;
	private long timeInc;
	*/
	
	public void newPartida(ArrayList<String> p) throws FileNotFoundException {
			if (existPartida(p.get(0))) {
				borrarPartida(Integer.parseInt(p.get(0)));
			}
		    dg = DatosGestor.getInstance();
			ArrayList<String> l = dg.leerTxt(path, nElementos);
			for (int i = 0; i < p.size(); ++i) {
				l.add(p.get(i));
			}
			dg.escribirTxt(path, nElementos, l);
			dg = null;
	   }
	
	private boolean existPartida(String id) {
		dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, nElementos);
		for (int i = 0; i < l.size(); i+=nElementos) {
			if (id.equals(l.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void borrarPartida(int idT) {
		dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, nElementos);
		boolean trobat = false;
		int i = 0;
		while (i < l.size() && !trobat) {
			if(idT == Integer.parseInt(l.get(i))) {
				for (int j = nElementos-1; j >= 0; --j) {
					l.remove(i+j);
				}
				trobat = true;
			}
			i+=nElementos;
		}
		dg.escribirTxt(path, nElementos, l);
		dg = null;
	}
	
	public ArrayList<Integer> cargarListaPartidas() {
		ArrayList<Integer> v = new ArrayList<Integer>();
		dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, nElementos);
		for (int i = 0; i < l.size(); i+=nElementos) {
			v.add(Integer.parseInt(l.get(i)));
		}
		return v;
	}

	public ArrayList<String> cargarPartida(int idTablero) {
		dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path,nElementos);
		ArrayList<String> v = new ArrayList<String>();
		for (int i = 0; i < l.size(); i+=nElementos) {
			if (idTablero == Integer.parseInt(l.get(i))) {
				for (int j = 0; j < nElementos; ++j) {
					v.add(l.get(i+j));
				}
			}
		}
		dg = null;
		return v;
	}
}
