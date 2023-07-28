package logico;

import java.io.Serializable;

public abstract class Componente implements Cloneable,Serializable{
	private static final long serialVersionUID = -3947030900384762237L;
	protected  String numSerie;
	protected int stock;
	protected float precio;
	protected String modelo;
	protected String marca;
	protected String descripcion;
	
	
	public Componente(String numSerie, int stock, String descripcion, float precio, String modelo, String marca) {
		super();
		this.numSerie = numSerie;
		this.stock = stock;
		this.descripcion = descripcion;
		this.precio = precio;
		this.modelo = modelo;
		this.marca = marca;
	}

	public String getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(String numSerie) {
		this.numSerie = numSerie;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	  public Object clone() throws CloneNotSupportedException {
	        Componente cloned = (Componente) super.clone();
	        return cloned;
	}
	// Dentro de la clase Componente

	  public double calcularSubtotal() {
	      return stock * precio;
	  }

}
