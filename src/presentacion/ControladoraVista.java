package presentacion;


//ALI MUHAMMAD 

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import domini.ControladorPartida;
import domini.ControllerConjuntoTableros;
import domini.ControllerTablero;
import domini.EstadisticasController;
import domini.MyOwnException;
import domini.Pair;
import domini.RankingController;
import domini.UserController;
import domini.Utilities;



public class ControladoraVista {
	
	private ControladorPartida cP;
	private ControllerTablero cT;
	private UserController cU;
	private RankingController rC;
	private EstadisticasController eC;
	private ControllerConjuntoTableros cCT;
	private String nombreUserActual;
	private int dificultad;
	private static Utilities u = new Utilities();


	public ControladoraVista() {
		cU = UserController.getInstance();
		rC = RankingController.getInstance();
	    this.vistaInicio();
	}
	
	
	
	//------------------------VISTAS--------------------------------------------------------------------------
	//------------------------VISTAS--------------------------------------------------------------------------
	
	
	
	//Definitivo
	public void vistaInicio() {
		ventanaInicio vInicio = new ventanaInicio(this);
		vInicio.setVisible(true);
		vInicio.setLocationRelativeTo(null);
		}
	
	//Definitivo
	public void vistaLogin() {
		ventanaLogin vLogin = new ventanaLogin(this);
		vLogin.setVisible(true);
		vLogin.setLocationRelativeTo(null);
		}
	
	//Definitivo?
	public void vistaOpcJuegosAnonim() {
		ventanaOpcJuegosAnonim vOpcJuegosAnonim = new ventanaOpcJuegosAnonim(this);
		vOpcJuegosAnonim.setVisible(true);
		vOpcJuegosAnonim.setLocationRelativeTo(null);
		cP = ControladorPartida.getInstance();
		}
	
	//Definitivo
	public void vistaRegistro() {
		ventanaRegistro vRegistro = new ventanaRegistro(this);
		vRegistro.setVisible(true);
		vRegistro.setLocationRelativeTo(null);
    	}
	
	//Definitivo
	public void vistasAyudas() {
		ventanaAyudas vAyudas = new ventanaAyudas(this);
		vAyudas.setVisible(true);
		vAyudas.setLocationRelativeTo(null);
		}
	
	//Definitivo
	public void vistaOpcJugarPartidaAnonimo() {
		ventanaOpcPartidasAnonim vistaOpcJuegoAnonim = new ventanaOpcPartidasAnonim(this);
	    vistaOpcJuegoAnonim.setVisible(true);
		vistaOpcJuegoAnonim.setLocationRelativeTo(null);
		}
	
	//Definitivo
	public void vistaRanking(int n) {
		ventanaRanking vistaRanking = new ventanaRanking(this,n);
		vistaRanking.setVisible(true);
	    }
	
	
	
	
	//Definitivo?
	public void vistaEstadisticas() {
		ventanaEstadisticas vistaEst = new ventanaEstadisticas(this);
		vistaEst.setVisible(true);
		}
	
	
	//Defintiivo?
	public void vistaEstadisticaUsuario(int dif) {
		ventanaEstadisticaUsuario vistaEstUser = new ventanaEstadisticaUsuario(this, eC.getEstadisticasPresentacion(cU.getIdUser(), dif));
		vistaEstUser.setVisible(true);
		}
	
	
	//Definitivo
	public void vistaBorrarCuenta() {
		ventanaBorrarUser vistaBorrarUser = new ventanaBorrarUser(this);
		vistaBorrarUser.setNombre(nombreUserActual);
		vistaBorrarUser.setVisible(true);
		}
	
	//Definitivo
	public void  vistaModificarPassword() {
		ventanaCmbPassword vistaCmbPassword = new ventanaCmbPassword(this);
		vistaCmbPassword.setNombre(nombreUserActual);
		vistaCmbPassword.setVisible(true);
		}
	
	//Definitivo
	public void vistaOpcUsuario() {
		ventanaOpcUsuario vistaOpsUser = new ventanaOpcUsuario(this);
		vistaOpsUser.setVisible(true);
		}
	
	//Definitivo
	public void vistaOpcionesJuegos() {
		ventanaOpcJuegos vistaOpcJuegos = new ventanaOpcJuegos(this);
		vistaOpcJuegos.setNom(nombreUserActual);
		vistaOpcJuegos.setVisible(true);
		}	
	
	//Definitivo
	public void vistaOpcionesPartida() {
		ventanaOpcPartidas vistaOpcPartidas = new  ventanaOpcPartidas(this);
		vistaOpcPartidas.setVisible(true);
	 	}
	
	//Definitivo?
	public void vistaOpcJugarPartida() {
		if(cU.isUserNull()) vistaOpcJugarPartidaAnonimo();
		else vistaOpcionesPartida();
		}
	
	//Definitivo?
	public void vistaNKenkenPersonal() {
		ventanaPersonalN vistaN = new ventanaPersonalN(this);
		vistaN.setVisible(true);
 		}
	
	
	//Definitivo?
	public void vistaCustomizado(int n) {
		CustomGame vistaCustom = new CustomGame(this,n);
		vistaCustom.setVisible(true);
 	}
	
	//Definitivo?
	public void vistaCargarListaP() {
		ArrayList<String> l = new ArrayList<String>();
		l = cP.cargarListaPartidas();
		
		
			ventanaCargarListaP vistaCargarListaP = new ventanaCargarListaP(this,l);
			vistaCargarListaP.setVisible(true);
		
		
	}
	
	//Definitivo?
	public void vistaPersonal() {
		 ventanaPersonal vistaPersonal = new ventanaPersonal(this);
		 vistaPersonal.setVisible(true);
	 	}
	
	//Definitivo?
	public void vistasAyudasUsuario() {
		 ventanaAyudasUsuario vistaAyudasUser = new  ventanaAyudasUsuario(this);
		 vistaAyudasUser.setVisible(true);
	 	}
	
	//Definitivo?
	public void vistaPartidaRepositorio() throws MyOwnException {
			//ArrayList<String> l = new ArrayList<String>();
			//l = this.consultarTableros();
			ventanaPartidaRepositorio vistaPartidaRepo = new ventanaPartidaRepositorio(this);
			vistaPartidaRepo.setVisible(true);
		}
	

  
	
	
     public void vistaGuardadoPartida(int dataValue[][]) {
    	 ventanaGuardado ventG = new ventanaGuardado(this,dataValue);
    	 ventG.setVisible(true);
	 }
    	 
     public void vistaGuardadoTablero() {
    	 ventanaGuardadoTablero ventGT = new ventanaGuardadoTablero(this);
    	 ventGT.setVisible(true);
     }
    	 
	
	
	
	
	//Definitivo
		public void salirDelJuego() {
			System.exit(0);
			}
	
	//---------------------CONSULTORAS-------------------------------------------------------------------------
	//---------------------CONSULTORAS----------------------------------------------------------------------	
	
		
		
		public boolean isUserNull() {
			return cU.isUserNull();
		}
		//Definitivo
		public String getNombreUserActual() {
			return this.nombreUserActual;
			}
		//Definitivo
		public void setNombreUserActual(String nombreUserActual ) {
			this.nombreUserActual = nombreUserActual;
			}
		//Definitivo
		public int[][] getResultado() {
			return cP.getResultado();
		}  
	    
	
		
		//Definitivo
		public Pair ayudaMarcarCasilla(int dataValue[][]) {
			return cP.ayudaMarcarCasilla(dataValue);
		}
		
		//Definitivo
	    public int ayudaNextNum(Pair p ) {
	    	return cP.ayudaNextNum(p);
	    }
	
	  //Definitivo
		public boolean[] getAyudas() {
			return cU.getAyudas();
			}
		
		//Definitivo
		public boolean resolverKenken(int m[][]) {
			return cP.resolverKenken(m);
		}
		
		public boolean resolverKenken(int n, int dataValues[][], int dataZone[][], int dataDefault[][], ArrayList<Character> iOp) {
			
			cT = ControllerTablero.getInstance(n, cU.getIdUser(), dataValues, dataZone, dataDefault, iOp, true);
			
			return (cT.getSizeTablero()>0);
		}
		
		//Definitivo
		public ArrayList<String> consultarTableros() throws MyOwnException {
			//esta llamara a consultar tableros  para obtener la lista de tableros.
			cCT = ControllerConjuntoTableros.getInstance();
			cU =  UserController.getInstance();
			ArrayList<String> l = new ArrayList<String>();
			
			l = cCT.getListNumOwnTableros(cU.getIdUser());
		
			return l;
		}
	
	//---------------------SETTERS-------------------------------------------------------------------------
	//---------------------SETTERS----------------------------------------------------------------------	
		public void setAyudas(boolean sug, boolean marcar, boolean next) {
			cU.setAyudas(sug, marcar, next);
			}
	
		
		
	//---------------------LINKADOS--------------------------------------------------------------------------
	//---------------------LINKADOS--------------------------------------------------------------------------
	
	
	//Definitivo
	public boolean loguearse(String nombre , String Pass) {		
		boolean cerrar = true;
		int id = cU.login(nombre,Pass);
		if (id == -1) { JOptionPane.showMessageDialog(null, "Password Incorrecto"); cerrar = false;}
		else  if (id == -2) {JOptionPane.showMessageDialog(null, "Usuario Incorrecto"); cerrar = false;}
		else if (id == -5){JOptionPane.showMessageDialog(null, "Error Desconocido"); cerrar = false;}
		else if ( id == -4) {JOptionPane.showMessageDialog(null, "No exisite el usuario"); cerrar = false;}
		else {
			JOptionPane.showMessageDialog(null, "Login Correcto");
			nombreUserActual = nombre;
			ventanaOpcJuegos vOpcJuegos = new ventanaOpcJuegos(this);
	        vOpcJuegos.setNom(nombreUserActual);
			vOpcJuegos.setVisible(true);
	    	vOpcJuegos.setLocationRelativeTo(null);
	    	cP = ControladorPartida.getInstance(cU.getIdUser());
	    	eC = EstadisticasController.getInstance();
		}
		return cerrar;
	}
	
	
	  //Definitivo
		public boolean borrarCuenta() {
			if(cU.borrarUser()) {this.vistaInicio(); return true;}
			else {return false;}
			} 
	
	//Definitivo
	public boolean crearUserNuevo(String nombre, String pass) {		
		boolean cerrar = true;
		int id = cU.createUser(nombre,pass);
	    if (id == -5){JOptionPane.showMessageDialog(null, "Error desconocido"); cerrar = false;}
	    else if (id == -2) {JOptionPane.showMessageDialog(null, "No se ha podido crear el usuario"); cerrar = false;}
	    else if (id == -1) {JOptionPane.showMessageDialog(null,"Ya existe este usuario"); cerrar = false;}
	    else if (id == -4) {JOptionPane.showMessageDialog(null,"No existe el usuario"); cerrar = false;}
	    else {
	    	JOptionPane.showMessageDialog(null, "Cuenta creada correctamente");
	    	this.vistaInicio();
	    	cerrar = true;
	    	}
	    return cerrar;
	    }
	
	
	//Defintiva ???
	public int getDificultad() {
		return 1;
	}
	
	
	public void generarPartidaRapidaAnonimo() {
		int n = u.newRandom(25)+1;
		cP.nuevaPartidaRapida(0, n, true, true, true);
		ventanaGame vg = new ventanaGame(n,this,cP.getOperaciones(),cP.getDataZone(),cP.getDataDefault());
	}
	
	
    public void generarPartidaPersonalizada(int n, int dif, int options) {
		if(cU.isUserNull()) cP.nuevaPartidaPersonalizada(dif, n, options, true, true, true);
		else cP.nuevaPartidaPersonalizada(dif, n, options, cU.getASug(), cU.getAMarcar(), cU.getANext());
		ventanaGame vg = new ventanaGame(n,this,cP.getOperaciones(),cP.getDataZone(),cP.getDataDefault());
		}
		
	public void  generarPartidas(int tipo)  {
		if (tipo == 1) {
			int n = u.newRandom(25)+1;
			cP.nuevaPartidaRapida(0, n, cU.getASug(), cU.getAMarcar(), cU.getANext());
			ventanaGame vg = new ventanaGame(n,this,cP.getOperaciones(),cP.getDataZone(),cP.getDataDefault());
		}
		else if (tipo == 2)   {
			ventanaPersonal vistaPersonal = new ventanaPersonal(this);
			vistaPersonal.setVisible(true);
		}
		else if (tipo == 3) {
			//jugarDelrepo
		}
		
		else if (tipo == 4) {
			//contrareloj
		}
		else {
			//mode superviviencia 
		}
	}
	
	
	//Definitivo
	public boolean modificarPassword(String oldPass , String newPass ) {
		if (cU.modifyPassword(oldPass,newPass)) {
			this.vistaLogin();
			return true;
			}
		else  {
			JOptionPane.showMessageDialog(null, "el password Antiguo no es correcto");
			return false;
			}
		}
	
	//Definitivo
	public void finPartida(boolean win) {
		if(cU.isUserNull()) {
			cP.finPartida(win,"");
			this.vistaOpcJuegosAnonim();
			}
		else {
			cP.finPartida(win, cU.getUsername());
			this.vistaOpcionesJuegos();
			}
		}
	
	//Definitivo
	public void cargarPartidaUser(int id) {
		cP = ControladorPartida.getInstance(cU.getIdUser());
		cP.cargarPartida(id);
		ventanaGame vg = new ventanaGame(cP.getDataDefault().length, this, cP.getOperaciones(), cP.getDataZone(), cP.getDataDefault(), cP.getDataValues());
	}
	
	
	
	
	//Definitivo
	public ArrayList<String> consultarRankingVista() {
		ArrayList<String> l = new ArrayList<String>();
		l = rC.getTop();
		return l;
	
	}
	
	
	
	//Definitivo
	public void cargarPartidaRepo(int idTab) {
		// aqui es donde hay q lanzar la partida con este tablero !
		System.out.println("antes de petar entro 4 ");
		cP.nuevaPartidaTablero(idTab, cU.getASug(), cU.getAMarcar(), cU.getANext());
		ventanaGame vg = new ventanaGame(cP.getDataDefault().length, this, cP.getOperaciones(), cP.getDataZone(), cP.getDataDefault());
	}
	
	
	//Especificacion : guardar partida .
	public void guardarPartida(String nombrePartida, int dataValue[][]) {
		try {
			cP.guardarPartida(nombrePartida, dataValue);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void guardarTablero(String nombreTablero) {
		cT.setKenkenName(nombreTablero);
		cT.addToConjunto();
	}
	
	
}
