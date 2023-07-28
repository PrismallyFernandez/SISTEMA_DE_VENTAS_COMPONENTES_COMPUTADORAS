package logico;

public class Motherboard extends Componente {

	private String socket;
	private String tipo;
	private String conexiones;
	
	public Motherboard(String numSerie, int stock, String descripcion, float precio, String modelo, String marca, String socket,
			String tipo, String conexiones) {
		super(numSerie, stock, descripcion, precio, modelo, marca);
		this.socket = socket;
		this.tipo = tipo;
		this.conexiones = conexiones;
	}

	public String getSocket() {
		return socket;
	}

	public void setSocket(String socket) {
		this.socket = socket;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getConexiones() {
		return conexiones;
	}

	public void setConexiones(String conexiones) {
		this.conexiones = conexiones;
	}
	
}
