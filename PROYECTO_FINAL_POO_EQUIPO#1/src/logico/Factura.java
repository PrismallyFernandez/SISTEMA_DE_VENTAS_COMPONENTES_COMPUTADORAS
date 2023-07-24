package logico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Factura {
    private String id;
    private Cliente cliente;
    private ArrayList<Producto> productos;
    private LocalDate fecha;
    private double total;

    public Factura(String id, Cliente cliente, ArrayList<Producto> productos, LocalDate fecha) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
    public double calcularTotalFactura(Map<Producto, Integer> cantidades) {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : cantidades.entrySet()) {
            Producto producto = entry.getKey();
            int cantidad = entry.getValue();
            total += producto.getPrecio() * cantidad;
        }
        return total;
    }

}