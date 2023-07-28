package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import logico.Cliente;
import logico.Combo;
import logico.Componente;
import logico.DiscoDuro;
import logico.Factura;
import logico.MemoriaRam;
import logico.Micro;
import logico.Motherboard;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import java.awt.Color;

public class ModificarFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private JButton btnDelete;
	private JButton btnCancelar;
	private JButton btnLimpiar;
	private JButton btnFacturar;
	private JButton btnSetear;
	
	private String tipo;
	private Componente selected = null;
	private Combo selectedC = null;
	private JTextField CedulatextField;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField SerietextField;
	private static JTextField txtTotal;
	private static ArrayList<Componente> componentesFactura = new ArrayList<Componente>();
	private static ArrayList<Componente> temporal = copiarPrueba();                         //Tienda.getInstance().copiarArray();
	
	private static ArrayList<Combo> combosFactura = new ArrayList<Combo>();
	private static ArrayList<Combo> combosTemp =  copiarCombo();  //Tienda.getInstance().copiarArrayCombo();
	private  Componente selected_1 = null;
	private Combo selected_2 = null;
	private JSpinner Agregarspinner = null;
	private boolean control;
	private Cliente auxCliente = null;
	private JTextField textDate;
	
	
	private Factura facturaSeleccionada;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarFactura dialog = new ModificarFactura(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public ModificarFactura(Factura facturaSeleccionada) {
		setResizable(false);
	    this.facturaSeleccionada = facturaSeleccionada;
	    Combo copy = new Combo(copiarListaProfunda(facturaSeleccionada.getMisComponentes()), null, null, 0, 0);
        Combo combo= new Combo (copiarListaProfunda(copy.getMisComponentes()), null, null, 0,0);
        
        
        
		System.out.println("mmmmmmmmmmmmmmmmmmmggggggggggg");
		System.out.println(Tienda.getInstance().getMisCombos().size());
		System.out.println("mmmmmmmmmmmmmmmmmmmggggggggggg");
		
		inicializar();
		setBounds(100, 100, 1046, 515);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(1, 137, 1023, 241);
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					String[] headers = {"Numero de serie","Tipo de Componente","Marca","Modelo", "Stock", "Precio", "Subtotal" };
					
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
					table.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int ind = table.getSelectedRow();
							if (ind >= 0 ) {
								btnDelete.setEnabled(true);
								
								btnSetear.setEnabled(true);
								String codigo = table.getValueAt(ind, 0).toString();
								//int stock = (int) table.getValueAt(ind, 4);
								selected = buscarComponenteBySerieFactura(codigo);
								selectedC = buscarCombosBySerieFactura(codigo);
								//Agregarspinner.setValue(selected != null ? selected.getStock() : selectedC.getStock());
							}
						}
					});
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);

				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(255, 255, 255));
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(29, 12, 995, 122);
				panel.add(panel_1);
				{
					JLabel lblCdula = new JLabel("Cédula:");
					lblCdula.setBounds(10, 11, 89, 14);
					panel_1.add(lblCdula);
				}
				{
					CedulatextField = new JTextField();
					CedulatextField.setColumns(10);
					CedulatextField.setBounds(76, 7, 216, 23);
					panel_1.add(CedulatextField);
				}
				{
					JButton btnBuscar = new JButton("Buscar");
					btnBuscar.setBackground(new Color(102, 0, 255));
					btnBuscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							//btnFacturar.setEnabled(true);
							
							auxCliente = Tienda.getInstance().ClienteByCedula(CedulatextField.getText());
							if(auxCliente != null){
									txtNombre.setText(auxCliente.getNombre()); 
									txtDireccion.setText(auxCliente.getDireccion());
									txtTelefono.setText(auxCliente.getCedula());
									control = true;
					
							}else {
								txtNombre.setEditable(true);
								txtTelefono.setEditable(true);
								txtDireccion.setEditable(true);
								control = false;
							  }
						}
					});
					btnBuscar.setBounds(355, 7, 89, 23);
					panel_1.add(btnBuscar);
				}
				{
					JLabel lblNombre = new JLabel("Nombre:");
					lblNombre.setBounds(10, 45, 79, 14);
					panel_1.add(lblNombre);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setBackground(new Color(255, 255, 255));
					txtNombre.setEditable(false);
					txtNombre.setColumns(10);
					txtNombre.setBounds(76, 37, 162, 23);
					panel_1.add(txtNombre);
				}
				{
					JLabel lblTelfono = new JLabel("Teléfono:");
					lblTelfono.setBounds(256, 42, 75, 14);
					panel_1.add(lblTelfono);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setBackground(new Color(255, 255, 255));
					txtTelefono.setEditable(false);
					txtTelefono.setColumns(10);
					txtTelefono.setBounds(329, 41, 115, 23);
					panel_1.add(txtTelefono);
				}
				{
					JLabel lblDireccin = new JLabel("Dirección:");
					lblDireccin.setBounds(10, 81, 89, 14);
					panel_1.add(lblDireccin);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setBackground(new Color(255, 255, 255));
					txtDireccion.setEditable(false);
					txtDireccion.setColumns(10);
					txtDireccion.setBounds(86, 71, 357, 23);
					panel_1.add(txtDireccion);
				}
				{
					JLabel lblNuemroDeSerie = new JLabel("Numero de serie:");
					lblNuemroDeSerie.setBounds(485, 45, 136, 14);
					panel_1.add(lblNuemroDeSerie);
				}
				{
					SerietextField = new JTextField();
					SerietextField.setColumns(10);
					SerietextField.setBounds(483, 73, 178, 23);
					panel_1.add(SerietextField);
				}
				{
					JButton btnAnadir = new JButton("Ingresar");
					btnAnadir.setBackground(new Color(51, 204, 153));
					btnAnadir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String serie = SerietextField.getText().toString();
							System.out.println(serie);
							Componente componente = buscarComponenteBySerie(serie);
							
							Boolean controlador = false;
							
							ArrayList<Combo> copia = new ArrayList<Combo>(Tienda.getInstance().getMisCombos().size());
							for (Combo comb : Tienda.getInstance().getMisCombos()) {
									try {
										copia.add((Combo) comb.clone());
									} catch (CloneNotSupportedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							System.out.println("----------------------------------");
							System.out.println(copia.size());
							Combo combo = buscarComboByCodigo(serie,copia);
							
							
							
							if(componente == null && combo != null && combo.getStock()>0) {
								int diferencia = 1;//Integer.valueOf((Integer)Agregarspinner.getValue());
								
								
								try {
									selected_2 = Tienda.getInstance().copiarCombo(combo);
								} catch (CloneNotSupportedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								selected_2.setStock(diferencia);
								combo.setStock(combo.getStock()-diferencia);
								reescribirCombo(combo);
								combosFactura.add(selected_2);
								
								controlador = true;
							}
							if(componente != null && componente.getStock()>0) {
								
								int diferencia = 1;//Integer.valueOf((Integer)Agregarspinner.getValue());
								try {
									selected_1 = Tienda.getInstance().copiarComp(componente);
								} catch (CloneNotSupportedException e1) {
									e1.printStackTrace();
									System.out.println("Hay una palomeria vigente");
								}
								
								selected_1.setStock(diferencia);
								componente.setStock(componente.getStock()-diferencia);
								reescribirComponete(componente);
								componentesFactura.add(selected_1);
								
								controlador = true;
							} 
							if(controlador == false) {
								JOptionPane.showMessageDialog(null, "El Componente no existe o no esta disponible", "Error", JOptionPane.INFORMATION_MESSAGE);
							}
							
							load();
						}
						
					});
					btnAnadir.setBounds(673, 72, 104, 23);
					panel_1.add(btnAnadir);
				}
				{
					textDate = new JTextField();
					textDate.setBackground(new Color(255, 255, 255));
					textDate.setEditable(false);
					textDate.setColumns(10);
					textDate.setBounds(539, 7, 162, 23);
					panel_1.add(textDate);
				}
				
				JLabel labelDate = new JLabel("Fecha:");
				labelDate.setBounds(486, 10, 47, 15);
				panel_1.add(labelDate);
			}
			{
				JLabel lblTotal = new JLabel("Total:");
				lblTotal.setBounds(629, 394, 46, 14);
				panel.add(lblTotal);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setBackground(new Color(255, 255, 255));
				txtTotal.setEditable(false);
				txtTotal.setColumns(10);
				txtTotal.setBounds(676, 390, 115, 23);
				panel.add(txtTotal);
			}
			{
				JLabel lblNuemroDeSerie = new JLabel("Cantidad por articulo:");
				lblNuemroDeSerie.setBounds(121, 394, 167, 14);
				panel.add(lblNuemroDeSerie);
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String fechaActual = formatter.format(now);
            textDate.setText(fechaActual);
			
			Agregarspinner = new JSpinner();
			Agregarspinner.setValue(1);
			Agregarspinner.setBounds(279, 392, 46, 20);
			panel.add(Agregarspinner);
			{
				btnSetear = new JButton("set->");
				btnSetear.setEnabled(false);
				btnSetear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(selectedC != null) {
						Combo nuevo = null;
							try {
								nuevo = (Combo) selectedC.clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
								
							Combo comb = buscarCombosBySerieFactura(nuevo.getCodigo());
							comb.setStock((Integer) Agregarspinner.getValue());
							
					        Agregarspinner.setValue(0);
						}
						if(selected != null) {
							Componente nuevo = null;
							try {
								nuevo = (Componente) selected.clone();
							} catch (CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
								
							Componente comp = buscarComponenteBySerieFactura(nuevo.getNumSerie());
							comp.setStock((Integer) Agregarspinner.getValue());
							
					        Agregarspinner.setValue(0);
						}
					        load();
					    }
				
					
				});
				btnSetear.setBounds(337, 390, 72, 25);
				panel.add(btnSetear);
			}
	
		}
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDelete = new JButton("Eliminar");
				btnDelete.setBackground(new Color(204, 0, 0));
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Estas seguro de querer eliminar el Companente de la factura?",
									"Eliminar Componente", JOptionPane.OK_CANCEL_OPTION);
							if(option == JOptionPane.OK_OPTION) {
								
								int diferencia = selected.getStock();
								Componente componente = buscarComponenteBySerie(selected.getNumSerie());
								componente.setStock(componente.getStock()+diferencia);
								reescribirComponete(componente);
								componentesFactura.remove(selected);
								
							}
							load();
						}
						if (selectedC != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Estas seguro de querer eliminar el Combo de la factura?",
									"Eliminar Combo", JOptionPane.OK_CANCEL_OPTION);
							if(option == JOptionPane.OK_OPTION) {
								ArrayList<Combo> copia = new ArrayList<Combo>(Tienda.getInstance().getMisCombos().size());
								for (Combo comb : Tienda.getInstance().getMisCombos()) {
										try {
											copia.add((Combo) comb.clone());
										} catch (CloneNotSupportedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
								int diferencia = selectedC.getStock();
								//Combo combo = buscarCombosBySerieFactura(selectedC.getCodigo());
								//combo.setStock(combo.getStock() + diferencia);
								//reescribirCombo(combo);
								componentesFactura.remove(selected);
							}
							load();
						}
					}
				});
				{
					btnLimpiar = new JButton("Limpiar");
					btnLimpiar.setBackground(new Color(102, 0, 255));
					btnLimpiar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							ArrayList<Componente> copiaFactura = new ArrayList<Componente>();
							for (Componente c : componentesFactura) {
							    try {
							        copiaFactura.add((Componente) c.clone());
							    } catch (CloneNotSupportedException e2) {
							        e2.printStackTrace();
							    }
							}
							for (Componente selected : copiaFactura) {
							    int cantidad = selected.getStock();
							    Componente componente = buscarComponenteBySerie(selected.getNumSerie());
							    componente.setStock(componente.getStock() + cantidad);
							    reescribirComponete(componente);
							    componentesFactura.remove(selected);
							}
							ArrayList<Combo> copiaFacturaCombo = new ArrayList<Combo>();
							for (Combo c : combosFactura) {
							    try {
							        copiaFacturaCombo.add((Combo) c.clone());
							    } catch (CloneNotSupportedException e3) {
							        e3.printStackTrace();
							    }
							}
							ArrayList<Combo> copia = new ArrayList<Combo>(Tienda.getInstance().getMisCombos().size());
							for (Combo comb : Tienda.getInstance().getMisCombos()) {
									try {
										copia.add((Combo) comb.clone());
									} catch (CloneNotSupportedException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							for (Combo selected : copiaFacturaCombo) {
							    int cantidad = selected.getStock();
							    Combo Combos = buscarComboByCodigo(selected.getCodigo(),copia);
							    Combos.setStock(Combos.getStock() + cantidad);
							    reescribirCombo(Combos);
							    combosFactura.remove(selected);
							}
							componentesFactura.clear();
							combosFactura.clear();
							load();
						}
					});
					{
						btnFacturar = new JButton("Facturar");
						btnFacturar.setBackground(new Color(51, 204, 153));
						btnFacturar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								
								ArrayList<Componente> faccomp;
								
								faccomp = copiarArraytemp();
								
							
								ArrayList<Combo> combofac;
								
								combofac = copiarArraytempCombos();
								
								if(!control) {
									 auxCliente = new Cliente(CedulatextField.getText(),txtNombre.getText(),txtDireccion.getText(),txtTelefono.getText());
									Tienda.getInstance().registrarCliente(auxCliente);
								}
								JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								//clear();
								
								String codigo = "Fa-"+Tienda.getInstance().getMisFacturas().size();
								
								
								
								
								
								Factura nuevaFactura = new Factura(codigo,faccomp,combofac, auxCliente, txtTotal.getText() );
								Tienda.getInstance().setMisCombos(combosTemp);
								Tienda.getInstance().setMisComponentes(temporal);
								Tienda.getInstance().agregarFactura(nuevaFactura);
								
								
								
								Tienda.getInstance().guardarClientesEnArchivo();
								Tienda.getInstance().guardarCombosEnArchivo();
								Tienda.getInstance().guardarComponentesEnArchivo();
								Tienda.getInstance().guardarFacturasEnArchivo();
								
								  

								
								clean();
								load();
								
							}
						});
						buttonPane.add(btnFacturar);
					}
					buttonPane.add(btnLimpiar);
				}
				buttonPane.add(btnDelete);;
			}
			
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(new Color(102, 0, 255));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		load();
		
		// Recuperar detalles de la factura seleccionada
        Cliente clienteFactura = facturaSeleccionada.getCliente();
        ArrayList<Componente> componentesFactura = facturaSeleccionada.getMisComponentes();
        ArrayList<Combo> combosFactura = facturaSeleccionada.getMisCombos();

        // Llenar los campos con los datos de la factura seleccionada
        CedulatextField.setText(clienteFactura.getCedula());
        txtNombre.setText(clienteFactura.getNombre());
        txtDireccion.setText(clienteFactura.getDireccion());
        txtTelefono.setText(clienteFactura.getTelefono());

       
	}

	public static void load() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		float total = 0;
		int[] cont;
			for (Componente aux : componentesFactura) {
				
				rows[0] = aux.getNumSerie();
				rows[2] = aux.getMarca();
				rows[3] = aux.getModelo();
				rows[4] = aux.getStock();
				rows[5] = aux.getPrecio();
				rows[6] = aux.getPrecio()*aux.getStock();
				if(aux instanceof DiscoDuro ){
					rows[1] = "Disco Duro";	
				}
				if(aux instanceof MemoriaRam){
					rows[1] = "Memoria Ram";	
				}
				if(aux instanceof Micro ){
					rows[1] = "Microprocesador";
				}
				if(aux instanceof Motherboard ){
					rows[1] = "MotherBoard";
				}
				model.addRow(rows);
				total += aux.getPrecio()*aux.getStock();
			}
			for (Combo aux : combosFactura) {
				cont = ListadoCombo.contador(aux);
				rows[0] = aux.getCodigo();
				rows[1] = "MDR: "+cont[0]+" - DIS: " + cont[1] + " - MIC: "+cont[2]+ " - MER: "+cont[3];
				rows[2] = aux.getNombre();
				rows[3] = 
				rows[4] = aux.getStock();
				rows[5] = aux.getPrecio();
				rows[6] = aux.getPrecio()*aux.getStock();
				model.addRow(rows);
				total += aux.getPrecio();
			}
			String txt = Float.toString(total);
			txtTotal.setText(txt);
			
			
			
	}	
	public Componente buscarComponenteBySerie(String serie) {
		Componente aux = null;
		for(Componente componentes : temporal) {
			if(componentes.getNumSerie().equalsIgnoreCase(serie)) {
				aux = componentes;
			}
		}
		return aux;
	}
	public Combo buscarComboByCodigo(String serie,ArrayList<Combo> copia) {
		Combo aux = null;
		for(Combo combos : copia) {
			if(combos.getCodigo().equalsIgnoreCase(serie)) {
				aux = combos;
			}
		}
		return aux;
	}
	
	public Componente buscarComponenteBySerieFactura(String serie) {
		Componente aux = null;
		for(Componente componentes : componentesFactura) {
			if(componentes.getNumSerie().equalsIgnoreCase(serie)) {
				aux = componentes;
			}
		}
		return aux;
	}
	public Combo buscarCombosBySerieFactura(String serie) {
		Combo aux = null;
		for(Combo combo : combosFactura) {
			if(combo.getCodigo().equalsIgnoreCase(serie)) {
				aux = combo;
			}
		}
		return aux;
	}
	
	public static ArrayList<Componente> copiarPrueba() {
			try {
				temporal = Tienda.getInstance().copiarArray();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return temporal;
	}
	public static ArrayList<Combo> copiarCombo()  {
		ArrayList<Combo> copia = new ArrayList<Combo>(Tienda.getInstance().getMisCombos().size());
		for (Combo comb : Tienda.getInstance().getMisCombos()) {
				try {
					copia.add((Combo) comb.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		System.out.println("-------------------------------");
		System.out.println(Tienda.getInstance().getMisCombos().size());
		return copia;
}

	public void reescribirComponete(Componente componente) {
		
		ArrayList<Componente> copia = new ArrayList<Componente>();
		for (Componente c : temporal) {
		    try {
		        copia.add((Componente) c.clone());
		    } catch (CloneNotSupportedException e2) {
		        e2.printStackTrace();
		    }
		}
		for(Componente componentes: copia) {
			if(componentes == componente) {
				temporal.remove(componentes);
				temporal.add(componente);
			}
		}
		
		
	}
	public void reescribirCombo(Combo combo) {
		
		ArrayList<Combo> copia = new ArrayList<Combo>();
		for (Combo c : combosTemp) {
		    try {
		        copia.add((Combo) c.clone());
		    } catch (CloneNotSupportedException e2) {
		        e2.printStackTrace();
		    }
		}
		for(Combo combos: copia) {
			if(combos == combo) {
				combosTemp.remove(combos);
				combosTemp.add(combo);
			}
		}
		
		
	}
	public void reescribirFacturaComp(Componente componente) {
		ArrayList<Componente> copia = new ArrayList<Componente>();
		for (Componente c : temporal) {
		    try {
		        copia.add((Componente) c.clone());
		    } catch (CloneNotSupportedException e2) {
		        e2.printStackTrace();
		    }
		}
		
		for(Componente componentes: copia) {
			if(componentes == componente) {
				System.out.println(componente.getStock());
				componentesFactura.remove(componentes);
				componentesFactura.add(componente);
			}
		}
		
		
	}
	public void reescribirFacturaComb(Combo combo) {
		
		ArrayList<Combo> copia = new ArrayList<Combo>();
		for (Combo c : combosFactura) {
		    try {
		        copia.add((Combo) c.clone());
		    } catch (CloneNotSupportedException e2) {
		        e2.printStackTrace();
		    }
		}
		for(Combo combos: copia) {
			if(combos == combo) {
				combosFactura.remove(combos);
				combosFactura.add(combo);
			}
		}
		
		
	}
	
	   public ArrayList<Componente> copiarListaProfunda(ArrayList<Componente> listaOriginal) {
           ArrayList<Componente> listaCopia = new ArrayList<Componente>();
           for (Componente c : listaOriginal) {
                   Componente copiaComponente = null;
                   try {
                           copiaComponente = (Componente) c.clone();
                   } catch (CloneNotSupportedException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                   }
                   listaCopia.add(copiaComponente);
           }
           return listaCopia;
   }
   
	public void inicializar() {
		ArrayList<Componente> copiaFactura = new ArrayList<Componente>();
		for (Componente c : componentesFactura) {
		    try {
		        copiaFactura.add((Componente) c.clone());
		    } catch (CloneNotSupportedException e2) {
		        e2.printStackTrace();
		    }
		}
		for (Componente selected : copiaFactura) {
		    int cantidad = selected.getStock();
		    Componente componente = buscarComponenteBySerie(selected.getNumSerie());
		    componente.setStock(componente.getStock() + cantidad);
		    reescribirComponete(componente);
		    componentesFactura.remove(selected);
		}
		ArrayList<Combo> copiaFacturaCombo = new ArrayList<Combo>();
		for (Combo c : combosFactura) {
		    try {
		        copiaFacturaCombo.add((Combo) c.clone());
		    } catch (CloneNotSupportedException e3) {
		        e3.printStackTrace();
		    }
		}
		componentesFactura.clear();
		combosFactura.clear();
		
	}
	public void clean(){
		componentesFactura.clear();
		combosFactura.clear();
		CedulatextField.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		SerietextField.setText("");
		txtNombre.setEditable(false);;
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
	}
	
	public ArrayList<Componente> copiarArraytemp (){
		
		ArrayList<Componente> copia = new ArrayList<Componente>(componentesFactura.size());
		for (Componente comp : componentesFactura) {
			try {
		    copia.add((Componente) comp.clone());
			}catch (CloneNotSupportedException e) {
				// TODO: handle exception
			}
		}
		
		
		return copia;
	}
	public ArrayList<Combo> copiarArraytempCombos (){
		
		ArrayList<Combo> copia = new ArrayList<Combo>(combosFactura.size());
		for (Combo comb : combosFactura) {
			try {
		    copia.add((Combo) comb.clone());
			}catch (CloneNotSupportedException e) {
				// TODO: handle exception
			}
		}
		return copia;
	}
}


