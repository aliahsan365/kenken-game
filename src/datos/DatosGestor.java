/*
 * Jonathan Nebot
 */

package datos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class DatosGestor {
	private static DatosGestor instance = null;
	private Scanner l;
	private Formatter e;
	private ArrayList<String> v;
	
	/*
	 * Creadora vacia de DatosGestor
	 */
	public static DatosGestor getInstance() {
		   if(instance == null) {
			   instance = new DatosGestor();
		   }
		   
		   return instance;
	   }
	
	protected DatosGestor()  {
		   v = null;
		   l = null;
		   e = null;
	   }
	
	/*
	 * Crea un directorio en p/addp
	 */
	public void crearDirectorio(String p, String addp) {
		String path = ClassLoader.getSystemResource(p).toString();
		path = path +"/"+addp;
		String pathaux = (String) path.subSequence(6, path.length());
		new File(pathaux).mkdir();
	}
	
	/*
	 * Crea un archivo en p/addp
	 */
	public void crearArchivo(String p, String addp) {
		String path = ClassLoader.getSystemResource(p).toString();
		path = path +"/"+addp;
		String pathaux = (String) path.subSequence(6, path.length());
		File f = new File(pathaux);
		try {
			FileWriter esc = new FileWriter(f,true);
			esc.write("");
			esc.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * Borra el archivo o directorio en p/addp
	 */
	public void borrarDirectorio(String p, String addp) {
		String path = ClassLoader.getSystemResource(p).toString();
		path = path +"/"+addp;
		String pathaux = (String) path.subSequence(6, path.length());
		new File(pathaux).delete();
	}
	
	/*
	 * Lee todo lo que haya en el txt
	 */
	public ArrayList<String> leerTodo(String path) {
		   abrirTxtL(path);
		   v = new ArrayList<String>();
		   while(l.hasNext()) {
			   v.add((String) l.next());
		   }
		   cerrarTxtL();
		   return v;
	}
	
	/*
	 * Lee del txt por filas de tamaño n
	 */
	public ArrayList<String> leerTxt(String path, int n) {
		   abrirTxtL(path);
		   v = new ArrayList<String>();
		   while(l.hasNext()) {
			 for (int i = 0; i < n; ++i) {
				 v.add((String) l.next());
			 }
		   }
		   cerrarTxtL();
		   return v;
	}
   /*
    *  Guarda en el txt el contenido de list por filas de tamaño n 
    */
   public void escribirTxt(String path, int n, ArrayList<String> list)  {
	   abrirTxtE(path);
	   for (int i = 0; i < list.size(); i+= n) {
		   for (int j = 0 ; j < n; ++j) {
			   e.format(list.get(i+j) + " ");
		   }
		   e.format("%n");
	   }
	   cerrarTxtE();
   }
	   
	/*
	 * Guarda en el txt todo el contenido de list    
	 */
   public void escribirTodo(String path, ArrayList<String> list)  {
	   abrirTxtE(path);
	   for (int i = 0; i < list.size(); i++) {
		   e.format(list.get(i) + " ");
	   }
	   cerrarTxtE();
   }
   
   /*
    * Abre el txt para escritura
    */
  private void abrirTxtE(String path) {
	   try {
		   	String pth = "../" + path;
			URL resourceUrl = getClass().getResource(pth);
			File file = new File(resourceUrl.toURI());
			OutputStream output = new FileOutputStream(file);
			e = new Formatter(output);
		
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
   }
  
  /*
   * Abre el txt para lectura
   */
  private void abrirTxtL(String path) {
	   try {
		   l = new Scanner(ClassLoader.getSystemResourceAsStream(path));
	   } catch (Exception e) {
			e.printStackTrace();
	}
   }
   
  /*
   * Cierra el txt de escritura
   */
  private void cerrarTxtE() {
	   e.close();
   }
  
  /*
   * Cierra el txt de lectura
   */
  private void cerrarTxtL() {
	   l.close();
   }
}
