package personajes.virus;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.comun.VirusComun;

public abstract class Virus extends Personajes {

	
	public Virus(int vida, int daño, int poscion_x, int posicion_y) { // Dependiendo del tipo de virus será de un tipo u de otro
		super(vida, daño, poscion_x, posicion_y);
	}
	
	public void hacerseDaño(Virus v, int dañoRecibido) {
		// TODO Auto-generated method stub
		
	}
	
}
