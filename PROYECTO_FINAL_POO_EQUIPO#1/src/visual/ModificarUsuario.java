package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Tienda;
import logico.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ModificarUsuario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textCodigo;
	private JTextField txtNombre;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textField_2;
	private JComboBox comboBox;
	private User selected;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) {
		try {
			ModificarUsuario dialog = new ModificarUsuario(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarUsuario(User user) {
		setResizable(false);
		this.selected = user; // Almacenar el usuario seleccionado en el constructor
		setBounds(100, 100, 309, 442);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(23, 13, 132, 14);
		contentPanel.add(lblCodigo);

		textCodigo = new JTextField();
		textCodigo.setEditable(false);
		textCodigo.setBackground(Color.WHITE);
		textCodigo.setBounds(23, 28, 97, 20);
		contentPanel.add(textCodigo);
		textCodigo.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre completo:");
		lblNombre.setBounds(23, 61, 113, 20);
		contentPanel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(23, 84, 221, 21);
		contentPanel.add(txtNombre);

		JLabel lblNombreUsuario = new JLabel("Usuario:");
		lblNombreUsuario.setBounds(25, 118, 147, 14);
		contentPanel.add(lblNombreUsuario);

		textUsername = new JTextField();
		textUsername.setBounds(23, 132, 221, 20);
		contentPanel.add(textUsername);
		textUsername.setColumns(10);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(23, 279, 97, 14);
		contentPanel.add(lblTipo);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Administrador", "Vendedor" }));
		comboBox.setBounds(23, 294, 127, 20);
		contentPanel.add(comboBox);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(25, 165, 97, 14);
		contentPanel.add(lblPassword);

		textPassword = new JTextField();
		textPassword.setBounds(23, 179, 221, 20);
		contentPanel.add(textPassword);
		textPassword.setColumns(10);

		JLabel lblConfirmarPassword = new JLabel("Confirmar Password:");
		lblConfirmarPassword.setBounds(23, 212, 167, 14);
		contentPanel.add(lblConfirmarPassword);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(23, 228, 221, 20);
		contentPanel.add(textField_2);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(new Color(51, 204, 153));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Verificar que los campos no estén vacíos
						if (txtNombre.getText().isEmpty() || textUsername.getText().isEmpty()
								|| textPassword.getText().isEmpty() || textField_2.getText().isEmpty()
								|| comboBox.getSelectedItem().equals("<Seleccione>")) {
							JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos",
									"Campos incompletos", JOptionPane.WARNING_MESSAGE);
							return;
						}

						// Verificar que las contraseñas coincidan
						if (!textPassword.getText().equals(textField_2.getText())) {
							JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						// Actualizar los datos del usuario seleccionado con los nuevos datos
						selected.setName(txtNombre.getText());
						selected.setUserName(textUsername.getText());
						selected.setPass(textPassword.getText());
						selected.setTipo(comboBox.getSelectedItem().toString());

						// Mostrar mensaje de éxito y cerrar el diálogo
						JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(new Color(102, 0, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		// Cargar los datos del usuario seleccionado en los campos del formulario
		loadUserData();
	}

	private void loadUserData() {
		if (selected != null) {
			textCodigo.setText(selected.getUserId());
			textCodigo.setEnabled(false);
			txtNombre.setText(selected.getName());
			textUsername.setText(selected.getUserName());
			textPassword.setText(selected.getPass());

			// Establecer el tipo de usuario seleccionado en el comboBox
			String tipoUsuario = selected.getTipo();
			if (tipoUsuario.equals("Administrador")) {
				comboBox.setSelectedItem("Administrador");
			} else if (tipoUsuario.equals("Vendedor")) {
				comboBox.setSelectedItem("Vendedor");
			}
		}
	}

}
