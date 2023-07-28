package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.Color;

public class ListadoCombo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private JButton btnDelete;
	private JButton btnCancelar;
	private Combo selected = null;
	private JButton btnUpdate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoCombo dialog = new ListadoCombo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoCombo() {
		setResizable(false);
		System.out.println(Tienda.getInstance().getMisCombos().size());
		setBounds(100, 100, 972, 345);
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
				JLabel lblCombos = new JLabel("Listado de Combos:");
				lblCombos.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblCombos.setHorizontalAlignment(SwingConstants.LEFT);
				panel.add(lblCombos);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Componentes","Nombre","C�digo","Precio", "Stock",};
					
					table = new JTable();
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPane.setViewportView(table);
					table.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int ind = table.getSelectedRow();
							if (ind >= 0 ) {
								btnDelete.setEnabled(true);
								btnUpdate.setEnabled(true);
								String codigo = table.getValueAt(ind, 2).toString();
								selected = Tienda.getInstance().CombobyCodigo(codigo);
							}
						}
					});
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);

				}
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
				btnDelete.setForeground(new Color(0, 0, 0));
				btnDelete.setBackground(new Color(204, 0, 0));
				btnDelete.setEnabled(false);
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							int option = JOptionPane.showConfirmDialog(null,
									"Estas seguro de querer eliminar el Companente",
									"Eliminar Componente", JOptionPane.OK_CANCEL_OPTION);
							if(option == JOptionPane.OK_OPTION) {
								EliminarCombo(selected);
								load(0);
							}
						}
					}
				});
				buttonPane.add(btnDelete);
			}
			
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setForeground(new Color(0, 0, 0));
				btnCancelar.setBackground(new Color(102, 0, 255));
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnUpdate = new JButton("Modificar");
					btnUpdate.setForeground(new Color(0, 0, 0));
					btnUpdate.setBackground(new Color(102, 0, 255));
					btnUpdate.setEnabled(false);
					btnUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {							
							ModificarCombo list = new ModificarCombo(selected.getCodigo());
							list.setModal(true);
							list.setVisible(true);
							load(0);
						}
					});
					buttonPane.add(btnUpdate);
				}
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			
			}
		}
		load(0);
	}

	public void load(int index) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int [] cont= {0,0,0,0};
		if(index == 0){
			for (Combo aux : Tienda.getInstance().getMisCombos()) {
				cont=contador(aux);
				rows[0] = "MDR: "+cont[0]+" - DIS: " + cont[1] + " - MIC: "+cont[2]+ " - MER: "+cont[3];
				rows[1] = aux.getNombre();
				rows[2] = aux.getCodigo();
				rows[3] = aux.getPrecio();
				rows[4] = aux.getStock();
				model.addRow(rows);

			}
		}
	}
	
	public static int[] contador(Combo aux) {
		int[] cont= {0,0,0,0};
		for (Componente comp : aux.getMisComponentes()) {
			if(comp instanceof Motherboard){
				cont[0]++;
			}
			if(comp instanceof DiscoDuro){
				cont[1]++;
			}
			if(comp instanceof Micro){
				cont[2]++;
			}
			if(comp instanceof MemoriaRam){
				cont[3]++;
			}
		}
		return cont;
	}
	
	public void EliminarCombo(Combo comb) {
		ArrayList<Combo> temporal = Tienda.getInstance().getMisCombos();
		for(Combo aux : temporal) {
			if(aux.getCodigo().equalsIgnoreCase(comb.getCodigo())) {
				Tienda.getInstance().getMisCombos().remove(comb);
			}
		}
	}
	
}
