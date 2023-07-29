package logico;

public class Micro extends Componente {

	private String socket;
	private float velocidad;

	public Micro(String numSerie, int stock, String descripcion, float precio, String modelo, String marca, String socket,
			float velocidad) {
		super(numSerie, stock, descripcion, precio, modelo, marca);
		this.socket = socket;
		this.velocidad = velocidad;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public float getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(float velocidad) {
		this.velocidad = velocidad;
	}

}

