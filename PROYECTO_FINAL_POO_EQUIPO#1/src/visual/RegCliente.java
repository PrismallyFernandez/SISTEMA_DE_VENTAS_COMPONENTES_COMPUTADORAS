package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.Cliente;
import logico.Tienda;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private Cliente selected;
	private JTextField txtCedula;
	private JTextField txtTelefono;

	public static void main(String[] args) {
		try {
			RegCliente dialog = new RegCliente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public RegCliente(Cliente cliente) {
		selected = cliente;

		if (selected == null) {
			setTitle("Registrar Cliente");
		} else {
			setTitle("Modificar Cliente");
		}

		setBounds(100, 100, 450, 265);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(15, 62, 69, 20);
				panel.add(lblNombre);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(81, 57, 330, 30);
				panel.add(txtNombre);
			}
			{
				JLabel lblDireccion = new JLabel("Direccion:");
				lblDireccion.setBounds(15, 102, 112, 20);
				panel.add(lblDireccion);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(81, 97, 330, 30);
				panel.add(txtDireccion);
			}

			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(15, 21, 69, 20);
			panel.add(lblCedula);

			txtCedula = new JTextField();
			txtCedula.setColumns(10);
			txtCedula.setBounds(81, 16, 146, 30);
			panel.add(txtCedula);

			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(15, 143, 112, 20);
			panel.add(lblTelefono);

			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(81, 138, 330, 30);
			panel.add(txtTelefono);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if (selected == null) {
					okButton.setText("Registrar");
				} else {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!camposLlenos()) {
							JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos", "Registro de cliente", JOptionPane.WARNING_MESSAGE);
							return;
						}

						if (selected == null) {
							if (Tienda.getInstance().ClienteByCedula(txtCedula.getText()) == null) {
								Cliente c = new Cliente(txtCedula.getText(), txtNombre.getText(), txtDireccion.getText(), txtTelefono.getText());
								Tienda.getInstance().registrarCliente(c);
								JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Registro de cliente", JOptionPane.INFORMATION_MESSAGE);
								clean();
							} else {
								JOptionPane.showMessageDialog(null, "Esta cédula ya está registrada", "Registro de cliente", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							selected.setNombre(txtNombre.getText());
							selected.setDireccion(txtDireccion.getText());
							selected.setTelefono(txtTelefono.getText());
							ListadoCliente.loadClientes(0);
							dispose();
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
			loadCliente();
		}
	}

	private boolean camposLlenos() {
		return !txtCedula.getText().isEmpty() &&
				!txtNombre.getText().isEmpty() &&
				!txtDireccion.getText().isEmpty() &&
				!txtTelefono.getText().isEmpty();
	}

	private void loadCliente() {
		if (selected != null) {
			txtCedula.setText(selected.getCedula());
			txtCedula.setEnabled(false);

			txtNombre.setText(selected.getNombre());
			txtDireccion.setText(selected.getDireccion());
			txtTelefono.setText(selected.getTelefono());
		}
	}

	protected void clean() {
		txtCedula.setEnabled(true);
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
	}
}
