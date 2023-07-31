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
import logico.Factura;
import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ListadoFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private final JLabel lblListadoDeClientes = new JLabel("Listado de Facturas:");
	private JButton btnmodificar;
	private JButton btnDelete;
	private JButton btnCancelar;
	private Factura selected = null;
	private JTextField textFieldFacturaPorCodigo;
	private JTextField textFieldFacturaPorCliente;

	public static void main(String[] args) {
		try {
			ListadoFactura dialog = new ListadoFactura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ListadoFactura() {
		setTitle("LISTADO DE FACTURAS");
		setResizable(false);
		setBounds(100, 100, 800, 479);
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
				scrollPane.setBounds(2, 80, 768, 283);
				panel.add(scrollPane);
				{
					String[] headers = {"Código de Factura", "Cédula","Cliente","Cantidad de componentes","Cantidad de combos","Total","Fecha"};

					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						
						public void mouseClicked(MouseEvent e) {
							int ind = table.getSelectedRow();
							if (ind >= 0 ) {
								btnDelete.setEnabled(true);
								btnmodificar.setEnabled(true);
								String codigoFactura = table.getValueAt(ind, 0).toString();
								selected = Tienda.getInstance().getFacturaByCodigo(codigoFactura);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);

					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);
				}
			}
			{
				JLabel lblCodigoFacturaLabel = new JLabel("Codigo:");
				lblCodigoFacturaLabel.setBounds(12, 13, 44, 16);
				panel.add(lblCodigoFacturaLabel);
			}

			textFieldFacturaPorCodigo = new JTextField();
			textFieldFacturaPorCodigo.setColumns(10);
			textFieldFacturaPorCodigo.setBounds(12, 34, 116, 23);
			panel.add(textFieldFacturaPorCodigo);
			{
				JLabel lblClienteFacturaLabel = new JLabel("C\u00E9dula Cliente:");
				lblClienteFacturaLabel.setBounds(312, 13, 97, 16);
				panel.add(lblClienteFacturaLabel);
			}

			JButton btnBuscarFacturaPorCodigo = new JButton("Buscar");
			btnBuscarFacturaPorCodigo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String codigoFactura = textFieldFacturaPorCodigo.getText();
					String cedulaCliente = textFieldFacturaPorCliente.getText();
					loadFacturas(codigoFactura, cedulaCliente);
				}
			});
			btnBuscarFacturaPorCodigo.setBounds(140, 33, 97, 25);
			panel.add(btnBuscarFacturaPorCodigo);

			JButton btnBuscarFacturaPorCliente = new JButton("Buscar");
			btnBuscarFacturaPorCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String codigoFactura = textFieldFacturaPorCodigo.getText();
					String cedulaCliente = textFieldFacturaPorCliente.getText();
					loadFacturas(codigoFactura, cedulaCliente);
				}
			});
			btnBuscarFacturaPorCliente.setBounds(440, 33, 97, 25);
			panel.add(btnBuscarFacturaPorCliente);
			{
				textFieldFacturaPorCliente = new JTextField();
				textFieldFacturaPorCliente.setColumns(10);
				textFieldFacturaPorCliente.setBounds(312, 34, 116, 23);
				panel.add(textFieldFacturaPorCliente);
			}
			{
				JButton btnLimpiarFiltro = new JButton("Limpiar filtro");
				btnLimpiarFiltro.setBackground(new Color(51, 204, 153));
				btnLimpiarFiltro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textFieldFacturaPorCodigo.setText(""); 
						textFieldFacturaPorCliente.setText(""); 
						loadFacturas("", "");

					}
				});
				btnLimpiarFiltro.setBounds(629, 13, 131, 49);
				panel.add(btnLimpiarFiltro);
			}
		}
		lblListadoDeClientes.setBackground(new Color(255, 255, 255));
		contentPanel.add(lblListadoDeClientes, BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDelete = new JButton("ELIMINAR");
				btnDelete.setForeground(new Color(0, 0, 0));
				btnDelete.setBackground(new Color(204, 0, 0));
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							if (selected != null) {
								int option = JOptionPane.showConfirmDialog(null,
										"Â¿EstÃ¡s seguro de querer eliminar esta factura?",
										"Eliminar factura", JOptionPane.OK_CANCEL_OPTION);
								if (option == JOptionPane.OK_OPTION) {
									Tienda.getInstance().eliminarFactura(selected);
									loadClientes();
								}
							}
						}
					}
				});
				buttonPane.add(btnDelete);
			}

			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnmodificar = new JButton("MODIFICAR");
					btnmodificar.setForeground(new Color(0, 0, 0));
					btnmodificar.setBackground(new Color(102, 0, 255));
					btnmodificar.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					        int filaSeleccionada = table.getSelectedRow();
					        if (filaSeleccionada >= 0) {
					            String codigoFactura = table.getValueAt(filaSeleccionada, 0).toString();
					            Factura facturaSeleccionada = Tienda.getInstance().getFacturaByCodigo(codigoFactura);
					            if (facturaSeleccionada != null) {
					                dispose();
					                ModificarFactura modificarFacturaDialog = new ModificarFactura(facturaSeleccionada);
					                modificarFacturaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					                modificarFacturaDialog.setVisible(true);
					            } else {
					                JOptionPane.showMessageDialog(null, "No se encontró la factura seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
					            }
					        } else {
					            JOptionPane.showMessageDialog(null, "Seleccione una factura para modificar.", "Información", JOptionPane.INFORMATION_MESSAGE);
					        }
					    }
					});


					btnmodificar.setEnabled(false);
					buttonPane.add(btnmodificar);
				}

				JButton btnCancelar = new JButton("CANCELAR");
				btnCancelar.setForeground(new Color(0, 0, 0));
				btnCancelar.setBackground(new Color(102, 0, 255));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{

					JButton buttonInfo = new JButton("INFO (VER FACTURA)");
					buttonInfo.setForeground(new Color(0, 0, 0));
					buttonInfo.setBackground(new Color(51, 204, 153));
					buttonInfo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int selectedRow = table.getSelectedRow();
							if (selectedRow >= 0) {
								String codigoFactura = table.getValueAt(selectedRow, 0).toString();
								Factura selectedFactura = Tienda.getInstance().getFacturaByCodigo(codigoFactura);
								if (selectedFactura != null) {
									DetallesFacturaDialog detallesDialog = new DetallesFacturaDialog(selectedFactura);
									detallesDialog.setVisible(true);
								} else {
									JOptionPane.showMessageDialog(ListadoFactura.this, "Factura no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(ListadoFactura.this, "Seleccione una factura.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					});


					buttonInfo.setActionCommand("Cancel");
					buttonPane.add(buttonInfo);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		loadClientes();
	}

	public static void loadClientes() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for (Factura aux : Tienda.getInstance().getMisFacturas()) {
			if(aux != null){
				rows[0] = aux.getCodigo();
				rows[1] = aux.getCliente().getCedula();
				rows[2] = aux.getCliente().getNombre();
				rows[3] = aux.getMisComponentes().size();
				rows[4] = aux.getMisCombos().size();
				rows[5] = aux.getMonto();
				rows[6] = aux.getFecha();
				model.addRow(rows);
			}
		}
	}
	public static void loadFacturas(String codigoFactura, String cedulaCliente) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];

		for (Factura aux : Tienda.getInstance().getMisFacturas()) {
			if (aux != null) {
				if (codigoFactura.isEmpty() || aux.getCodigo().equals(codigoFactura)) {
					Cliente cliente = aux.getCliente();
					if (cedulaCliente.isEmpty() || cliente.getCedula().equals(cedulaCliente)) {
						rows[0] = aux.getCodigo();
						rows[1] = cliente.getCedula();
						rows[2] = cliente.getNombre();
						rows[3] = aux.getMisComponentes().size();
						rows[4] = aux.getMisCombos().size();
						rows[5] = aux.getMonto();
						rows[6] = aux.getFecha();
						model.addRow(rows);
					}
				}
			}
		}
	}

}
