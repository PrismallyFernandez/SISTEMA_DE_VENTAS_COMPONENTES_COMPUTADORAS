package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Tienda;
import logico.User;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                login dialog = new login();
	                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	                dialog.setVisible(true);

	                // Esperar hasta que la ventana de inicio de sesión se cierre
	                dialog.setModal(true);

	                // Verificar si el login fue exitoso antes de abrir la ventana principal
	                if (Tienda.getInstance().isLogged()) {
	                    Principal principal = new Principal();
	                    principal.setVisible(true);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}



	/**
	 * Create the dialog.
	 */
	public login() {
		setTitle("User Login");
		setBounds(100, 100, 918, 554);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("New label");
			lblNewLabel.setIcon(new ImageIcon(login.class.getResource("/images/loginImg.png")));
			lblNewLabel.setBounds(25, -42, 554, 511);
			panel.add(lblNewLabel);
			
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblUsuario.setBounds(591, 145, 101, 20);
			panel.add(lblUsuario);
			
			txtUser = new JTextField();
			txtUser.setBounds(591, 166, 244, 33);
			panel.add(txtUser);
			txtUser.setColumns(10);
			
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPassword.setBounds(591, 246, 101, 20);
			panel.add(lblPassword);
			
			txtPassword = new JPasswordField();
			txtPassword.setBounds(591, 271, 244, 33);
			panel.add(txtPassword);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(32, 178, 170));
			panel_1.setBounds(0, 0, 420, 469);
			panel.add(panel_1);
			panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("Login");
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(30, 144, 255));
				okButton.setBounds(591, 346, 244, 33);
				panel.add(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (Tienda.getInstance().confirmLogin(txtUser.getText(), new String(txtPassword.getPassword()))) {
							Principal principal = new Principal();
							dispose();
							principal.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "El nombre de usuario o su clava es incorrecta", "Error de Login", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(new Color(51, 204, 153));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
