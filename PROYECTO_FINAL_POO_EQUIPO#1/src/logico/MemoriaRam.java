package logico;

public class MemoriaRam extends Componente {

	private int capacidad;
	private String tipo;

	public MemoriaRam(String numSerie, int stock,String descripcion, float precio, String modelo, String marca, int capacidad,
			String tipo) {
		super(numSerie, stock,descripcion, precio, modelo, marca);
		this.capacidad = capacidad;
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


}
