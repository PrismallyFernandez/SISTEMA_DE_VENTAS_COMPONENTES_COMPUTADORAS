package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Componente;
import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Micro;
import logico.Motherboard;
import logico.Tienda;

public class ModComponente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JTextField textSerial;
	private JTextField textDescripcion;
	private JSpinner spnPrecio;
	private JSpinner spnCantidad;

	private JRadioButton rdbMicro;
	private JRadioButton rdbDiscoDuro;
	private JRadioButton rdbMotherBoard;
	private JRadioButton rdbMemoriaRam;
	private JPanel pnlPrueba;

	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textSocket;
	private JComboBox<String> cmboxConexiones;
	private JComboBox<String> cmboxRam;


	private JTextField txtGb;
	private JSpinner spnCapacidad;

	private JSpinner spnVelocidad;
	private JTextField txtGhz;

	private JLabel lblTipoDeRam;
	private JLabel lblSocket;
	private JLabel lblConexiones;
	private JLabel lblCapacidad;
	private JLabel lblVelocidad;
	private JTextField textConexiones;


	public static void main(String[] args) {
		try {
			ModComponente dialog = new ModComponente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	ModComponente(String Serial) {
		setTitle("MODIFICAR COMPONENTES");
		setResizable(false);
		setBounds(100, 100, 413, 676);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(5, 13, 391, 230);
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n General:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel);
			panel.setLayout(null);

			JLabel lblSerial = new JLabel("N\u00FAmero de Serie:");
			lblSerial.setBounds(12, 29, 132, 14);
			panel.add(lblSerial);

			textSerial = new JTextField();
			textSerial.setBackground(new Color(255, 255, 255));
			textSerial.setEditable(false);
			textSerial.setBounds(12, 44, 200, 20);
			panel.add(textSerial);
			textSerial.setColumns(10);

			JLabel lblDescripcion = new JLabel("Descripcion");
			lblDescripcion.setBounds(12, 77, 90, 14);
			panel.add(lblDescripcion);

			textDescripcion = new JTextField();
			textDescripcion.setBounds(12, 92, 200, 20);
			panel.add(textDescripcion);
			textDescripcion.setText((String) null);
			textDescripcion.setColumns(10);

			JLabel lblPrecioBase = new JLabel("Precio Base:");
			lblPrecioBase.setBounds(12, 120, 72, 14);
			panel.add(lblPrecioBase);

			spnPrecio = new JSpinner();
			spnPrecio.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
			spnPrecio.setBounds(12, 136, 200, 20);
			panel.add(spnPrecio);

			JLabel lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setBounds(12, 169, 72, 14);
			panel.add(lblCantidad);

			spnCantidad = new JSpinner();
			spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnCantidad.setBounds(12, 185, 200, 20);
			panel.add(spnCantidad);
		}

		pnlPrueba = new JPanel();
		pnlPrueba.setBackground(new Color(255, 255, 255));
		pnlPrueba.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPrueba.setLayout(null);
		pnlPrueba.setToolTipText("");
		pnlPrueba.setBounds(5, 387, 391, 202);
		contentPanel.add(pnlPrueba);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(12, 13, 90, 14);
		pnlPrueba.add(lblMarca);

		textMarca = new JTextField();
		textMarca.setBounds(12, 28, 128, 20);
		pnlPrueba.add(textMarca);
		textMarca.setText((String) null);
		textMarca.setColumns(10);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(201, 13, 110, 14);
		pnlPrueba.add(lblModelo);

		textModelo = new JTextField();
		textModelo.setBounds(201, 28, 128, 20);
		pnlPrueba.add(textModelo);
		textModelo.setText((String) null);
		textModelo.setColumns(10);

		lblTipoDeRam = new JLabel("Tipo De Ram:");
		lblTipoDeRam.setBounds(12, 83, 110, 14);
		pnlPrueba.add(lblTipoDeRam);

		cmboxRam = new JComboBox<String>();
		cmboxRam.setBounds(12, 101, 128, 22);
		pnlPrueba.add(cmboxRam);
		cmboxRam.setModel(new DefaultComboBoxModel<String>(new String[] {"<<Seleccione>>", "DDR", "DDR-2", "DDR-3", "DDR-4", "DDR-5"}));

		lblSocket = new JLabel("Socket:");
		lblSocket.setBounds(201, 83, 110, 14);
		pnlPrueba.add(lblSocket);

		textSocket = new JTextField();
		textSocket.setBounds(201, 102, 128, 20);
		pnlPrueba.add(textSocket);
		textSocket.setText((String) null);
		textSocket.setColumns(10);

		lblConexiones = new JLabel("Conexiones:");
		lblConexiones.setBounds(139, 135, 90, 14);
		pnlPrueba.add(lblConexiones);

		cmboxConexiones = new JComboBox<String>();
		cmboxConexiones.setBounds(139, 148, 128, 22);
		pnlPrueba.add(cmboxConexiones);
		cmboxConexiones.setModel(new DefaultComboBoxModel<String>(new String[] {"<<Seleccione>>", "IDE", "SATA", "SATA-2", "SATA-3"}));

		lblCapacidad = new JLabel("Capacidad:");
		lblCapacidad.setBounds(139, 83, 110, 14);
		pnlPrueba.add(lblCapacidad);

		spnCapacidad = new JSpinner();
		spnCapacidad.setBounds(139, 102, 128, 20);
		pnlPrueba.add(spnCapacidad);
		spnCapacidad.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		txtGb = new JTextField();
		txtGb.setBounds(266, 102, 29, 20);
		pnlPrueba.add(txtGb);
		txtGb.setColumns(10);
		txtGb.setEditable(false);
		txtGb.setText("GB");

		lblVelocidad = new JLabel("Velocidad:");
		lblVelocidad.setBounds(12, 83, 81, 14);
		pnlPrueba.add(lblVelocidad);

		spnVelocidad = new JSpinner();
		spnVelocidad.setBounds(12, 101, 98, 22);
		pnlPrueba.add(spnVelocidad);
		spnVelocidad.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));

		txtGhz = new JTextField();
		txtGhz.setBounds(111, 102, 29, 20);
		pnlPrueba.add(txtGhz);
		txtGhz.setText("Ghz");
		txtGhz.setEditable(false);
		txtGhz.setColumns(10);

		textConexiones = new JTextField();
		textConexiones.setBounds(139, 149, 128, 20);
		pnlPrueba.add(textConexiones);
		textConexiones.setText((String) null);
		textConexiones.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Componente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(5, 256, 391, 118);
		contentPanel.add(panel);
		panel.setLayout(null);
		Componente comp = Tienda.getInstance().ComponenteByCodigo(Serial);
		textSerial.setText(Serial);

		if(comp instanceof Motherboard){
			rdbMotherBoard = new JRadioButton("MotherBoard");

			rdbMotherBoard.setSelected(true);
			textMarca.setText(comp.getMarca());
			textModelo.setText(comp.getModelo());
			textDescripcion.setText(comp.getDescripcion());
			spnPrecio.setValue(comp.getPrecio());
			spnCantidad.setValue(comp.getStock());

			lblTipoDeRam.setVisible(true);
			cmboxRam.setVisible(true);
			cmboxRam.setSelectedItem(((Motherboard) comp).getTipo());

			lblSocket.setVisible(true);
			textSocket.setVisible(true);
			lblSocket.setEnabled(true);
			textSocket.setEnabled(true);
			textSocket.setText(((Motherboard) comp).getSocket());


			cmboxConexiones.setVisible(false);
			textConexiones.setVisible(true);
			lblConexiones.setVisible(true);
			textConexiones.setText(((Motherboard) comp).getConexiones());

			lblVelocidad.setVisible(false);
			spnVelocidad.setVisible(false);

			lblCapacidad.setVisible(false);
			spnCapacidad.setVisible(false);

			txtGb.setVisible(false);
			txtGhz.setVisible(false);

			rdbMotherBoard.setSelected(true);
			rdbMotherBoard.setBounds(7, 25, 118, 23);
			panel.add(rdbMotherBoard);
		}
		if(comp instanceof DiscoDuro){
			rdbDiscoDuro = new JRadioButton("Disco Duro");
			rdbDiscoDuro.setSelected(true);

			textMarca.setText(comp.getMarca());
			textModelo.setText(comp.getModelo());
			textDescripcion.setText(comp.getDescripcion());
			spnPrecio.setValue(comp.getPrecio());
			spnCantidad.setValue(comp.getStock());

			cmboxConexiones.setVisible(true);
			cmboxConexiones.setSelectedItem(((DiscoDuro) comp).getTipoConexion());
			textConexiones.setVisible(false);
			lblConexiones.setVisible(true);

			lblCapacidad.setBounds(139, 83, 110, 14);
			spnCapacidad.setBounds(139, 102, 128, 20);
			txtGb.setBounds(266, 102, 29, 20);

			lblCapacidad.setVisible(true);
			spnCapacidad.setVisible(true);
			spnCapacidad.setValue(((DiscoDuro) comp).getCapacidad());

			lblTipoDeRam.setVisible(false);
			cmboxRam.setVisible(false);

			lblSocket.setVisible(false);
			textSocket.setVisible(false);

			lblVelocidad.setVisible(false);
			spnVelocidad.setVisible(false);

			txtGb.setVisible(true);
			txtGhz.setVisible(false);


			rdbDiscoDuro.setBounds(202, 25, 109, 23);
			panel.add(rdbDiscoDuro);
		}
		if(comp instanceof Micro){
			rdbMicro = new JRadioButton("Micro-Procesador");
			rdbMicro.setSelected(true);

			textMarca.setText(comp.getMarca());
			textModelo.setText(comp.getModelo());
			textDescripcion.setText(comp.getDescripcion());
			spnPrecio.setValue(comp.getPrecio());
			spnCantidad.setValue(comp.getStock());

			lblTipoDeRam.setVisible(false);
			cmboxRam.setVisible(false);

			lblSocket.setVisible(true);
			textSocket.setVisible(true);
			textSocket.setText(((Micro) comp).getSocket());

			cmboxConexiones.setVisible(false);
			lblConexiones.setVisible(false);
			textConexiones.setVisible(false);

			lblVelocidad.setVisible(true);
			spnVelocidad.setVisible(true);
			spnVelocidad.setValue(((Micro) comp).getVelocidad());

			lblCapacidad.setVisible(false);
			spnCapacidad.setVisible(false);

			txtGb.setVisible(false);
			txtGhz.setVisible(true);

			rdbMicro.setBounds(7, 69, 152, 23);
			panel.add(rdbMicro);
		}
		if(comp instanceof MemoriaRam){
			rdbMemoriaRam = new JRadioButton("Memoria Ram");
			rdbMemoriaRam.setSelected(true);

			textMarca.setText(comp.getMarca());
			textModelo.setText(comp.getModelo());
			textDescripcion.setText(comp.getDescripcion());
			spnPrecio.setValue(comp.getPrecio());
			spnCantidad.setValue(comp.getStock());

			lblTipoDeRam.setVisible(true);
			cmboxRam.setVisible(true);
			cmboxRam.setSelectedItem(((MemoriaRam) comp).getTipo());

			lblSocket.setVisible(false);
			textSocket.setVisible(false);

			cmboxConexiones.setVisible(false);
			lblConexiones.setVisible(false);
			textConexiones.setVisible(false);

			lblVelocidad.setVisible(false);
			spnVelocidad.setVisible(false);

			lblCapacidad.setBounds(201, 83, 110, 14);
			spnCapacidad.setBounds(201, 102, 128, 20);
			lblCapacidad.setVisible(true);
			spnCapacidad.setVisible(true);
			spnCapacidad.setValue(((MemoriaRam) comp).getCapacidad());

			txtGb.setBounds(326, 102, 29, 20);
			txtGb.setVisible(true);
			txtGhz.setVisible(false);


			rdbMemoriaRam.setBounds(202, 69, 127, 23);
			panel.add(rdbMemoriaRam);
		}	

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				JButton btnregistrar = new JButton("REGISTRAR");
				btnregistrar.setBackground(new Color(51, 204, 153));
				btnregistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (verificarCampos()) {
							Componente aux = null;
							String modelo = textModelo.getText();
							String marca = textMarca.getText();
							String descripcion = textDescripcion.getText();
							Float precio = Float.valueOf(spnPrecio.getValue().toString());
							int stock = Integer.valueOf((Integer) spnCantidad.getValue());
							String serial = textSerial.getText();

							if(comp instanceof Motherboard){
								String socket = textSocket.getText();
								String tipo = (String) cmboxRam.getSelectedItem();
								String conexion = textConexiones.getText(); 
								aux = new Motherboard(serial, stock, descripcion, precio, modelo, marca, socket, tipo, conexion);
							}
							if(comp instanceof DiscoDuro){
								int capacidad = Integer.valueOf((Integer) spnCapacidad.getValue()); 
								String conexion = (String)cmboxConexiones.getSelectedItem();
								aux = new DiscoDuro(serial, stock, descripcion, precio, modelo, marca, capacidad, conexion);
							}
							if(comp instanceof Micro){
								String socket = textSocket.getText();
								Float velocidad = Float.valueOf((Float) spnVelocidad.getValue());
								aux = new Micro(serial, stock, descripcion, precio, modelo, marca, socket, velocidad);
							}
							if(comp instanceof MemoriaRam){
								String tipo = (String) cmboxRam.getSelectedItem();
								int capacidad = Integer.valueOf((Integer) spnCapacidad.getValue()); 
								aux = new MemoriaRam(serial, stock, descripcion, precio, modelo, marca, capacidad, tipo);
							}
							guardar(aux);
							JOptionPane.showMessageDialog(null, "Modificación Exitosa", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
			}
			{
				JButton btncancelar = new JButton("CANCELAR");
				btncancelar.setBackground(new Color(102, 0, 255));
				btncancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btncancelar.setActionCommand("Cancel");
				buttonPane.add(btncancelar);
			}
		}

	}

	private boolean verificarCampos() {

		if (rdbMotherBoard.isSelected()) {
			return !textMarca.getText().isEmpty() && !textModelo.getText().isEmpty() &&
					!textDescripcion.getText().isEmpty() && !textSocket.getText().isEmpty() &&
					!textConexiones.getText().isEmpty();
		} else if (rdbDiscoDuro.isSelected()) {
			return !textMarca.getText().isEmpty() && !textModelo.getText().isEmpty() &&
					!textDescripcion.getText().isEmpty() && !txtGb.getText().isEmpty() &&
					!cmboxConexiones.getSelectedItem().equals("<<Seleccione>>");
		} else if (rdbMicro.isSelected()) {
			return !textMarca.getText().isEmpty() && !textModelo.getText().isEmpty() &&
					!textDescripcion.getText().isEmpty() && !txtGhz.getText().isEmpty() &&
					!textSocket.getText().isEmpty();
		} else if (rdbMemoriaRam.isSelected()) {
			return !textMarca.getText().isEmpty() && !textModelo.getText().isEmpty() &&
					!textDescripcion.getText().isEmpty() && !txtGb.getText().isEmpty() &&
					!cmboxRam.getSelectedItem().equals("<<Seleccione>>");
		}
		return false;
	}

	private void guardar(Componente comp) {
		for (Componente aux : Tienda.getInstance().getMisComponentes()) {
			if (aux.getNumSerie().equalsIgnoreCase(comp.getNumSerie())) {
				aux.setMarca(comp.getMarca());
				aux.setModelo(comp.getModelo());
				aux.setDescripcion(comp.getDescripcion());
				aux.setNumSerie(comp.getNumSerie());
				aux.setPrecio(comp.getPrecio());
				aux.setStock(comp.getStock());
			}
		}
	}
}
