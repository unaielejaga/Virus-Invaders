package personajes.virus;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.comun.VirusComun;

public abstract class Virus extends Personajes {

	
	public Virus(int vida, int da�o, int poscion_x, int posicion_y) { // Dependiendo del tipo de virus ser� de un tipo u de otro
		super(vida, da�o, poscion_x, posicion_y);
	}
	
	public void hacerseDa�o(Virus v, int da�oRecibido) {
		// TODO Auto-generated method stub
		
	}
	
}
