/*
 * Jonathan Nebot
 */

package domini;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import datos.UserGestor;

public class UserController {
   
	private static UserController instance = null;
	private User u;
	private UserGestor ug;
	private EstadisticasController ec;

   /* Constructora */
   
	protected UserController() {	//Singleton
	   u = null;
	   try {
		   ug = new UserGestor();
	   } catch (FileNotFoundException e) {
		   e.printStackTrace();
	   }
	}
	
   public static UserController getInstance() {
	   if(instance == null) {
		   instance = new UserController();
	   }
	   
	   return instance;
   }
   
   /* Modificadores */
   
   /*
    * True si el usuario no está inicializado
    */
   public boolean isUserNull() {
	   return (u == null);
   }
   
   /*
    * Devuelve la id del usuario activo
    */
   public int getIdUser() {
	   return u.getIdUser();
   }
   
   /*
    * Devuelve el nombre de usuario del usuario activo
    */
   public String getUsername() {
	   return u.getUsername();
   }
   
   /*
    * Devuelve la contraseña del usuario activo
    */
   public String getPassword() {
	   return u.getPassword();
   }
   
   /*
    * Devuelve si tiene activada la ayuda de siguiente numero del usuario activo
    */
   public boolean getANext() {
	   return u.isANext();
   }
   
   /*
    * Devuelve si tiene activada la ayuda de marcar casilla del usuario activo
    */
   public boolean getAMarcar() {
	   return u.isAMarcar();
   }
   
   /*
    * Devuelve si tiene activadas las sugerencias del usuario activo
    */
   public boolean getASug() {
	   return u.isASug();
   }
   
   /*
    * Modifica la contraseña del usuario activo
    */
   public boolean modifyPassword(String oldPass, String newPass) {
	   boolean b = false;
	   if (oldPass.equals(u.getPassword())) {
		   ug.salvarPass(u.getIdUser(), newPass);
		   u.setPassword(newPass);
		  b = true;
	   } 
	   return b;
   }
   
   /*
    * Dice si existe o no un usuario
    */
   public boolean existUser(String username) {
	   return ug.existUser(username);
   }
   
   /*
    * Crea un nuevo usuario en el sistema si no existe
    */
   public int createUser(String user, String pass) {
	   int res = -5;//error desconocido
	   if (ug.isUser(user, pass) == -4) {
		   try {
			   int id = ug.newUser(user, pass);
			   res = id;
			   carregarUser(id, user, pass);
			   ec = EstadisticasController.getInstance();
			   ec.crearEstadisticas(id);
			   ec = null;
		   } catch (FileNotFoundException e) {
				   e.printStackTrace();
			   } 
		   } else {
			   res = -1;//ya existe el usuario
		   }
	   if (u == null)
		   res = -2; //No se ha podido crear el usuario
	   return res;
   }
   
   /*
    * borra el usuario activo de la persistencia
    */
   public boolean borrarUser() {
	   ug.borrarUser(u.getIdUser());
	   return true;
   }
   
   /*
    * Si existe el usuario y coincide la contraseña devuelve la id del mismo y lo carga en memoria principal
    * 
    * errores:
    * -1 -> no coincide la contraseña
    * -4 -> no existe el usuario
    * -5 -> error desconocido
    */
   public int login(String user, String pass) {
	   int id = ug.isUser(user, pass);
	   if (id > 0) {
 			carregarUser(id, user, pass);
 		} else if (id != -4 && id != -1) {
 			id = -5;
 		}
	   return id;
   }
   
   /*
    * Devuelve las ayudas del usuario activo
    */
   public boolean[] getAyudas() {
	   return u.getAyudas();
   }
   
   /*
    * Actualiza las ayudas activas del usuario y las guarda en persistencia
    */
   public void setAyudas(boolean sug, boolean marcar, boolean next) {
 	   u.setASug(sug);
	   u.setAMarcar(marcar);
	   u.setANext(next);
	   guardarUserAyudas();
   }
   
   /*
    * Guarda las ayudas en el fichero txt
    */
   private void guardarUserAyudas() {
	   boolean[] ayu = u.getAyudas();
	   ug.guardarUserAyudas(u.getIdUser(), ayu[0], ayu[1], ayu[2]);
   }
   
   /*
    * Carga el usuario de la base de datos en la memoria
    */
    private void carregarUser(int id, String user, String pass) {
    	  ArrayList<String> l = ug.getUser(id);
    	  boolean sug, marcar, next;
    	  sug = Boolean.parseBoolean(l.get(3));
    	  marcar = Boolean.parseBoolean(l.get(4));
    	  next = Boolean.parseBoolean(l.get(5));
		  this.u = new User(id, user, pass, sug, marcar, next);
   }
    
    
} 
