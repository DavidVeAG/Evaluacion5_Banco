import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmBanco extends JFrame {
    private JTextField txtBusqueda;
    private JButton btnBuscar, btnSiguiente, btnAnterior;
    private JTextArea txtResultado;
    private JComboBox<String> cbCampo;
    private GestorClientes gestor;
    private int indiceActual = -1;

    public FrmBanco() {
        setTitle("Banco");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCampo = new JLabel("Campo:");
        lblCampo.setBounds(10, 10, 50, 20);
        add(lblCampo);

        cbCampo = new JComboBox<>(new String[] { "nombre", "apellido1", "apellido2", "documento" });
        cbCampo.setBounds(70, 10, 120, 20);
        add(cbCampo);

        txtBusqueda = new JTextField();
        txtBusqueda.setBounds(200, 10, 100, 20);
        add(txtBusqueda);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(310, 10, 80, 20);
        add(btnBuscar);

        txtResultado = new JTextArea();
        txtResultado.setBounds(10, 40, 380, 150);
        txtResultado.setEditable(false);
        add(txtResultado);

        btnAnterior = new JButton("Anterior");
        btnAnterior.setBounds(80, 200, 100, 30);
        add(btnAnterior);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setBounds(220, 200, 100, 30);
        add(btnSiguiente);

        gestor = new GestorClientes();

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = txtBusqueda.getText();
                String campo = cbCampo.getSelectedItem().toString();
                gestor.ordenarPorCampo(campo); // <-- nuevo
                indiceActual = gestor.buscar(campo, texto, 0, gestor.getClientes().size() - 1);
                mostrarCliente();
            }
        });

        btnAnterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indiceActual > 0) {
                    indiceActual--;
                    mostrarCliente();
                }
            }
        });

        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (indiceActual < gestor.getClientes().size() - 1) {
                    indiceActual++;
                    mostrarCliente();
                }
            }
        });

        setVisible(true);
    }

    private void mostrarCliente() {
        if (indiceActual >= 0 && indiceActual < gestor.getClientes().size()) {
            Cliente c = gestor.getClientes().get(indiceActual);
            txtResultado.setText("Nombre: " + c.getNombre() + "\n" +
                    "Apellido1: " + c.getApellido1() + "\n" +
                    "Apellido2: " + c.getApellido2() + "\n" +
                    "Documento: " + c.getTipoCarta());
        } else {
            txtResultado.setText("No encontrado");
        }
    }

    public static void main(String[] args) {
        new FrmBanco();
    }
}
