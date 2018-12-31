package personajes.antivirus;

import java.util.ArrayList;

import personajes.Personajes;

public class Antivirus extends Personajes {
	
	

	public Antivirus(int vida, int daño, int posicion_x, int posicion_y) { // Tendrá unas 3 o 5 vidas y 1 de daño
		super(vida,daño, posicion_x, posicion_y);
	}
	
	ArrayList<Antivirus> antivirus = new ArrayList<>();
	
	public void disparo(Antivirus a) { // Al disparar se mete un Disparo en un ArrayList de disparos
		ArrayList<Disparos> disparos = new ArrayList<>();
		Disparos d = new Disparos(a.getPosicion_x(), a.getPosicion_y());
		
	}


	public void vidasRestantes(Antivirus a, int dañoRecibido) { // Calcula las vidas que le quedan
		a.setVida(a.getVida()-dañoRecibido);
	}

}
