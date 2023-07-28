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
import logico.Tienda;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ListUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object rows[];
	private final JLabel lblListadoDeUsuarios = new JLabel("Listado de Usuarios:");
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnCancelar;
	private User selected = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListUsuario dialog = new ListUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListUsuario() {
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Codigo", "Nombre", "Usuario", "Tipo"};

					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int ind = table.getSelectedRow();
							if (ind >= 0) {
								btnDelete.setEnabled(true);
								btnUpdate.setEnabled(true);
								String userId = table.getValueAt(ind, 0).toString();
								 selected = Tienda.getInstance().UserById(userId);
							}//listo
						}
					});
					scrollPane.setViewportView(table);

					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);
				}
			}
		}
		contentPanel.add(lblListadoDeUsuarios, BorderLayout.NORTH);
		{
			JPanel buttonPane = new JPanel();
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
								"Estas seguro de querer eliminar el usuario?",
								"Eliminar Usuario", JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION) {
							Tienda.getInstance().eliminarUsuario(selected);
							loadUsers();
						}
					}
				}
			});
			buttonPane.add(btnDelete);
		}
		
		{
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBackground(new Color(102, 0, 255));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			{
			    btnUpdate = new JButton("Modificar");
			    btnUpdate.setBackground(new Color(102, 0, 255));
			    btnUpdate.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			            if (selected != null) {
			                ModificarUsuario modificarUsuarioDialog = new ModificarUsuario(selected);
			                modificarUsuarioDialog.setModal(true);
			                modificarUsuarioDialog.setVisible(true);
			                loadUsers();
			            }
			        }
			    });
			    btnUpdate.setEnabled(false);
			    buttonPane.add(btnUpdate);
			}
			btnCancelar.setActionCommand("Cancel");
			buttonPane.add(btnCancelar);
		}
	}
		loadUsers();
	}
	
	//listo

	public static void loadUsers() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		for (User user : Tienda.getInstance().getMisUsuarios()) {
			rows[0] = user.getUserId();
			rows[1] = user.getName();
			rows[2] = user.getUserName();
			rows[3] = user.getTipo();
			model.addRow(rows);
		}
	}

}
