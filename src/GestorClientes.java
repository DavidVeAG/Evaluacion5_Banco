import java.io.*;
import java.util.*;

public class GestorClientes {
    private ArrayList<Cliente> listaClientes;

    public GestorClientes() {
        listaClientes = new ArrayList<>();
        cargarDatos("src/datos/Datos.csv");
        Collections.sort(listaClientes); // orden inicial por apellido1, apellido2, nombre
    }

    private void cargarDatos(String ruta) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String ape1 = partes[0];
                    String ape2 = partes[1];
                    String nom = partes[2];
                    String carta = partes[3];
                    Cliente nuevo = new Cliente(ape1, ape2, nom, carta);
                    listaClientes.add(nuevo);
                }
            }
            lector.close();
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> getClientes() {
        return listaClientes;
    }

    public void ordenarPorCampo(String campo) {
        Comparator<Cliente> comparador = switch (campo.toLowerCase()) {
            case "nombre" -> Comparator.comparing(Cliente::getNombre, String.CASE_INSENSITIVE_ORDER);
            case "apellido1" -> Comparator.comparing(Cliente::getApellido1, String.CASE_INSENSITIVE_ORDER);
            case "apellido2" -> Comparator.comparing(Cliente::getApellido2, String.CASE_INSENSITIVE_ORDER);
            case "documento" -> Comparator.comparing(Cliente::getTipoCarta, String.CASE_INSENSITIVE_ORDER);
            default -> Comparator.comparing(Cliente::getApellido1);
        };
        Collections.sort(listaClientes, comparador);
    }

    public int buscar(String campo, String texto, int izquierda, int derecha) {
        if (campo == null || texto == null) return -1;
        if (izquierda > derecha) return -1;

        int centro = (izquierda + derecha) / 2;
        Cliente cliente = listaClientes.get(centro);

        String valor = switch (campo.toLowerCase()) {
            case "nombre" -> cliente.getNombre();
            case "apellido1" -> cliente.getApellido1();
            case "apellido2" -> cliente.getApellido2();
            case "documento" -> cliente.getTipoCarta();
            default -> "";
        };

        valor = (valor == null) ? "" : valor.toLowerCase();
        texto = texto.toLowerCase();

        int cmp = texto.compareTo(valor);

        if (cmp == 0) {
            return centro;
        } else if (cmp < 0) {
            return buscar(campo, texto, izquierda, centro - 1);
        } else {
            return buscar(campo, texto, centro + 1, derecha);
        }
    }
}
