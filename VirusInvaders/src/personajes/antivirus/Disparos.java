package personajes.antivirus;

import java.util.ArrayList;

public class Disparos { // Todos los disparos tienen un eje x y y

	int poscion_x; // Lo heredan del personaje del que salen (Antivirus o Virus)
	int posicion_y; // Se irá modificando segun el tiempo (hilos)
	ArrayList<Disparos> disparos = new ArrayList<>();
	
	public int getPoscion_x() {
		return poscion_x;
	}

	public void setPoscion_x(int poscion_x) {
		this.poscion_x = poscion_x;
	}

	public int getPosicion_y() {
		return posicion_y;
	}

	public void setPosicion_y(int posicion_y) {
		this.posicion_y = posicion_y;
	}
	
	public Disparos(int posicion_x, int posicion_y) {
		this.poscion_x = posicion_x;
		this.posicion_y = posicion_y;
				
		
	}
}
