/*Moises Diaz*/
package datos; 

import java.util.Formatter;
import java.util.Scanner;

import domini.CasillaKenken;
import domini.ConjuntoTablero;
import domini.TaulellKenken;
import domini.Zona;

import java.net.URL;
import java.io.*;


public class GestorTableros{
	private Scanner x;
	private Formatter y;
	
	public void openFileR(){
		try {
			//x = new Scanner(ClassLoader.getSystemResourceAsStream("datos/BDTableros.txt"));
			x = new Scanner(new File ("E:/EclipseWorkspace/PROP Kenken/src/datos/BDTableros.txt"));
		}
		catch (Exception e){			
			System.out.println("Read File not found.");
		}
	}
	public void openFileW(){
		try {
			/*URL resourceUrl = getClass().getResource("../datos/BDTableros.txt");
			File file = new File(resourceUrl.toURI());
			OutputStream output = new FileOutputStream(file);
			y = new Formatter(output);*/
			y = new Formatter ("E:/EclipseWorkspace/PROP Kenken/src/datos/BDTableros.txt");
		}
		catch(Exception e){
			System.out.println("Write File not found.");
		}
	}
	public void closeFileR(){
		x.close();
	}
	
	public void closeFileW(){
		y.close();
	}
	
	 public void readTableros(ConjuntoTablero CT){
		 //pre: ConjuntoTablero está vacío.
		 while(x.hasNext()) {
			 
			 int n = x.nextInt();
			 int user = x.nextInt();
			 int idKenken = x.nextInt();
			 TaulellKenken aux = new TaulellKenken(n,user,idKenken,false,0,0);
			 
			 for(int i = 0; i < n; i++) {
				 for(int j = 0; j < n; j++) {
					 
					 int v = x.nextInt();
					 int r = x.nextInt();
					 int iz = x.nextInt();
					 int f = x.nextInt();
					 int c  = x.nextInt();
					 boolean ed = x.nextBoolean();
					 
					 aux.setCasillaKenken(new CasillaKenken(r, iz, f, c, ed, v), f, c);
				 }
			 }
			 n = x.nextInt();
			 for(int y = 0; y < n; y++) {
				 char c = x.next().charAt(0);
	
				 Zona auxZ = new Zona (c, x.nextInt());
				 aux.addZona(auxZ);
			 }
			 
			 CT.addTableroInRead(aux);
			
		 }
		 System.out.println("Tablas leidas completamente!");

	 }
	
	 public void storeTableros(ConjuntoTablero CT) {
		//pre: ConjuntoTablero no está vacío.
		 int n = CT.getNumTableros();
		 for(int i = 0; i < n; ++i) {
			 try{
				 TaulellKenken aux = CT.getTableroIesimo(i);
				 int tam = aux.getSizeTab();
				 int user = aux.getUsuario();
				 int idKenken = aux.getIdTablero();
				 y.format("%d "+"%d "+"%d\n", tam, user, idKenken);
				 
				 for(int l = 0; l < tam; l++){
					 for(int k = 0; k < tam; k++){
						 
						 CasillaKenken auxC = aux.getCasillaK(l, k);
						 
						 y.format("%d "+"%d "+"%d "+"%d "+"%d "+"%b", auxC.getValor(), auxC.getResultado(), auxC.getIndexZona(), l, k, auxC.getEditable());
						 y.format("\n");
						 
					 }
				 }
				 
				 tam = aux.getNumZonas();
				 y.format("%d\n", tam);
				 
				 for(int m = 0; m < tam; ++m) {
					 
					 Zona auxZ = aux.getZonaByIndex(m);
					 y.format("%c"+" "+"%d\n", auxZ.getOperacion(), auxZ.getResultado());
					 
				 }
			 }
			 catch (Exception e){
				 System.out.println(e.getMessage());
			 }
		 }
		 
		 
	 }
	
}