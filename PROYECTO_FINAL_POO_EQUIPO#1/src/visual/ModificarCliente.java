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

public class ModificarCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private Cliente selected;
	private JTextField txtCedula;
	private JTextField txtTelefono;

	public static void main(String[] args) {
		try {
			ModificarCliente dialog = new ModificarCliente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModificarCliente(Cliente cliente) {
		selected = cliente;

		if (selected == null) {
			dispose(); 
		}

		setTitle("MODIFICAR CLIENTES");
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
				txtDireccion.setBounds(81, 138, 330, 30);
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
			txtTelefono.setBounds(81, 98, 330, 30);
			panel.add(txtTelefono);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnmodificar = new JButton("MODIFICAR");
				btnmodificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							if (!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtTelefono.getText().isEmpty()) {
								selected.setNombre(txtNombre.getText());
								selected.setDireccion(txtDireccion.getText());
								selected.setTelefono(txtTelefono.getText());
								ListadoCliente.loadClientes(0);
								dispose();
								JOptionPane.showMessageDialog(ModificarCliente.this, "Cliente modificado exitosamente.", "Afirmación", JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(ModificarCliente.this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				});
				btnmodificar.setActionCommand("OK");
				buttonPane.add(btnmodificar);
				getRootPane().setDefaultButton(btnmodificar);
			}
			{
				JButton cancelButton = new JButton("CANCELAR");
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

	private void loadCliente() {
		if (selected != null) {
			txtCedula.setText(selected.getCedula());
			txtCedula.setEnabled(false);

			txtNombre.setText(selected.getNombre());
			txtDireccion.setText(selected.getDireccion()); 
			txtTelefono.setText(selected.getTelefono()); 
		}
	}
}
