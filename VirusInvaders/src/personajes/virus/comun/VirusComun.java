package personajes.virus.comun;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.Virus;

public class VirusComun extends Virus {

	public VirusComun(int vida, int da�o, int posicion_x, int posicion_y) { // Tendr�n 1 de vida y 1 de da�o
		super(vida, da�o, posicion_x, posicion_y);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void hacerseDa�o(Virus v, int da�oRecibido) {
		v.setVida(v.getVida()-da�oRecibido);
		if(v.getVida()==0) {
		}
		
	}




}
