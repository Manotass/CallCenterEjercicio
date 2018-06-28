public class LlamadaEnProceso {



    Llamada llamada;
    Empleado empleado;


    public LlamadaEnProceso(Llamada llamada, Empleado empleado) {
        this.llamada = llamada;
        this.empleado = empleado;
    }

    public Llamada getLlamada() {
        return llamada;
    }

    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
