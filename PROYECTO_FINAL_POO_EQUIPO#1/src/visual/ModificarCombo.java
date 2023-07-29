package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Combo;
import logico.Componente;
import logico.DiscoDuro;
import logico.MemoriaRam;
import logico.Micro;
import logico.Motherboard;
import logico.Tienda;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class ModificarCombo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static DefaultTableModel model_1;


	private static Object rows[];
	private JComboBox<String> comboBox;
	private JButton btnCancelar;
	private Componente selected = null;
	private Componente selected_1 = null;
	private JButton btnAgregar;
	private JPanel pnlAgregar;
	private JTextPane textPnlAviso;
	private JSpinner spnAgregar;
	private JPanel pnlRemover;
	private JTextPane txtpnCuntosArtculosDeseas;
	private JSpinner spnRemover;
	private JButton btnCrear;
	private JButton btnRemover;
	private ArrayList <Componente> temporal = FacturarComplejo.copiarPrueba();
	private JButton btnAceptar;
	private JButton btnAceptar_1;
	private JTable table_1;
	private JSpinner spnTotal;
	private JLabel lblTotal;
	private JSpinner spnSubtotal;
	private JLabel lblSubtotal;
	private JLabel lblConDe;
	private JTextField textNombreCombo;
	private JTextField textSerial;
	private JLabel lblStock;
	private JSpinner spnStock;
	private String codigo;

	public static void main(String[] args) {
		try {
			ModificarCombo dialog = new ModificarCombo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public ModificarCombo(String sele) {
		setResizable(false);
		Combo copy = Tienda.getInstance().CombobyCodigo(sele);

		Combo combo= new Combo (copiarListaProfunda(copy.getMisComponentes()), null, null, 0,0);

		setBounds(100, 100, 845, 736);
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
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 15));
			{
				JLabel lblTipoDeQueso = new JLabel("Tipo de Componente:");
				lblTipoDeQueso.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblTipoDeQueso);
			}
			{
				comboBox = new JComboBox<String>();
				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						load(comboBox.getSelectedIndex());

					}
				});
				comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"<Todos>", "MotherBoards", "Microprocesadores", "Memorias RAM", "Discos Duros"}));
				panel.add(comboBox);
			}
		}


		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 45, 822, 218);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			panel.add(scrollPane);
			{
				String[] headers = {"Numero de serie","Marca","Modelo", "Cantidad", "Precio", "Tipo de Componente"};

				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int ind = table.getSelectedRow();
						if (ind >= 0 ) {
							clean ();
							btnAgregar.setEnabled(true);
							btnRemover.setEnabled(false);
							pnlAgregar.setVisible(false);
							pnlRemover.setVisible(false);
							String codigo = table.getValueAt(ind, 0).toString();
							selected = temporalByCodigo(codigo);
						}

					}

				});
				model = new DefaultTableModel();
				model.setColumnIdentifiers(headers);
				table.setModel(model);

			}

		}

		{
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBackground(new Color(51, 204, 153));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selected.getStock() > 0) {
						pnlAgregar.setVisible(true);
						btnAgregar.setVisible(false);
						pnlAgregar.setBounds(296, 211, 229, 172);
						panel.setComponentZOrder(pnlAgregar, 0);
					}
					else {
						JOptionPane.showMessageDialog(null, "Stock Vacío!!!", "AVISO", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			});
			btnAgregar.setEnabled(false);
			btnAgregar.setBounds(286, 270, 97, 25);
			panel.add(btnAgregar);
		}
		{
			pnlAgregar = new JPanel();
			pnlAgregar.setLayout(null);
			pnlAgregar.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cantidad...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlAgregar.setBackground(SystemColor.info);
			pnlAgregar.setBounds(834, 13, 229, 172);
			panel.add(pnlAgregar);
			{
				textPnlAviso = new JTextPane();
				textPnlAviso.setText("¿Cu\u00E1ntos art\u00EDculos deseas a\u00F1adir al combo?\r\n\r\nNo deben exceder la cantidad actual en existencia!");
				textPnlAviso.setEditable(false);
				textPnlAviso.setBackground(SystemColor.info);
				textPnlAviso.setBounds(12, 32, 179, 86);
				pnlAgregar.add(textPnlAviso);
			}
			{
				spnAgregar = new JSpinner();
				spnAgregar.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnAgregar.setBounds(12, 131, 95, 22);
				pnlAgregar.add(spnAgregar);
			}

			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					int temp = Integer.valueOf((Integer)spnAgregar.getValue());

					if (temp <= selected.getStock()) {
						try {
							selected_1 = Tienda.getInstance().copiarComp(selected);
						} catch (CloneNotSupportedException e1) {
							e1.printStackTrace();
						}

						boolean control = existencia(selected_1,temp, combo);

						if (!control) {	
							combo.getMisComponentes().add(selected_1);
							modCombo(selected_1.getNumSerie(),temp, combo);
						}

						if (temp < selected.getStock()) {
							agregar(selected, combo, 1, temp);
						}
						if (temp == selected.getStock()) {
							agregar(selected, combo, 2, temp);
						}

						pnlAgregar.setVisible(false);
						btnAgregar.setVisible(true);

						if (combo.getMisComponentes().size() > 0) {
							load2(combo);
							btnCrear.setEnabled(true);
						}
						load(0);
						spnAgregar.setValue(0);
					}

					pnlAgregar.setVisible(false);
					btnAgregar.setVisible(true);
					btnAgregar.setEnabled(false);
					clean();

				}
			});
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnAceptar.setBounds(129, 143, 88, 22);
			pnlAgregar.add(btnAceptar);
		}

		{
			pnlRemover = new JPanel();
			pnlRemover.setLayout(null);
			pnlRemover.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cantidad...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlRemover.setBackground(SystemColor.info);
			pnlRemover.setBounds(834, 198, 229, 172);
			panel.add(pnlRemover);
			{

				txtpnCuntosArtculosDeseas = new JTextPane();
				txtpnCuntosArtculosDeseas.setText("Cu\u00E1ntos art\u00EDculos deseas remover del combo?\r\n\r\nNo deben exceder la cantidad actual en existencia!");
				txtpnCuntosArtculosDeseas.setEditable(false);
				txtpnCuntosArtculosDeseas.setBackground(SystemColor.info);
				txtpnCuntosArtculosDeseas.setBounds(12, 32, 179, 86);
				pnlRemover.add(txtpnCuntosArtculosDeseas);

			}
			{
				spnRemover = new JSpinner();
				spnRemover.setModel(new SpinnerNumberModel(1, 1, null, 1));
				spnRemover.setBounds(12, 131, 95, 22);
				pnlRemover.add(spnRemover);
			}
			{
				btnAceptar_1 = new JButton("Aceptar");
				btnAceptar_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int temp = Integer.valueOf((Integer)spnRemover.getValue());
						boolean control = modTemporal(selected_1, temp);

						if (!control) {	
							temporal.add(selected_1);
							modOficial(selected.getNumSerie(),temp);
						}

						if (temp < selected_1.getStock()) {
							remover(selected_1, 1, temp, combo);
						}
						if (temp == selected_1.getStock()) {
							remover(selected_1, 2, temp, combo);
						}

						pnlRemover.setVisible(false);
						btnRemover.setVisible(true);
						btnRemover.setEnabled(false);
						load(0);
						load2(combo);
						if (combo.getMisComponentes().size() > 0) {
							btnCrear.setEnabled(true);
						}
						clean();
					}
				});
				btnAceptar_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				btnAceptar_1.setBounds(129, 137, 88, 22);
				pnlRemover.add(btnAceptar_1);
			}
		}
		{
			btnRemover = new JButton("Remover");
			btnRemover.setBackground(new Color(204, 0, 0));
			btnRemover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnlRemover.setVisible(true);
					btnRemover.setVisible(false);
					pnlRemover.setBounds(296, 211, 229, 172);
					panel.setComponentZOrder(pnlRemover, 0);
					spnRemover.setValue(selected_1.getStock());
				}
			});
			btnRemover.setEnabled(false);
			btnRemover.setBounds(417, 270, 97, 25);
			panel.add(btnRemover);
		}
		{
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setBounds(0, 300, 822, 218);
			panel.add(scrollPane_1);
			{
				String[] headers = {"Numero de serie","Marca","Modelo", "Cantidad", "Precio", "Tipo de Componente"};

				table_1 = new JTable();
				table_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int ind = table_1.getSelectedRow();
						if (ind >= 0 ) {
							clean ();
							btnRemover.setEnabled(true);
							btnAgregar.setEnabled(false);
							pnlAgregar.setVisible(false);
							pnlRemover.setVisible(false);
							String codigo = table_1.getValueAt(ind, 0).toString();
							selected_1 = Buscar(codigo,combo);
						}
						spnRemover.setModel(new SpinnerNumberModel(1, 1, selected_1.getStock(), 1));

					}
				});
				table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane_1.setViewportView(table_1);

				model_1 = new DefaultTableModel();
				model_1.setColumnIdentifiers(headers);
				table_1.setModel(model_1);
			}
		}
		{
			{
				spnTotal = new JSpinner();
				spnTotal.setEnabled(false);
				spnTotal.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnTotal.setBounds(685, 547, 97, 22);
				panel.add(spnTotal);
			}
			{
				lblTotal = new JLabel("Total:");
				lblTotal.setBounds(647, 550, 56, 16);
				panel.add(lblTotal);
			}
			{
				spnSubtotal = new JSpinner();
				spnSubtotal.setEnabled(false);
				spnSubtotal.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
				spnSubtotal.setBounds(125, 547, 97, 22);
				panel.add(spnSubtotal);
			}
			{
				lblSubtotal = new JLabel("SubTotal:");
				lblSubtotal.setBounds(64, 550, 62, 16);
				panel.add(lblSubtotal);
			}
			{
				lblConDe = new JLabel("Con 10% de DESCUENTO ---------->");
				lblConDe.setHorizontalAlignment(SwingConstants.CENTER);
				lblConDe.setForeground(SystemColor.textHighlight);
				lblConDe.setBackground(SystemColor.info);
				lblConDe.setBounds(336, 550, 229, 16);
				panel.add(lblConDe);
			}
		}

		JLabel lblNombre = new JLabel("Nombre del combo:");
		lblNombre.setBounds(5, 16, 113, 16);
		panel.add(lblNombre);

		textNombreCombo = new JTextField();
		textNombreCombo.setBounds(120, 13, 179, 22);
		panel.add(textNombreCombo);
		textNombreCombo.setColumns(10);

		JLabel lblNumSer = new JLabel("N\u00FAmero de serie:");
		lblNumSer.setBounds(601, 16, 113, 16);
		panel.add(lblNumSer);

		textSerial = new JTextField();
		textSerial.setText(codigo);
		textSerial.setEditable(false);
		textSerial.setBounds(706, 13, 99, 22);
		panel.add(textSerial);
		textSerial.setColumns(10);
		{
			lblStock = new JLabel("Cantidad a Registrar:");
			lblStock.setBounds(336, 16, 130, 16);
			panel.add(lblStock);
		}

		spnStock = new JSpinner();
		spnStock.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spnStock.setBounds(458, 13, 87, 22);
		panel.add(spnStock);
		{

			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				btnCancelar = new JButton("CANCELAR");
				btnCancelar.setBackground(new Color(102, 0, 255));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnCrear = new JButton("CREAR");
					btnCrear.setBackground(new Color(51, 204, 153));
					btnCrear.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (textNombreCombo.getText().isEmpty() || textSerial.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Debe llenar todos los campos antes de crear el combo.",
										"Campos vacíos", JOptionPane.ERROR_MESSAGE);
							} else {
								Stock(combo);
								guardar(combo, combo);
								JOptionPane.showMessageDialog(null, "Registro Exitoso", "AVISO", JOptionPane.INFORMATION_MESSAGE);
								load(0);
								load2(combo);
							}
						}
					});
					buttonPane.add(btnCrear);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		spnTotal.setValue(copy.getPrecio());
		textNombreCombo.setText(copy.getNombre());
		textSerial.setText(copy.getCodigo());
		spnStock.setValue(copy.getStock());
		spnSubtotal.setValue((copy.getPrecio() * 0.1) + copy.getPrecio());
		load(0);
		load2(combo);
		pnlAgregar.setVisible(false);
		pnlRemover.setVisible(false);
	}

	public void load(int index) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		if(index == 0){
			for (Componente aux : temporal) {

				rows[0] = aux.getNumSerie();
				rows[1] = aux.getMarca();
				rows[2] = aux.getModelo();
				rows[3] = aux.getStock();
				rows[4] = aux.getPrecio();
				if(aux instanceof DiscoDuro ){
					rows[5] = "Disco Duro";	
				}
				if(aux instanceof MemoriaRam){
					rows[5] = "Memoria Ram";	
				}
				if(aux instanceof Micro ){
					rows[5] = "Microprocesador";
				}
				if(aux instanceof Motherboard ){
					rows[5] = "MotherBoard";
				}
				model.addRow(rows);

			}
		}

		if(index == 1){
			for (Componente aux : temporal) {
				if(aux instanceof Motherboard){
					rows[0] = aux.getNumSerie();
					rows[1] = aux.getMarca();
					rows[2] = aux.getModelo();
					rows[3] = aux.getStock();
					rows[4] = aux.getPrecio();
					rows[5] = "MotherBoard";	
					model.addRow(rows);
				}
			}	
		}
		if(index == 2){
			for (Componente aux : temporal) {
				if(aux instanceof Micro){
					rows[0] = aux.getNumSerie();
					rows[1] = aux.getMarca();
					rows[2] = aux.getModelo();
					rows[3] = aux.getStock();
					rows[4] = aux.getPrecio();
					rows[5] = "Miroprocesador";	
					model.addRow(rows);
				}
			}	
		}
		if(index==3){
			for (Componente aux : temporal) {
				if(aux instanceof MemoriaRam){
					rows[0] = aux.getNumSerie();
					rows[1] = aux.getMarca();
					rows[2] = aux.getModelo();
					rows[3] = aux.getStock();
					rows[4] = aux.getPrecio();
					rows[5] = "Memoria Ram";	
					model.addRow(rows);
				}
			}	
		}
		if(index==4){
			for (Componente aux :temporal) {
				if(aux instanceof DiscoDuro){
					rows[0] = aux.getNumSerie();
					rows[1] = aux.getMarca();
					rows[2] = aux.getModelo();
					rows[3] = aux.getStock();
					rows[4] = aux.getPrecio();
					rows[5] = "Disco Duro";	
					model.addRow(rows);
				}
			}	
		}
	}

	public void load2(Combo combo){
		float precio = (Float)spnTotal.getValue();
		int stock = (int) spnStock.getValue();
		combo.setNombre(textNombreCombo.getText());
		combo.setCodigo(textSerial.getText());
		combo.setPrecio(precio);
		combo.setStock(stock);

		model_1.setRowCount(0);
		rows = new Object[model_1.getColumnCount()];

		for (Componente aux :combo.getMisComponentes()) {

			rows[0] = aux.getNumSerie();
			rows[1] = aux.getMarca();
			rows[2] = aux.getModelo();
			rows[3] = aux.getStock();
			rows[4] = aux.getPrecio();
			if(aux instanceof DiscoDuro ){
				rows[5] = "Disco Duro";	
			}
			if(aux instanceof MemoriaRam){
				rows[5] = "Memoria Ram";	
			}
			if(aux instanceof Micro ){
				rows[5] = "Microprocesador";
			}
			if(aux instanceof Motherboard ){
				rows[5] = "MotherBoard";
			}
			model_1.addRow(rows);

		}

	}


	public boolean modTemporal(Componente sel, int temp) {
		for (Componente componente : temporal) {
			if (componente.getNumSerie().equalsIgnoreCase(sel.getNumSerie())) {
				componente.setStock(sel.getStock() + temp);
				return true;
			}
		}
		return false;
	}

	public void modCombo(String serial, int stock, Combo combo) {
		float descuento = 0;
		for (Componente componente : combo.getMisComponentes()) {
			if (componente.getNumSerie().equalsIgnoreCase(serial)) {
				componente.setStock(stock);
				spnSubtotal.setValue((Float.valueOf((Float) spnSubtotal.getValue()))+(componente.getPrecio() * stock));
				descuento = (float) spnSubtotal.getValue();
				descuento = (float) (descuento - (descuento * 0.1));
				spnTotal.setValue(descuento);
			}
		}
	}
	public void modOficial(String serial, int stock) {
		for (Componente componente : temporal) {
			if (componente.getNumSerie().equalsIgnoreCase(serial)) {
				componente.setStock(stock);
			}
		}
	}

	public boolean existencia(Componente aux, int temp, Combo combo) {
		float descuento = 0;
		for (Componente componente : combo.getMisComponentes()) {
			if (componente.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
				componente.setStock(componente.getStock()+temp);
				spnSubtotal.setValue((Float.valueOf((Float) spnSubtotal.getValue()))+(componente.getPrecio() * temp));
				descuento = (float) spnSubtotal.getValue();
				descuento = (float) (descuento - (descuento * 0.1));
				spnTotal.setValue(descuento);
				return true;
			}
		}
		return false;
	}

	public void clean () {
		spnAgregar.setValue(1);
		btnAgregar.setEnabled(false);
		btnRemover.setEnabled(false);
	}

	public Componente Buscar(String numSerie,Combo combo) {

		for (Componente componente : combo.getMisComponentes()) {
			if (componente.getNumSerie().equalsIgnoreCase(numSerie)) {
				return componente;
			}
		}
		return null;
	}
	public void remover(Componente aux, int control,int cant, Combo combo) {
		float descuento = 0;
		for (Componente comp : temporal) {
			if (comp.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
				if (control == 1) {
					aux.setStock(aux.getStock() - cant);
				}
				if (control == 2) {
					combo.getMisComponentes().remove(aux);

				}
			}
		}
		spnSubtotal.setValue((Float) spnSubtotal.getValue()-(cant*aux.getPrecio()));
		descuento = (float) spnSubtotal.getValue();
		descuento = (float) (descuento - (descuento * 0.1));
		spnTotal.setValue(descuento);

	}
	public void Stock(Combo combo) {
		int stock = (int) spnStock.getValue();
		int cant=0;
		for (Componente comp: Tienda.getInstance().getMisComponentes()) {
			for(Componente aux: combo.getMisComponentes()) {
				if (comp.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
					cant = aux.getStock();
					if (comp.getStock() >= (cant*stock)) {
						comp.setStock(comp.getStock() - (cant*stock));
					}
					else if(comp.getStock() < (cant*stock)) {
						JOptionPane.showMessageDialog(null, "No cuenta con Stock suficiente de los artï¿½culos solicitados", "AVISO", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	public Componente temporalByCodigo(String numSerie) {

		for (Componente componente : temporal) {
			if (componente.getNumSerie().equalsIgnoreCase(numSerie)) {
				return componente;
			}
		}
		return null;
	}

	public void guardar(Combo combo, Combo sele) {
		for (Componente comp: Tienda.getInstance().getMisComponentes()) {
			for(Componente aux: temporal) {
				if (comp.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
					comp.setStock(aux.getStock());
				}
			}
		}
		combo.setCodigo(codigo);
		combo.setNombre(textNombreCombo.getText());
		combo.setPrecio((float) spnTotal.getValue());
		sele = combo;


	}


	public ArrayList<Componente> copiarListaProfunda(ArrayList<Componente> listaOriginal) {
		ArrayList<Componente> listaCopia = new ArrayList<Componente>();
		for (Componente c : listaOriginal) {
			Componente copiaComponente = null;
			try {
				copiaComponente = (Componente) c.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			} 
			listaCopia.add(copiaComponente); 
		}
		return listaCopia;
	}

	public void cargar(Combo comb) {

	}

	public void agregar(Componente aux,Combo combo, int control,int cant) {
		for (Componente comp : combo.getMisComponentes()) {
			if (comp.getNumSerie().equalsIgnoreCase(aux.getNumSerie())) {
				if (control == 1) {
					aux.setStock(aux.getStock() - cant);
				}
				if (control == 2) {
					temporal.remove(aux);
				}
			}
		}
	}

}
