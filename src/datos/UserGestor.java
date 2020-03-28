/*
 * Jonathan Nebot
 */

package datos;

import java.io.FileNotFoundException;
import java.util.*;


public class UserGestor {
	private DatosGestor dg;
	private String path;
	
   /* Constructora */
	
   public UserGestor() throws FileNotFoundException {
	   setPath("datos/users.txt");
	   dg = null;
   }
   
   /* Modificadores */
   
   public int newUser(String user, String pass) throws FileNotFoundException {
	    dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, 6);
		int id = Integer.parseInt(l.get(l.size()-6));
		id++;
		l.add(Integer.toString(id));
		l.add(user);
		l.add(pass);
		l.add("true");
		l.add("true");
		l.add("true");
		dg.escribirTxt(path, 6, l);
		String p = "datos";
		String pid = ""+id;
		dg.crearDirectorio(p, pid);
		pid = "/"+pid+"/";
		String addp = pid+"estadisticasF.txt";
		dg.crearArchivo(p, addp);
		addp = pid+"estadisticasN.txt";
		dg.crearArchivo(p, addp);
		addp = pid+"estadisticasD.txt";
		dg.crearArchivo(p, addp);
		addp = pid+"partidas.txt";
		dg.crearArchivo(p, addp);
		dg = null;
	    return id;
   }
   
   int lastId() {
	   dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, 6);
		int id = Integer.parseInt(l.get(l.size()-6));
		dg = null;
	    return id;
   }

   public void salvarPass(int id, String pass) {
	   dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTxt(path, 6);
		for(int i = 0; i < l.size(); i+= 6) {
			if(Integer.parseInt(l.get(i)) == id) {
				l.set(i+2, pass);
			}
		}
		dg.escribirTxt(path, 6, l);
		dg = null;
   }
   
   public int isUser(String user, String pass) {
	   	dg = DatosGestor.getInstance();
	   	ArrayList<String> l = dg.leerTxt(path, 6);
		int i;
		for(i = 0; i < l.size(); i+= 6) {
			if(l.get(i+1).equals(user)) {
				if(l.get(i+2).equals(pass)) {
					return Integer.parseInt(l.get(i));
				} else {
					return -1;
				}
			}
		}
		dg = null;
		if (i == l.size())
			   return -4;
		return -2;
   }

public boolean existUser(String username) {
	boolean b = false;
	dg = DatosGestor.getInstance();
	ArrayList<String> l = dg.leerTxt(path, 6);
	for(int i = 0; i < l.size(); i+= 6)
		if(username.equals(l.get(i+1))) b =  true;
	dg = null;
	return b;
}




public void borrarUser(int idUser) {
	dg = DatosGestor.getInstance();
	ArrayList<String> l = dg.leerTxt(path, 6);
	for(int i = 0; i < l.size(); i+= 6) {
		if(Integer.parseInt(l.get(i)) == idUser) {
			l.remove(i+5);
			l.remove(i+4);
			l.remove(i+3);
			l.remove(i+2);
			l.remove(i+1);
			l.remove(i);
		}
	}
	dg.escribirTxt(path, 6, l);
	String p = "datos";
	String pid = "/"+idUser+"/";
	String addp = pid+"estadisticasF.txt";
	dg.borrarDirectorio(p, addp);
	addp = pid+"estadisticasN.txt";
	dg.borrarDirectorio(p, addp);
	addp = pid+"estadisticasD.txt";
	dg.borrarDirectorio(p, addp);
	addp = pid+"paridas.txt";
	dg.borrarDirectorio(p, addp);
	dg.borrarDirectorio(p, pid);
	dg = null;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public void listarUsersDB() {
	dg = DatosGestor.getInstance();
	ArrayList<String> l = dg.leerTxt(path, 3);
	for(int i = 0; i < l.size(); i+= 3) {
		System.out.print("idUser: ");
		System.out.println(l.get(i));
		System.out.print("username: ");
		System.out.println(l.get(i+1));
		System.out.print("pass: ");
		System.out.println(l.get(i+2));
	}
	System.out.println("-----------------------");
}

public ArrayList<String> getUser(int id) {
	dg = DatosGestor.getInstance();
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> l = dg.leerTxt(path, 6);
	int i = 0;
	boolean trobat = false;
	while( i < l.size() && !trobat) {
		if(Integer.parseInt(l.get(i)) == id) {
			for (int j = i; j < i+6; ++j)
				list.add(l.get(j));
			trobat = true;
		}
		i+= 6;
	}
	dg = null;
	return list;
}

public void guardarUserAyudas(int idUser, boolean sug, boolean marcar, boolean next) {
	dg = DatosGestor.getInstance();
	ArrayList<String> l = dg.leerTxt(path, 6);
	boolean trobat = false;
	for (int i = 0; i < l.size() && !trobat; i+=6){
		if(Integer.parseInt(l.get(i)) == idUser) {
			trobat = true;
			l.set(i+3, Boolean.toString(sug));
			l.set(i+4, Boolean.toString(marcar));
			l.set(i+5, Boolean.toString(next));
			dg.escribirTxt(path, 6, l);
		}
	}
	dg = null;
}
} 