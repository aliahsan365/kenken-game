/*
 * Moises Diaz
 */

package domini; 

import java.util.ArrayList;
import java.util.ListIterator;

import datos.DatosGestor;

import java.util.Iterator;


public class ConjuntoTablero{
	
	private ArrayList<TaulellKenken> CTablero ;
	
	
	
	//Constructoras
	public ConjuntoTablero(){
		this.CTablero = new ArrayList<TaulellKenken>();
	}
	
	
	
//----------------------------------------------------------------------------------	
	//Modificadoras
	public void addTableroInRead(TaulellKenken TK){
		this.CTablero.add(TK);
	}
	
	public void AddTablero(TaulellKenken TK){
		//int r = calculateMaxIdKenken();
		//TK.setIdTablero(r+1);
		this.CTablero.add(TK);

	}
	
	public void RemoveTablero(int id) throws MyOwnException{
		boolean finished = false;
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		while(it.hasNext() && !finished){
			if(it.next().getIdTablero() == id){
				it.remove();
				finished = true;
			}
		}
	}
	
	public void SetTablero(ControllerTablero ctrKenken){
		int id = ctrKenken.getIdKenken();
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		boolean finished = false;
		while(it.hasNext() && !finished){
			if(it.next().getIdTablero() == id){
				it.set(ctrKenken.getTablero());
				finished = true;
			}
		}
	}
	
	
	
//-----------------------------------------------------------------------------------
	//Consultoras
	public int calculateMaxIdKenken(){
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		int r = 0;
		if(it.hasNext()){
			r = it.next().getIdTablero();
		}
		while(it.hasNext()){
			TaulellKenken aux = it.next();
			if(aux.getIdTablero() > r) r = aux.getIdTablero();
		}
		
		return r;
	}
	
	private ArrayList<Integer> ordenarArraylist(ArrayList<Integer> s){
		ArrayList<Integer> r = new ArrayList<Integer>();
		int n = s.size();
		if(n>0) {
			r.add(s.get(0));
			int m = 1;
			int i = 1;
			while(i < n){
				int j = 0;
				boolean next = false;
				while(j < m && !next){
					if(s.get(i) < r.get(j)){
						r.add(j, s.get(i));
						next = true;
					}
					++j;
				}
				if(!next) r.add(r.size(), s.get(i));
				++i;
			}
		}
		return r;
	}
	
	public int getNumTableros(){
		return this.CTablero.size();
	}
	
	public TaulellKenken getTableroIesimo(int i){
		return this.CTablero.get(i);
	}
	
	public TaulellKenken getTablero(int idTablero) throws MyOwnException{
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		while(it.hasNext()){
			TaulellKenken aux = it.next();
			if(aux.getIdTablero() == idTablero) return aux;
		}
		throw new MyOwnException("No existe el tablero con id: "+idTablero);

	}
	
	public ArrayList<String> getListKenkenGame(ArrayList<Integer> s){
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		ArrayList<String> r = new ArrayList<String>();
		s = ordenarArraylist(s);
		int i = 0;
		while(it.hasNext() && i < s.size()){
			TaulellKenken aux = it.next();
			if(aux.getIdTablero() == s.get(i)){
				r.add(aux.getName());
				r.add(String.valueOf(aux.getIdTablero()));
				++i;
			}
		}
		
		return r;
	}
	
	public ArrayList<String> howManyOwn(int id_user){
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		ArrayList<String> r = new ArrayList<String>();
		
		while(it.hasNext()){
			TaulellKenken aux = it.next();
			if(aux.getUsuario() == id_user){
				r.add(aux.getName());
				r.add(String.valueOf(aux.getIdTablero()));
			}
		}
		return r;
	}
	public TaulellKenken getUserKenken(int idUser, int idTablero) throws MyOwnException{
		ListIterator<TaulellKenken> it = this.CTablero.listIterator();
		while(it.hasNext()){
			TaulellKenken aux = it.next();
			if(aux.getUsuario() == idUser && aux.getIdTablero() == idTablero) return aux;
		}
		throw new MyOwnException("El usuario no tiene un tablero con el id: " + idTablero);
	}
	/*-------------------------------------------------------------------------------------------------------*/
	/*Lectura y Escritura de datos*/
	public void ReadConjuntoTableros(){
		String path = "datos/BDTableros.txt";
		DatosGestor dg = DatosGestor.getInstance();
		ArrayList<String> l = dg.leerTodo(path);
		ListIterator<String> it = l.listIterator();
		while(it.hasNext()){
			int n = Integer.parseInt(it.next());
			int user = Integer.parseInt(it.next());
			int idKenken = Integer.parseInt(it.next());
			int diff = Integer.parseInt(it.next());
			String KenkenName = it.next();
			TaulellKenken aux = new TaulellKenken(n,user,idKenken,false, diff, 0);
			aux.setName(KenkenName);
			for(int i = 0; i < n; i++) {
				 for(int j = 0; j < n; j++) {
					 
					 int v = Integer.parseInt(it.next());
					 int r = Integer.parseInt(it.next());
					 int iz = Integer.parseInt(it.next());
					 int f = Integer.parseInt(it.next());
					 int c  = Integer.parseInt(it.next());
					 boolean ed = Boolean.parseBoolean(it.next());
					 
					 aux.setCasillaKenken(new CasillaKenken(r, iz, i, j, ed, v), i, j);
				 }
			}
			n = Integer.parseInt(it.next());
			 for(int y = 0; y < n; y++) {
				 char c = it.next().charAt(0);
				 Zona auxZ = new Zona (c, Long.parseLong(it.next()));
				 aux.addZona(auxZ);
			 }
			 addTableroInRead(aux);
		}
		dg = null;
	}
	
	public void WriteConjuntoTableros(){
		String path = "datos/BDTableros.txt";
		DatosGestor dg = DatosGestor.getInstance();
		ArrayList<String> l = new ArrayList<String>();
		ListIterator<TaulellKenken> l2 = this.CTablero.listIterator();
		int NT = this.CTablero.size();
		for(int i = 0; i < NT; ++i){
			TaulellKenken t = l2.next();
			int n = t.getSizeTab();
			l.add(Integer.toString(n));
			l.add(Integer.toString(t.getUsuario()));			
			l.add(Integer.toString(t.getIdTablero()));
			l.add(Integer.toString(t.getDif()));
			l.add(t.getName());
			l.add("%n");
			for(int k = 0; k < n; ++k){
				for(int m = 0; m < n; ++m){
					l.add(Integer.toString(t.getCasillaK(k, m).getValor()));
					l.add(Integer.toString(t.getCasillaK(k, m).getResultado()));
					l.add(Integer.toString(t.getCasillaK(k, m).getIndexZona()));
					l.add(Integer.toString(k));
					l.add(Integer.toString(m));
					l.add(String.valueOf(t.getCasillaK(k, m).getEditable()));
					l.add("%n");
				}
			}
			n = t.getNumZonas();
			l.add(Integer.toString(n));
			l.add("%n");
			for(int k = 0; k < n; ++k){
				Zona z = t.getZonaByIndex(k);
				l.add(new StringBuilder().append(z.getOperacion()).toString());
				l.add(Long.toString(z.getResultado()));
				l.add("%n");
			}
			
		}
		dg.escribirTodo(path, l);
		dg = null;
	}
	
	
}