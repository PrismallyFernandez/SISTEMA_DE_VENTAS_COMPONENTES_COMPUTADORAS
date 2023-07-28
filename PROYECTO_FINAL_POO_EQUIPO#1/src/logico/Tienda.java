package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Tienda implements Serializable {
	private static final long serialVersionUID = -8956569334515007357L;
	private ArrayList<Componente>misComponentes;
	private ArrayList<Cliente>misClientes;
	private ArrayList<Factura>misFacturas;
	private ArrayList<Combo>misCombos;
	public static int serial = 1;
	public static int codigoUsuario = 1;
	public static Tienda tienda=null;
	private ArrayList<User> misUser;
	
	private static User loginUser; // Variable para almacenar el usuario autenticado
    private boolean loggedIn = false;
	
	private Tienda() {
		super();
		misComponentes = new ArrayList<Componente>();
		misClientes = new ArrayList<>();
		misFacturas = new ArrayList<>();	
		misCombos = new ArrayList<Combo>();
		this.misUser = new ArrayList<>();
		
		cargarComponentesDesdeArchivo();
		cargarCombosDesdeArchivo();
		cargarFacturasDesdeArchivo();
		cargarClientesDesdeArchivo();
		Default();
		//User usuario = new User("Administrador", "admin", "admin");
		//misUser.add(usuario);
	}
	
    public static Tienda getInstance() {
        if (tienda == null) {
            tienda = new Tienda();
        }
        return tienda;
    }
	
    public boolean hasDefaultUser() {
        return UserById("ADM-0") != null;
    }

	public void Default() {
        if (!hasDefaultUser()) {
            User usuario = new User("ADM-0", "admin", "Administrador", "admin", "admin");
            regUser(usuario);
        }
    }
	

	public boolean confirmLogin(String username, String password) {
        for (User user : misUser) {
            if (user.getUserName().equals(username) && user.getPass().equals(password)) {
                loginUser = user; // Almacenar el usuario autenticado
                loggedIn = true; // Usuario autenticado exitosamente
                return true;
            }
        }
        // Usuario no autenticado
        return false;
    }

    // Método para verificar el estado de inicio de sesión
    public boolean isLogged() {
        return loggedIn;
    }
	//
//	
//	public boolean confirmLogin(String text, String text2) {
//		boolean login = false;
//		for (User user : misUser) {
//			if (user.getUserName().equals(text) && user.getPass().equals(text2)) {
//				LoginUser = user;
//				login = true;
//			}
//		}
//		return login;
//	}
    
    
    
    
    
    
    
    
    


	public ArrayList<Componente> getMisComponentes() {
		return misComponentes;
	}

	public void setMisComponentes(ArrayList<Componente> misComponentes) {
		this.misComponentes = misComponentes;
	}

	public ArrayList<User> getMisUsuarios() {
		return misUser;
	}
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}	
	
	public void RegComponente(Componente comp){
		misComponentes.add(comp);
		serial++;
	}
	
	public Cliente ClienteByCedula(String cedula) {

		for (Cliente cliente : misClientes) {
			if (cliente.getCedula().equalsIgnoreCase(cedula)) {
				return cliente;
			}
		}

		return null;
	}
	
	public void eliminarCliente(Cliente selected) {
		misClientes.remove(selected);
	}
	
	public User UserById(String userId) {

		for (User usuario : misUser) {
			if (usuario.getUserId().equalsIgnoreCase(userId)) {
				return usuario;
			}
		}

		return null;
	}
	
	public void eliminarUsuario(User selected) {
		misUser.remove(selected);
	}
	
	
	public Componente ComponenteByCodigo(String numSerie) {

		Componente prueba = null;
		for (Componente componente : misComponentes) {
			if (componente.getNumSerie().equalsIgnoreCase(numSerie)) {
				prueba = componente;
			}
		}
		return prueba;
	}

	public void eliminarComponente(Componente selected) {
		misComponentes.remove(selected);
	}
	
	
	public Componente copiarComp (Componente selec) throws CloneNotSupportedException {
		Componente aux = (Componente) selec.clone();
		return aux;
	}
	
	
	public ArrayList<Componente> copiarArray () throws CloneNotSupportedException{
		
		ArrayList<Componente> copia = new ArrayList<Componente>(misComponentes.size());
		for (Componente comp : misComponentes) {
		    copia.add((Componente) comp.clone());
		}
		return copia;
	}

	public void registrarCliente(Cliente aux) {
		misClientes.add(aux);
	}

	public ArrayList<Combo> copiarArrayCombo() throws CloneNotSupportedException {
		ArrayList<Combo> copia = new ArrayList<Combo>(misCombos.size());
		for (Combo comb : misCombos) {
				copia.add((Combo) comb.clone());
				
			}
	
		return copia;
	}
	public Combo copiarCombo (Combo selec) throws CloneNotSupportedException {
		Combo aux = (Combo) selec.clone();
		return aux;
	}

	public ArrayList<Combo> getMisCombos() {
		return misCombos;
	}

	public void setMisCombos(ArrayList<Combo> misCombos) {
		this.misCombos = misCombos;
	}

	public void agregarFactura(Factura nuevaFactura) {
		misFacturas.add(nuevaFactura);
	}

	public void actualizarFacturas(ArrayList<Componente> temporal) {
	    for(Componente componentes : temporal) {
	        int index = misComponentes.indexOf(componentes);
	        System.out.println(index);
	        if (index >= 0) {
	        	System.out.println("-----------------------------------------------");
	            Componente comp = misComponentes.get(index);
	            System.out.println("-----------------------------------------------");
	            System.out.println(componentes.getStock());
	            System.out.println(comp.getStock()); 
	            comp.setStock(componentes.getStock());
	        }
	    }
	}	
	
	//factura
	public Factura getFacturaByCodigo(String codigoFactura) {
	    for (Factura factura : misFacturas) {
	        if (factura.getCodigo().equalsIgnoreCase(codigoFactura)) {
	            return factura;
	        }
	    }
	    return null;
	}
	
	public void eliminarFactura(Factura selected) {
		misFacturas.remove(selected);
	}
	
	
	public Combo CombobyCodigo(String serial) {
		for(Combo comb : misCombos) {
			if(comb.getCodigo().equalsIgnoreCase(serial)) {
				return comb;
			}
		}
		return null;
	}
	
	public void guardarComponentesEnArchivo() {
		try {
			File archivo = new File("Miscomponentes.dat");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(misComponentes.size());
			for (Componente componente : misComponentes) {

				oos.writeObject(componente);
			}
			oos.close();
			fos.close();
			System.out.println("Se ha guardado el ArrayList misComponentes en el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println(
					"Ha ocurrido un error al guardar el ArrayList misComponentes en el archivo: " + e.getMessage());
		}
	}

	public void cargarComponentesDesdeArchivo() {
		try {
			File archivo = new File("Miscomponentes.dat");
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int numComponentes = ois.readInt();
			for (int i = 0; i < numComponentes; i++) {
				Componente componente = (Componente) ois.readObject();
				misComponentes.add(componente);
			}
			ois.close();
			fis.close();
		System.out
					.println("Se han cargado " + numComponentes + " componentes desde el archivo " + archivo.getPath());
		} catch (Exception e) {
		System.out.println("Ha ocurrido un error al cargar los componentes desde el archivo: " + e.getMessage());
		}
	}

	public void guardarCombosEnArchivo() {
		try {
			File archivo = new File("Miscombos.dat");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(misCombos.size());
			for (Combo combo : misCombos) {

				oos.writeObject(combo);
			}

			oos.close();
			fos.close();
			System.out.println("Se ha guardado el ArrayList misCombos en el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out
					.println("Ha ocurrido un error al guardar el ArrayList misCombos en el archivo: " + e.getMessage());
		}
	}

	public void cargarCombosDesdeArchivo() {
		try {
			File archivo = new File("Miscombos.dat");
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int numCombos = ois.readInt();
			for (int i = 0; i < numCombos; i++) {
				Combo combo = (Combo) ois.readObject();
				misCombos.add(combo);
			}
			ois.close();
			fis.close();
			System.out.println("Se han cargado " + numCombos + " combos desde el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al cargar los combos desde el archivo: " + e.getMessage());
		}
	}

	public void guardarFacturasEnArchivo() {
		try {
			File archivo = new File("MisFacturas.dat");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(misFacturas.size());
			for (Factura factura : misFacturas) {

				oos.writeObject(factura);
			}

			oos.close();
			fos.close();
			System.out.println("Se ha guardado el ArrayList misFacturas en el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println(
					"Ha ocurrido un error al guardar el ArrayList misFacturass en el archivo: " + e.getMessage());
		}
	}

	public void cargarFacturasDesdeArchivo() {
		try {
			File archivo = new File("MisFacturas.dat");
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int numFacturas = ois.readInt();
			for (int i = 0; i < numFacturas; i++) {
				Factura factura = (Factura) ois.readObject();
				misFacturas.add(factura);
			}
			ois.close();
			fis.close();
			System.out.println("Se han cargado " + numFacturas + " las facturas desde el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al cargar las facturas desde el archivo: " + e.getMessage());
		}
	}

	public void guardarClientesEnArchivo() {
		try {
			File archivo = new File("MisClientes.dat");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(misFacturas.size());
			for (Cliente cliente : misClientes) {

				oos.writeObject(cliente);
			}

			oos.close();
			fos.close();
			System.out.println("Se ha guardado el ArrayList misClientes en el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println(
					"Ha ocurrido un error al guardar el ArrayList misClientes en el archivo: " + e.getMessage());
		}
	}

	public void cargarClientesDesdeArchivo() {
		try {
			File archivo = new File("MisClientes.dat");
			FileInputStream fis = new FileInputStream(archivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int numClientes = ois.readInt();
			for (int i = 0; i < numClientes; i++) {
				Cliente cliente = (Cliente) ois.readObject();
				misClientes.add(cliente);
			}
			ois.close();
			fis.close();
			System.out.println("Se han cargado " + numClientes + " los Clientes desde el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al cargar los clientes desde el archivo: " + e.getMessage());
		}
	}
	
	
	public void guardarUsuariosEnArchivo() {
		try {
			File archivo = new File("empresa.dat");
			FileOutputStream fos = new FileOutputStream(archivo);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeInt(misUser.size());
			for (User user : misUser) {

				oos.writeObject(user);
			}

			oos.close();
			fos.close();
			System.out.println("Se ha guardado el ArrayList misUsers en el archivo " + archivo.getPath());
		} catch (Exception e) {
			System.out.println(
					"Ha ocurrido un error al guardar el ArrayList misUsers en el archivo: " + e.getMessage());
		}
	}

	public static void setTienda(Tienda tienda) {
		Tienda.tienda = tienda;
	}
	public void regUser(User user) {
		
		misUser.add(user);
		

	}

	public float gananciaDiscoDuro() {
		float total = 0;
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof DiscoDuro) {
					total += comp.getPrecio();

				}
			}
		}
		return total;

	}

	public float gananciaMotherBoard() {
		float total = 0;
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof Motherboard) {
					total += comp.getPrecio();
				}
			}
		}
		return total;
	}

	public float gananciaMicro() {
		float total = 0;
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof Micro) {
					total += comp.getPrecio();
				}
			}
		}
		return total;
	}

	public float gananciaRam() {
		float total = 0;
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof MemoriaRam) {
					total += comp.getPrecio(); 
				}
			}
		}
		return total;
	}

	public float gananciaCombo() {
		float total = 0;
		for (Factura fact : misFacturas) {
			for (Combo comb : fact.getMisCombos()) {
				total += comb.getPrecio(); 
			}
		}
		return total;
	}

	public float totalGanancia() {
		float total = 0;
		float a = gananciaDiscoDuro();
		float b = gananciaMotherBoard();
		float c = gananciaMicro();
		float d = gananciaRam();
		float e = gananciaCombo();
		total = a + b + c + d + e;
		return total;
	}
	
	public int cantDiscoDuro() {
		int cantidad = 0;
		ArrayList contador = new ArrayList<>();
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof DiscoDuro) {
					contador.add(comp);
					cantidad = contador.size();

				}
			}
		}
		return cantidad;
	}

	public int cantRam() {
		int cantidad = 0;
		ArrayList<Componente> contador = new ArrayList<>();
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof MemoriaRam) {
					contador.add(comp);
					cantidad = contador.size();

				}
			}
		}
		return cantidad;
	}

	public int cantMotherBoard() {
		int cantidad = 0;
		ArrayList<Componente> contador = new ArrayList<>();
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof Motherboard) {
					contador.add(comp);
					cantidad = contador.size();

				}
			}
		}
		return cantidad;
	}

	public int cantMicro() {
		int cantidad = 0;
		ArrayList<Componente> contador = new ArrayList<>();
		for (Factura fact : misFacturas) {
			for (Componente comp : fact.getMisComponentes()) {
				if (comp instanceof Micro) {
					contador.add(comp);
					cantidad = contador.size();

				}
			}
		}
		return cantidad;
	}

	public int cantCombo() {
		int cantidad = 0;
		ArrayList<Combo> contador = new ArrayList<>();
		for (Factura fact : misFacturas) {
			for (Combo cb : fact.getMisCombos()) {
				contador.add(cb);
				cantidad = contador.size();

			}

		}
		return cantidad;

	}

	
	
	
	
}