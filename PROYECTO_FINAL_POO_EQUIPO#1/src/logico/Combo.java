package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Combo implements Cloneable, Serializable {

	private static final long serialVersionUID = -3159306032366986253L;
	private ArrayList<Componente> misComponentes;
	private String nombre;
	private String codigo;
	private float precio;
	private int stock;

	public Combo(ArrayList<Componente> misComponentes, String nombre, String codigo,float precio, int stock) {
		super();
		this.misComponentes = misComponentes;
		this.nombre = nombre;
		this.precio = precio;
		this.codigo = codigo;
		this.stock = stock;
	}

	public ArrayList<Componente> getMisComponentes() {
		return misComponentes;
	}

	public void setMisComponentes(ArrayList<Componente> misComponentes) {
		this.misComponentes = misComponentes;
	}
	public Object clone() throws CloneNotSupportedException {
		Combo cloned = (Combo) super.clone();
		return cloned;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	public double calcularSubtotal() {
		return stock * precio;
	}

}