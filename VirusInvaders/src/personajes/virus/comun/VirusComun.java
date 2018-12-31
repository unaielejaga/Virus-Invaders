package personajes.virus.comun;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.Virus;

public class VirusComun extends Virus {

	public VirusComun(int vida, int daño, int posicion_x, int posicion_y) { // Tendrán 1 de vida y 1 de daño
		super(vida, daño, posicion_x, posicion_y);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void hacerseDaño(Virus v, int dañoRecibido) {
		v.setVida(v.getVida()-dañoRecibido);
		if(v.getVida()==0) {
		}
		
	}




}
