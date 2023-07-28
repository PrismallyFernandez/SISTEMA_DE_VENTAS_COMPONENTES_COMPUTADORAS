package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Tienda;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;

public class DatosGenerales extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField mdrCant;
	private JTextField RamCant;
	private JTextField disCant;
	private JTextField discash;
	private JTextField mrcash;
	private JTextField ramcash;
	private JTextField totalVentas;
	private JTextField mdrcash;
	private JTextField mrcant;
	private JTextField combocash;
	private JTextField comboCant;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosGenerales dialog = new DatosGenerales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosGenerales() {
		setResizable(false);
		
		setTitle("Modificar de Cliente:");
		setBounds(100, 100, 414, 632);
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
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			mdrCant = new JTextField();
			mdrCant.setEditable(false);
			mdrCant.setBounds(247, 46, 63, 20);
			panel.add(mdrCant);
			mdrCant.setColumns(10);
			
			RamCant = new JTextField();
			RamCant.setText("0");
			RamCant.setEditable(false);
			RamCant.setBounds(247, 110, 63, 20);
			panel.add(RamCant);
			RamCant.setColumns(10);
			
			disCant = new JTextField();
			disCant.setText("0");
			disCant.setEditable(false);
			disCant.setBounds(247, 78, 63, 20);
			panel.add(disCant);
			disCant.setColumns(10);
			
			JLabel lblAsdf = new JLabel("Cantidad de componentes vendidos por tipo:");
			lblAsdf.setBounds(12, 12, 319, 14);
			panel.add(lblAsdf);
			
			JLabel lblQuesosCilindricosVendidos = new JLabel("Tarjetas Madre vendidas:");
			lblQuesosCilindricosVendidos.setBounds(12, 50, 228, 15);
			panel.add(lblQuesosCilindricosVendidos);
			
			JLabel lblQuesosEsfericosVendidos = new JLabel("Discos duros vendidos:");
			lblQuesosEsfericosVendidos.setBounds(12, 82, 205, 15);
			panel.add(lblQuesosEsfericosVendidos);
			
			JLabel lblQuesosCilindricosH = new JLabel("Memorias Ram vendidas:");
			lblQuesosCilindricosH.setBounds(12, 116, 228, 15);
			panel.add(lblQuesosCilindricosH);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(0, 168, 412, 2);
			panel.add(separator);
			
			JLabel lblDineroGeneradoPor = new JLabel("Dinero generado por tipos de Componente:");
			lblDineroGeneradoPor.setBounds(12, 182, 335, 15);
			panel.add(lblDineroGeneradoPor);
			
			discash = new JTextField();
			discash.setText("0");
			discash.setEditable(false);
			discash.setColumns(10);
			discash.setBounds(323, 254, 63, 20);
			panel.add(discash);
			
			mrcash = new JTextField();
			mrcash.setText("0");
			mrcash.setEditable(false);
			mrcash.setColumns(10);
			mrcash.setBounds(323, 340, 63, 20);
			panel.add(mrcash);
			
			ramcash = new JTextField();
			ramcash.setText("0");
			ramcash.setEditable(false);
			ramcash.setColumns(10);
			ramcash.setBounds(323, 297, 63, 20);
			panel.add(ramcash);
			
			JLabel lblDineroGeneradoPor_1 = new JLabel("Dinero generado por Discos duros:");
			lblDineroGeneradoPor_1.setBounds(12, 256, 308, 15);
			panel.add(lblDineroGeneradoPor_1);
			
			JLabel lblDineroGeneradoPor_2 = new JLabel("Dinero generado por Memorias Ram:");
			lblDineroGeneradoPor_2.setBounds(12, 299, 285, 15);
			panel.add(lblDineroGeneradoPor_2);
			
			JLabel lblDineroGenradoPor = new JLabel("Dinero generado por Microprocesadores:");
			lblDineroGenradoPor.setBounds(12, 342, 298, 15);
			panel.add(lblDineroGenradoPor);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(0, 383, 437, 8);
			panel.add(separator_1);
			
			totalVentas = new JTextField();
			totalVentas.setEditable(false);
			totalVentas.setColumns(10);
			totalVentas.setBounds(141, 505, 63, 20);
			panel.add(totalVentas);
			
			JLabel lblTotalDeVentas = new JLabel("Total de ventas:");
			lblTotalDeVentas.setBounds(12, 507, 129, 15);
			panel.add(lblTotalDeVentas);
			
			JLabel lblDineroGeneradoPor_1_1 = new JLabel("Dinero generado por Tarjetas madre:");
			lblDineroGeneradoPor_1_1.setBounds(12, 224, 308, 15);
			panel.add(lblDineroGeneradoPor_1_1);
			
			mdrcash = new JTextField();
			mdrcash.setText("0");
			mdrcash.setEditable(false);
			mdrcash.setColumns(10);
			mdrcash.setBounds(323, 222, 63, 20);
			panel.add(mdrcash);
			
			JLabel lblMicroprocesadoresVendidos = new JLabel("Microprocesadores vendidos:");
			lblMicroprocesadoresVendidos.setBounds(12, 141, 228, 15);
			panel.add(lblMicroprocesadoresVendidos);
			
			mrcant = new JTextField();
			mrcant.setText("0");
			mrcant.setEditable(false);
			mrcant.setColumns(10);
			mrcant.setBounds(247, 136, 63, 20);
			panel.add(mrcant);
			
			JSeparator separator_1_1 = new JSeparator();
			separator_1_1.setBounds(-15, 474, 437, 8);
			panel.add(separator_1_1);
			
			JLabel lblDineroGeneradoPor_3 = new JLabel("Dinero generado por Combos");
			lblDineroGeneradoPor_3.setBounds(12, 401, 298, 15);
			panel.add(lblDineroGeneradoPor_3);
			
			JLabel lblCombosVendidos = new JLabel("Combos vendidos:");
			lblCombosVendidos.setBounds(12, 434, 298, 15);
			panel.add(lblCombosVendidos);
			
			combocash = new JTextField();
			combocash.setText("0");
			combocash.setEditable(false);
			combocash.setColumns(10);
			combocash.setBounds(323, 399, 63, 20);
			panel.add(combocash);
			
			comboCant = new JTextField();
			comboCant.setText("0");
			comboCant.setEditable(false);
			comboCant.setColumns(10);
			comboCant.setBounds(323, 429, 63, 20);
			panel.add(comboCant);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 255));
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setForeground(new Color(0, 0, 0));
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
		
		load();
	}
	public void load() {
		
		String cantmdr = Integer.toString(Tienda.getInstance().cantMotherBoard());
		mdrCant.setText(cantmdr);
		//-----------------------------------
		String cantram = Integer.toString(Tienda.getInstance().cantRam());
		RamCant.setText(cantram);
		//-----------------------------------
		String cantdis = Integer.toString(Tienda.getInstance().cantDiscoDuro());
		disCant.setText(cantdis);
		//-----------------------------------
		String cantmr = Integer.toString(Tienda.getInstance().cantMicro());
		mrcant.setText(cantmr);
		
		
		String dinerodis = Float.toString(Tienda.getInstance().gananciaDiscoDuro());
		discash.setText(dinerodis);
		//-----------------------------------
		String dineromr = Float.toString(Tienda.getInstance().gananciaMicro());
		mrcash.setText(dineromr);
		//-----------------------------------
		String dineroram = Float.toString(Tienda.getInstance().gananciaRam());
		ramcash.setText(dineroram);
		//-----------------------------------
		String dineromdr = Float.toString(Tienda.getInstance().gananciaMotherBoard());
		mdrcash.setText(dineromdr);
		
		String total = Float.toString(Tienda.getInstance().totalGanancia());
		totalVentas.setText(total);
	}
}
