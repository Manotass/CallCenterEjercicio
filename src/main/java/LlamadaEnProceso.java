public class LlamadaEnProceso {



    Llamada llamada;
    Empleado empleado;

    /**CLASE QUE CONTIENE LOS ATRIBUTOS DE UNA LLAMADA EN PROCESO*/
    public LlamadaEnProceso(Llamada llamada, Empleado empleado) {
        this.llamada = llamada;
        this.empleado = empleado;
    }

    /**SETTER AND GETTERS DE LOS ATRIBUTOS*/
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
