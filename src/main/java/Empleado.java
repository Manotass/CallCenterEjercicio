public class Empleado {

    private String tipo;
    private boolean ocupado;
    private String identificador;

    /**CLASE QUE CONTIENE LOS ATRIBUTOS DE UN EMPLEADO*/
    public Empleado(String tipo,boolean ocupado,String identificador){

        this.tipo=tipo;
        this.ocupado=ocupado;
        this.identificador=identificador;

    }

    /**SETTER AND GETTERS DE LOS ATRIBUTOS*/
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }




}
