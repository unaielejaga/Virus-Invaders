package personajes;

public abstract class Personajes {
	
	private int vida; // Todos los personajes tienen una vida limitada por los disparos que puede recibir
	private int da�o;// Los personajes pueden disparar proyectiles que hacen diferente da�o depende de que personaje sea
	private int posicion_x;
	private int posicion_y;
	
	
	public int getPosicion_x() {
		return posicion_x;
	}

	public void setPosicion_x(int posicion_x) {
		this.posicion_x = posicion_x;
	}

	public int getPosicion_y() {
		return posicion_y;
	}

	public void setPosicion_y(int posicion_y) {
		this.posicion_y = posicion_y;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDa�o() {
		return da�o;
	}

	public void setDa�o(int da�o) {
		this.da�o = da�o;
	}

	public Personajes(int vida, int da�o, int posiocion_x, int posicion_y) {
		this.vida = vida;
		this.da�o = da�o;
		this.posicion_x = posiocion_x;
		this.posicion_y = posicion_y;
	}


}
