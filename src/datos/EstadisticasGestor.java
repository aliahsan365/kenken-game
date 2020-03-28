/*
 * Jonathan Nebot
 */
package datos;

import java.util.ArrayList;

public class EstadisticasGestor {
	
	private String path;
	private String fac;
	private String nor;
	private String dif;
	private DatosGestor dg;
	
   /* Constructora */
	
	public EstadisticasGestor(int idUser) {
		dg = null;
		fac = "F.txt";
		nor = "N.txt";
		dif = "D.txt";
		path = "datos/"+idUser+"/estadisticas";
	}
	
	public ArrayList<String> cargarEstadisticas(int dificultat, int n) {
		String pth = "";
		switch(dificultat) {
		case 1:
			pth = path + fac;
			break;
		case 2:
			pth = path + nor;
			break;
		case 3:
			pth = path + dif;
			break;
		default:
			//lanzar error
		}
		dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(pth, n);
		dg = null;
		return l;
	}
	   
	   
	public void guardarEstadisticas(int dificultat, ArrayList<String> l, int n) {
		String pth = "";
		switch(dificultat) {
		case 1:
			pth = path + fac;
			break;
		case 2:
			pth = path + nor;
			break;
		case 3:
			pth = path + dif;
			break;
		default:
			//lanzar error
		}
		dg = DatosGestor.getInstance();
		dg.escribirTxt(pth, n, l);
		dg = null;
	}
}
