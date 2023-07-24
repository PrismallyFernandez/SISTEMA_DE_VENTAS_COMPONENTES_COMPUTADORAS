package visual;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 488);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarClientes registrarClientes = new RegistrarClientes();
				registrarClientes.setModal(true);
				registrarClientes.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_4 = new JMenu("Lista de los Clientes");
		mnNewMenu_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaClientes listaClientes = new ListaClientes();
				listaClientes.setModal(true);
				listaClientes.setVisible(true);
			}
		});
		mnNewMenu.add(mnNewMenu_4);
		
		JMenu mnNewMenu_1 = new JMenu("Componentes");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarComponentes registrarComponentes = new RegistrarComponentes();
				registrarComponentes.setModal(true);
				registrarComponentes.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_5 = new JMenu("Lista de los Comp.");
		mnNewMenu_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaComponentes listaComponentes = new ListaComponentes();
				listaComponentes.setModal(true);
				listaComponentes.setVisible(true);
			}
		});
		mnNewMenu_1.add(mnNewMenu_5);
		
		
		
		JMenu mnNewMenu_2 = new JMenu("Combos");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registrar");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCombos registrarCombos = new RegistrarCombos();
				registrarCombos.setModal(true);
				registrarCombos.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_6 = new JMenu("Lista de los Combos");
		mnNewMenu_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaCombos listaCombos = new ListaCombos();
				listaCombos.setModal(true);
				listaCombos.setVisible(true);
			}
		});
		mnNewMenu_2.add(mnNewMenu_6);
		
		JMenu mnNewMenu_3 = new JMenu("Ventas");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Realizar Venta");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RealizarVentas realizarVentas = new RealizarVentas();
				realizarVentas.setModal(true);
				realizarVentas.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_7 = new JMenu("Lista de las Ventas");
		mnNewMenu_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaVentas listaVentas = new ListaVentas();
				listaVentas.setModal(true);
				listaVentas.setVisible(true);
			}
		});
		mnNewMenu_3.add(mnNewMenu_7);
		
		JMenu menu = new JMenu("New menu");
		mnNewMenu_7.add(menu);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(panel_1, BorderLayout.SOUTH);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

}
