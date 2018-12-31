package personajes.virus.boss;

import java.util.ArrayList;

import personajes.Personajes;
import personajes.virus.Virus;

public class VirusBoss extends Virus implements IVirus {

	public VirusBoss(int vida, int da�o, int posicion_x, int posicion_y) { // Tendr� bastante vida y da�o
		super(vida, da�o, posicion_x, posicion_y);
		// TODO Auto-generated constructor stub
	}

	ArrayList<VirusBoss> virusBoss = new ArrayList<>();


	@Override
	public void hacerseDa�o(Virus v, int da�oRecibido) {
		v.setVida(v.getVida()-da�oRecibido);
		
	}

	@Override
	public void disparoAumentado(VirusBoss v, int aumentoDa�o) {
		v.setDa�o(v.getDa�o() + aumentoDa�o);
	}


}
