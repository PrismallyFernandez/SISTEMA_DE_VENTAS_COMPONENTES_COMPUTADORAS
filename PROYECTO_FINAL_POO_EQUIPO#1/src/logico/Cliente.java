package logico;

import java.util.ArrayList;

public class Cliente {
	    private String id;
	    private String cedula;
	    private String nombre;
	    private String telefono;
	    private String direccion;
	    private ArrayList<Factura> misFacturas;

	    public Cliente(String id, String cedula, String nombre, String telefono, String direccion) {
	        this.id = id;
	        this.cedula = cedula;
	        this.nombre = nombre;
	        this.telefono = telefono;
	        this.direccion = direccion;
	        this.misFacturas = new ArrayList<>();
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getCedula() {
	        return cedula;
	    }

	    public void setCedula(String cedula) {
	        this.cedula = cedula;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    public ArrayList<Factura> getMisFacturas() {
	        return misFacturas;
	    }

	    public void agregarFactura(Factura factura) {
	        misFacturas.add(factura);
	    }

}
