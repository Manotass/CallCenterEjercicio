public class Empleado {

    private String tipo;
    private boolean ocupado;
    private String identificador;


    public Empleado(String tipo,boolean ocupado,String identificador){

        this.tipo=tipo;
        this.ocupado=ocupado;
        this.identificador=identificador;

    }

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
