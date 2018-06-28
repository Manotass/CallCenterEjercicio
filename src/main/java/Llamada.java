public class Llamada {

    private String nombreCliente;
    private int duracion;
    private Empleado empleado;

    public Llamada(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        this.duracion = duracion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }


    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
