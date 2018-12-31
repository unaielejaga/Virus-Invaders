package usuarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;



public class Usuarios {
	public String nick;
	public String contraseña;
	public int puntuacion;
	public static HashMap<String, Integer> puntos = new HashMap<>(); // Guarda nick y puntos
	public static HashMap<String, String> usuarios = new HashMap<>();// Guarda nick y contraseñas
	
	public Usuarios(String nick, String contraseña) {
		this.nick = nick;
		this.contraseña = contraseña;
	}
	
	public Usuarios(String nick, int puntacion) {
		this.nick = nick;
		this.puntuacion = puntacion;
	}
	
	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	
	public static HashMap<String, Integer> getPuntos() {
		return puntos;
	}


	public static void setPuntos(HashMap<String, Integer> puntos) {
		Usuarios.puntos = puntos;
	}


	public static HashMap<String, String> getUsuarios() {
		return usuarios;
	}


	public Usuarios() {
		
	}
	
	
	public static void setUsuarios(HashMap<String, String> usuarios) {
		Usuarios.usuarios = usuarios;
	}
	
	
	public static void escribirDeHashAFicheroPuntos(HashMap<String, Integer> map){ // Escribe puntos
	    try{
	    File fileTwo=new File("puntos.txt");
	    FileOutputStream fos = new FileOutputStream(fileTwo);
	        PrintWriter pw = new PrintWriter(fos);

	        for(Map.Entry<String,Integer> m :map.entrySet()){
	            pw.println(m.getKey() + "=" + m.getValue());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	    }catch(Exception e){}
	    
	}
	
	public static void leerDeFicherosAHashPuntos(HashMap<String, Integer> map) { // Lee puntos
		 try{
		        File toRead=new File("puntos.txt");
		        FileInputStream fis = new FileInputStream(toRead);

		        Scanner sc=new Scanner(fis);
      
		        String currentLine;
		        while(sc.hasNextLine()){
		            currentLine = sc.nextLine();
		           
		            StringTokenizer st = new StringTokenizer(currentLine,"=",false);
		           
		            map.put(st.nextToken(),Integer.parseInt(st.nextToken()));
		        }
		        fis.close();
		    }catch(Exception e){}
		  }
	



	public static void escribirDeHashAFicheroUsuarios(HashMap<String, String> map){ // Escribe usuarios
	    try{
	    File fileTwo=new File("usuarios.txt");
	    FileOutputStream fos = new FileOutputStream(fileTwo);
	        PrintWriter pw = new PrintWriter(fos);

	        for(Map.Entry<String,String> m :map.entrySet()){
	            pw.println(m.getKey() + "=" + m.getValue());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	    }catch(Exception e){}
	    
	}
	
	public static void leerDeFicherosAHashUsuarios(HashMap<String, String> map) { // Lee usuarios
		 try{
		        File toRead = new File("usuarios.txt");
		        FileInputStream fis = new FileInputStream(toRead);

		        Scanner sc = new Scanner(fis);

		        String currentLine;
		        while(sc.hasNextLine()){
		            currentLine=sc.nextLine();
		           
		            StringTokenizer st=new StringTokenizer(currentLine,"=",false);
		           
		            map.put(st.nextToken(),st.nextToken());
		        }
		        fis.close();
		    }catch(Exception e){}
		  }
}
