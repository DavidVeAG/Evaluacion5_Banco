public class Cliente implements Comparable<Cliente> {
    private String apellido1;
    private String apellido2;
    private String nombre;
    private String tipoCarta;

    public Cliente(String apellido1, String apellido2, String nombre, String tipoCarta) {
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.nombre = nombre;
        this.tipoCarta = tipoCarta;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoCarta() {
        return tipoCarta;
    }

    @Override
    public int compareTo(Cliente otro) {
        int cmp = this.apellido1.compareToIgnoreCase(otro.apellido1);
        if (cmp != 0) return cmp;
        cmp = this.apellido2.compareToIgnoreCase(otro.apellido2);
        if (cmp != 0) return cmp;
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }
}