package domini;

import domini.MyOwnException;
import java.util.*;

public class ControllerConjuntoTableros {
	private static ControllerConjuntoTableros instance = null;
	private ConjuntoTablero ct;

	
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	/*Creadoras*/
	
	protected ControllerConjuntoTableros(){
		ct = new ConjuntoTablero();
		loadConjuntoTableros();
	}
	public static ControllerConjuntoTableros getInstance() {
		if(instance == null) {
			instance = new ControllerConjuntoTableros();
		}
		
		return instance;
	}
	
	private void loadConjuntoTableros(){
		ct = new ConjuntoTablero();
		ct.ReadConjuntoTableros();
	}
	private void storeConjuntoTableros(){
		ct.WriteConjuntoTableros();
	}
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	/*Modificadoras*/
	
	
	public void addTableroKenken(TaulellKenken tab){
		//int r =
		this.ct.AddTablero(tab); 
		storeConjuntoTableros();
		//return r;
	}
	
	public void removeTableroKenken(int idTK, boolean data){
		try{

			ct.RemoveTablero(idTK);
			
			if(data) storeConjuntoTableros();
		}
		catch (MyOwnException e){
			System.out.println(e.getMessage());
		}
	}
	public void refreshConjunto(){
		loadConjuntoTableros();
	}
	/*
	public void setTableroKenken(ControllerTablero ctrTab){
		loadConjuntoTableros();
		ct.SetTablero(ctrTab);
		storeConjuntoTableros();
	}*/
	/*-------------------------------------------------------------------------------------------------------------------------------*/
	/*Consultoras*/
	public int calculateIdKenken(){
		return this.ct.calculateMaxIdKenken()+1;
	}
	
	public TaulellKenken ObtenerTablero(int idTK)throws MyOwnException{
		TaulellKenken Tab = new TaulellKenken();
		try{
			Tab =  this.ct.getTablero(idTK);
			
		}
		catch (MyOwnException e){
			throw new MyOwnException(e.getMessage());
		} 
		return Tab;
	}
	
	public ArrayList<String> getListNumOwnTableros(int id_user){
		ArrayList<String> r = ct.howManyOwn(id_user);
		//if(r.size() > 0)
		return r;
		//else throw new MyOwnException("El usuario no tiene ningun tablero");
	}
	
	public TaulellKenken getMyKenken(int id_user, int idTK) throws MyOwnException{
		TaulellKenken Tab = new TaulellKenken();
		try{
			Tab = ct.getUserKenken(idTK, id_user);
		}
		catch(MyOwnException e){
			throw new MyOwnException(e.getMessage());
		}
		return Tab;
	}
	
	public ArrayList<String> getListKenkensPlaying(ArrayList<Integer> li){
		return this.ct.getListKenkenGame(li);
	}
	
}