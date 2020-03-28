package domini;


import java.util.ArrayList;
import java.util.ListIterator;;

public class ControllerTablero{
	private static ControllerTablero instance = null;
	private TaulellKenken tk;
	private ControllerConjuntoTableros ccjt;
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	/*Constructoras*/
	
	protected ControllerTablero(int tamany, int id_user, int id_kenken, int imat[][], int izone[][], int idefault[][], ArrayList<Character> iop){
		this.tk = new TaulellKenken(tamany, id_user, id_kenken, imat,  izone, idefault, iop);
		ccjt = ControllerConjuntoTableros.getInstance();
		setIdKenken();
	}
	
	protected ControllerTablero(int tam, int id_user, boolean auto, int diff, int opt) {
		this.tk = new TaulellKenken(tam,id_user, 0, auto, diff, opt);
		ccjt = ControllerConjuntoTableros.getInstance();
		setIdKenken();
	}
	
	protected ControllerTablero(int tam, int id_user, int diff) {
		this.tk = new TaulellKenken(tam, id_user,0,true, diff, 0);
		ccjt = ControllerConjuntoTableros.getInstance();
		setIdKenken();
	}
	
	protected ControllerTablero(int idTablero){
		ccjt = ControllerConjuntoTableros.getInstance();
		try {
			this.tk = new TaulellKenken();
			this.tk.copyTaulell(ccjt.ObtenerTablero(idTablero));			
			this.tk.setIdUser(-1);
			setIdKenken();
		}
		catch(MyOwnException e){}
	}
	
	protected ControllerTablero(){
		this.tk = new TaulellKenken();
		ccjt = ControllerConjuntoTableros.getInstance();

	}
	public static ControllerTablero getInstance(int tamany, int id_user, int imat[][], int izone[][], int idefault[][], ArrayList<Character> iop, boolean nuevo){
		if(instance == null || nuevo) {
			instance = new ControllerTablero(tamany,id_user, 0, imat, izone, idefault,iop);
		}
		
		return instance;
	}
	
	public static ControllerTablero getInstance(int tam, int id_user, boolean auto, int diff, int opt, boolean nuevo) {
		if(instance == null || nuevo) {
			instance = new ControllerTablero(tam, id_user, auto, diff, opt);
		}
		
		return instance;
	}
	
	public static ControllerTablero getInstance(int tam, int id_user, int diff, boolean nuevo) {
		if(instance == null || nuevo) {
			instance = new ControllerTablero(tam, id_user, diff);
		}
		
		return instance;
	}
	
	public static ControllerTablero getInstance(int idTablero){
		if(instance == null) {
			instance = new ControllerTablero(idTablero);
		}
		
		return instance;
	}
	
	public static ControllerTablero getInstance(){
		if(instance == null) {
			instance = new ControllerTablero();
		}
		
		return instance;
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	
	/*Modificadoras*/
	
	
	public int ayudaNextNum(Pair pair) {
		   return tk.getResultCasilla(pair);
	   }
	
	 public Pair ayudaMarcarCasilla(int dataValues[][]) {
		   return tk.sugerenciaKenken(dataValues);
	   }
	
	
	public void setTablero(int iTK){

		try{
			this.tk = ccjt.ObtenerTablero(iTK);
		}
		catch(MyOwnException e){ 
			
		}
		
		
	}
	public void setCasilla(CasillaKenken ck){
		this.tk.setCasillaKenken(ck, ck.getFila(), ck.getColumna());
	}
	public void modificarValueCasillaTablero(Pair pos, int nwVal) throws MyOwnException{
		int row = pos.getFila();
		int col = pos.getColumna();
		if(this.tk.getIfEditable(row, col)){
			this.tk.modifyCurrentValueCasilla(row, col, nwVal);
		}
		else throw new MyOwnException ("No se puede modificar casilla con valor por defecto");
	}
	public void setDefaultValue(Pair pos, int nwVal){
		int row = pos.getFila();
		int col = pos.getColumna();
		this.tk.setDefaultCasillaKenken(row, col, nwVal);
	}
	
	public void setKenkenName(String s){
		this.tk.setName(s);
	}
	
	private void setIdKenken(){
		this.tk.setIdTablero(this.ccjt.calculateIdKenken());
	}
	
	public void setZonasCasillas(int[][] z){
		int n = z.length;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				this.tk.getCasillaK(i, j).setIndexZona(z[i][j]);;
				/*CasillaKenken ck = this.tk.getCasillaK(i, j);
				ck.setIndexZona(z[i][j]); 
				this.tk.setCasillaKenken(ck, i, j);*/
			}
		}
		
	}
	
	public void setDefaults(int[][] d){
		int n = d.length;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				this.tk.setDefaultCasillaKenken(i, j, d[i][j]);
			}
		}
	}
	
	public void setZonasTablero(ArrayList<String> s){
		ListIterator<String> it = s.listIterator();
		while(it.hasNext()){
			this.tk.addZona(it.next().charAt(0), Integer.parseInt(it.next()));
		}
		
	}
	public void setCurrentValues(int[][] cv){
		int n = cv.length;
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				this.tk.modifyCurrentValueCasilla(i, j,cv[i][j]);;
			}
		}
	}
	
	public void removeKenken(boolean rem){
		ccjt.removeTableroKenken(this.tk.getIdTablero(), rem);
	}
	
	public void addToConjunto(){
		 ccjt.addTableroKenken(this.tk);
		 ccjt.refreshConjunto();
	}
	public void refreshConjuntotablero(){
		ccjt.refreshConjunto();
	}
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	/*Consultoras*/
	public TaulellKenken getTablero(){
		return this.tk;
	}
	
	public int getIdKenken(){
		return this.tk.getIdTablero();
	}
	
	public String getNameKenken(){
		return this.tk.getName();
	}
	
	public boolean KenkenDone(){
		int n = this.tk.getSizeTab();
		CasillaKenken aux1;
		aux1 =  new CasillaKenken();
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				aux1 = this.tk.getCasillaK(i, j);
				if(aux1.getValor() != aux1.getResultado()) return false;
			}
		}
		return true;
	}
	
	public int getSizeTablero(){
		return this.tk.getSizeTab();
	}
	
	public int[][] getDefaults(){
		return tk.getiDef();
	}
	
	public int[][] getDataZone(){
		return this.tk.getiZone();
	}
	
	public int[][] getCurrentValues(){
		return this.tk.getiMat();
	}
	
	public ArrayList<String> getOperaciones(){
		ArrayList<String> r = new ArrayList<String>();
		
		for(int i = 0; i < this.tk.getNumZonas(); ++i){
			r.add(String.valueOf((this.tk.getZonaByIndex(i).getOperacion())));
			r.add(String.valueOf((this.tk.getZonaByIndex(i).getResultado())));
		}
		
		return r;
	}
	public boolean resolverKenken(int[][] dataValues) {
		return tk.resolveKenken(dataValues);
	}
	
	public int[][] getResultado(){
		return this.tk.getiSol();
	}
	
	public int getDificultat(){
		return this.tk.getDif();
	}
	
	public ArrayList<String> getKenkenList(ArrayList<Integer> idTab){
		//ccjt = ControllerConjuntoTableros.getInstance();
		return ccjt.getListKenkensPlaying(idTab);
	}
	
	public ArrayList<String> getOwnKenkenList(int idUser){
		return ccjt.getListNumOwnTableros(idUser);
	}
	
}