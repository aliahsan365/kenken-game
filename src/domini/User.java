/*
 * Jonathan Nebot
 */

package domini;

public class User {
   
   private int idUser;
   private String username;
   private String password;
   private boolean aSug;
   private boolean aMarcar;
   private boolean aNext;

   /* Constructora */
   
   public User() {
	   this.idUser = -1;
	   this.username = "anonimo";
	   this.password = null;
	   this.aSug = true;
	   this.aMarcar = true;
	   this.aNext = true;
   }
   
   public User(int id, String username, String password, boolean sug, boolean marcar, boolean next) {
      this.idUser = id;
	  this.username = username;
      this.password = password;
      this.aSug = sug;
      this.aMarcar = marcar;
      this.aNext = next;
   }
   
   /* Modificadores */
   
   void setIdUser(int id) {
		  this.idUser = id;
	   }
   
   void setUsername(String user) {
	  this.username = user;
   }
   
   public void setPassword(String pass) {
      this.password = pass;
   }
   
   public void setAMarcar(boolean aMarcar) {
		this.aMarcar = aMarcar;
	}

	public void setANext(boolean aNext) {
		this.aNext = aNext;
	}
   
   /* Consultoras */

   public int getIdUser() {
	   return this.idUser;
   }
   
   public String getUsername() {
      return this.username;
   }
   
   public String getPassword() {
	  return this.password;
   }

	public boolean isASug() {
		return aSug;
	}
	
	public void setASug(boolean aSug) {
		this.aSug = aSug;
	}
	
	public boolean isAMarcar() {
		return aMarcar;
	}
	
	public boolean isANext() {
		return aNext;
	}

	public boolean[] getAyudas() {
		boolean[] ayu = new boolean[3];
		ayu[0] = isASug();
		ayu[1] = isAMarcar();
		ayu[2] = isANext();
		return ayu;
	}
} 
