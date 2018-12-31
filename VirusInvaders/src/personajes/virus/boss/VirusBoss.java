package personajes.virus.boss;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.Virus;

public class VirusBoss extends Virus implements IVirus {

	public VirusBoss(int vida, int daño, int posicion_x, int posicion_y) { // Tendrá bastante vida y daño
		super(vida, daño, posicion_x, posicion_y);
		// TODO Auto-generated constructor stub
	}

	ArrayList<VirusBoss> virusBoss = new ArrayList<>();


	@Override
	public void hacerseDaño(Virus v, int dañoRecibido) {
		v.setVida(v.getVida()-dañoRecibido);
		
	}

	@Override
	public void disparoAumentado(VirusBoss v, int aumentoDaño) {
		v.setDaño(v.getDaño() + aumentoDaño);
	}


}
