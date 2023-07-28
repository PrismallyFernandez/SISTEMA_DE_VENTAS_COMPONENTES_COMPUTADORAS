package visual;

import javax.swing.*;

import logico.Combo;
import logico.Componente;
import logico.DiscoDuro;
import logico.Factura;
import logico.MemoriaRam;
import logico.Micro;
import logico.Motherboard;

import java.awt.*;
import java.text.SimpleDateFormat;

public class DetallesFacturaDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Factura factura;

    public DetallesFacturaDialog(Factura factura) {
    	setResizable(false);
        this.factura = factura;

        setTitle("Detalles de Factura");
        setModal(true);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        JLabel lblLogo = new JLabel();
        lblLogo.setPreferredSize(new Dimension(150, 100)); // Ajusta el tamaño del logo según tus preferencias

        // Carga la imagen del logo desde un archivo (ajusta la ruta según la ubicación del archivo)
        ImageIcon logoIcon = new ImageIcon("ruta/del/logo.png"); // Reemplaza "ruta/del/logo.png" con la ruta de tu archivo de imagen
        lblLogo.setIcon(new ImageIcon(DetallesFacturaDialog.class.getResource("/images/8058280.jpg")));

        
        JLabel lblCodigo = new JLabel("Código de Factura: " + factura.getCodigo());
        lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JLabel lblCliente = new JLabel("Cliente: " + factura.getCliente().getNombre());
        JLabel lblFecha = new JLabel("Fecha: " + dateFormat.format(factura.getFecha()));
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));

        
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        textArea.setBackground(new Color(255, 255, 204));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        
        StringBuilder invoiceDetails = new StringBuilder();
        invoiceDetails.append("Detalles de la Factura:\n");
        invoiceDetails.append("=========================\n");

        for (int i = 0; i < factura.getMisComponentes().size(); i++) {
            Componente componente = factura.getMisComponentes().get(i);
            String tipoComponente = buscarComponenteBySerieFactura(componente.getNumSerie());
            invoiceDetails.append((i + 1) + ". " + tipoComponente + "\n");
            invoiceDetails.append("   Descripción: " + componente.getDescripcion() + "\n");
            
            invoiceDetails.append("   Modelo : " + componente.getModelo() + "\n");
            invoiceDetails.append("   Marca : " + componente.getMarca() + "\n");
            
            invoiceDetails.append("   Precio: " + componente.getPrecio() + "\n");
            invoiceDetails.append("   Cantidad: " + componente.getStock() + "\n");
            invoiceDetails.append("=========================\n");
        }

        for (int i = 0; i < factura.getMisCombos().size(); i++) {
            Combo combo = factura.getMisCombos().get(i);
            invoiceDetails.append((i + 1) + ". " + combo.getNombre() + " (Combo)" + "\n");
            invoiceDetails.append("   Precio: " + combo.getPrecio() + "\n");
            invoiceDetails.append("=========================\n");
        }

        invoiceDetails.append("Total de la Factura: " + factura.getMonto() + "\n");

        textArea.setText(invoiceDetails.toString());

        panel.add(lblLogo, BorderLayout.WEST);
        panel.add(lblCodigo, BorderLayout.NORTH);
        panel.add(lblCliente, BorderLayout.CENTER);
        panel.add(lblFecha, BorderLayout.SOUTH);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    // Método para obtener el tipo de componente mediante el número de serie
    private String buscarComponenteBySerieFactura(String serie) {
        String tipoComponente = "";
        Componente aux = null;
        for (Componente componentes : FacturarComplejo.copiarPrueba()) {
            if (componentes.getNumSerie().equalsIgnoreCase(serie)) {
                aux = componentes;
                break;
            }
        }
        if (aux != null) {
            if (aux instanceof DiscoDuro) {
                tipoComponente = "Disco Duro";
            } else if (aux instanceof MemoriaRam) {
                tipoComponente = "Memoria RAM";
            } else if (aux instanceof Micro) {
                tipoComponente = "Microprocesador";
            } else if (aux instanceof Motherboard) {
                tipoComponente = "Motherboard";
            }
        }
        return tipoComponente;
    }
}
