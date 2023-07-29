package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;

public class Principal extends JFrame {

	
	
	

	
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private  Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-50);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(200, 100, 200, 100));
		menuBar.setFont(new Font("SansSerif", Font.BOLD, 15));
		menuBar.setBackground(Color.WHITE);
		setJMenuBar(menuBar);
		
		
		JMenu mnComponentes = new JMenu("Componentes");
		mnComponentes.setIcon(new ImageIcon(Principal.class.getResource("/images/rom.png")));
		menuBar.add(mnComponentes);
		
		JMenuItem mntmRegComp = new JMenuItem("Registrar Componente");
		mntmRegComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarComponente regC = new RegistrarComponente();
				regC.setModal(true);
				regC.setVisible(true);
			}
		});
		mnComponentes.add(mntmRegComp);
		
		JMenuItem mntmListadoDeComp = new JMenuItem("Listado de Componentes");
		mntmListadoDeComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoComponentes list = new ListadoComponentes();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		mnComponentes.add(mntmListadoDeComp);
		
		JMenuItem mntmElaborarCombo = new JMenuItem("Elaborar Combo");
		mntmElaborarCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Combox combo = new Combox();
				combo.setModal(true);
				combo.setVisible(true);
			}
		});
		mnComponentes.add(mntmElaborarCombo);
		
		JMenuItem mntmListadoDeCombos = new JMenuItem("Listado de Combos");
		mntmListadoDeCombos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoCombo combo = new ListadoCombo();
				combo.setModal(true);
				combo.setVisible(true);
			}
		});
		mnComponentes.add(mntmListadoDeCombos);
		JMenu mnVentas = new JMenu("Ventas");
		mnVentas.setIcon(new ImageIcon(Principal.class.getResource("/images/bolsa-de-la-compra.png")));
		menuBar.add(mnVentas);
		
		JMenuItem mntmFacturar = new JMenuItem("Facturar");
		mntmFacturar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FacturarComplejo factura = new FacturarComplejo();
				factura.setModal(true);
				factura.setVisible(true);
			}
		});
		mnVentas.add(mntmFacturar);
		
//		aqui va
		
		JMenuItem mntmListadoFactura = new JMenuItem("Listado Factura");
		mntmListadoFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoFactura list = new ListadoFactura();
				list.setModal(true);
				list.setVisible(true);
			}
		});
		mnVentas.add(mntmListadoFactura);
		
		
		
		//aqui termina
		JMenu mmAdministrador = new JMenu("Cliente");
		mmAdministrador.setIcon(new ImageIcon(Principal.class.getResource("/images/objetivo (1).png")));
		menuBar.add(mmAdministrador);
		
	
		
		JMenuItem mntmListadoClientes_1 = new JMenuItem("Listado clientes");
		mntmListadoClientes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoCliente list = new ListadoCliente();
				list.setModal(true);
				list.setVisible(true);
				
			}
		});
		mmAdministrador.add(mntmListadoClientes_1);
		
		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegCliente reg = new RegCliente(null);
				reg.setModal(true);
				reg.setVisible(true);
			}
		});
		mmAdministrador.add(mntmRegistrarCliente);
		
		
		JMenu mnUser = new JMenu("Usuario");
		mnUser.setIcon(new ImageIcon(Principal.class.getResource("/images/recursos-humanos.png")));
		menuBar.add(mnUser);
		
		JMenuItem mntmRegistrarUsuario = new JMenuItem("Registrar Usuario");
		mntmRegistrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegUser reg = new RegUser();
				reg.setModal(true);
				reg.setVisible(true);
				
			}
		});
		mnUser.add(mntmRegistrarUsuario);
		
		JMenuItem mntmListarUsuarios = new JMenuItem("Listado Usuarios");
		mntmListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsuario listUser = new ListUsuario();
				listUser.setModal(true);
				listUser.setVisible(true);
			}
		});
		mnUser.add(mntmListarUsuarios);
		
		
		//resumen
		
		JMenu mnResumen = new JMenu("Resumen");
		mnResumen.setIcon(new ImageIcon(Principal.class.getResource("/images/escribe.png")));
		menuBar.add(mnResumen);
		
		JMenuItem mntmListadoClientes = new JMenuItem("Datos Generales");
		mntmListadoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosGenerales datos = new DatosGenerales();
				datos.setModal(true);
				datos.setVisible(true);
			}
		});
		mnResumen.add(mntmListadoClientes);
		
		
		
		JMenu mnExit = new JMenu("Salir");
		mnExit.setIcon(new ImageIcon(Principal.class.getResource("/images/salida (2).png")));
		mnExit.setBackground(Color.WHITE);
		menuBar.add(mnExit);
		
		JMenuItem mntmExit = new JMenuItem("Salir");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null,"¿Estás seguro que deseas salir del programa?","Confirmar Salida",JOptionPane.YES_NO_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
				    System.exit(0);
				}
			}
		});
		mnExit.add(mntmExit);
		
		
		
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/images/principalbg.png")));
		lblNewLabel.setBounds(0, 0, 1933, 953);
		contentPane.add(lblNewLabel);
	}
}
