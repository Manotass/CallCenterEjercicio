import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Dispatcher {

    public static void main(String[] arg) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.administradorLLamadas();
    }
    /**
    * CREAMOS UNA INSTANCIA DE LA CLASE EMPLEADO DE MANERA GLOBAR PUES REQUERIMOS PODER ACCEDES A ESTE DESDE OTRAS CLASES*/
    Empleado empleadoQueAtendera;
    /**ESTE ATRIBUTO SE CREO UNICAMENTE PARA PODER HACERLE PRUEBAS UNITARIAS AL METODO QUE ASIGNA LLAMADAS*/
    int llamadasAtendidas = 0;

    /**CREAMOS UNA LISTA INDEPENDIENTE POR CADA TIPO DE EMPLEADO. ESTO FACILITA LA BUSQUEDA DE EMPLEADOS DISPONIBLES*/
    List<Empleado> empleadosOperadores = Collections.synchronizedList(new ArrayList<Empleado>());
    List<Empleado> empleadosSupervisores = Collections.synchronizedList(new ArrayList<Empleado>());
    List<Empleado> empleadosDirectores = Collections.synchronizedList(new ArrayList<Empleado>());
    /**CREAMOS UNA LISTA DE LLAMAS, PARA ASI TENER UNA COLA DE LAS MISMAS Y ATENDERLAS EN ORDEN FIFO*/
    ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
    /**METODO CONTRUCTOR DE LA CLASE*/
    public Dispatcher() {

    }
    int numeroThread=0;
    /**METODO QUE SE ENCARGA DE ASIGNAR LLAMADAS*/
    public void administradorLLamadas() {
        if(llamadas.size()>0) {
            /**BUSCAMOS SI HAY EMPLEADOS DISPONIBLES PARA ATENDER LAS LLAMADAS*/
            empleadoQueAtendera = this.buscarEmpleadoDisponible();
            if (empleadoQueAtendera!=null) {
                /**CAMBIAMOS EL ESTADO DEL EMPLEADO A OCUPADO PARA QUE NO VAYA A SER ASIGNADO A OTRO HILO DE PROCESAMIENTO*/
                numeroThread++;
                empleadoQueAtendera.setOcupado(true);
                Llamada llamada = llamadas.get(0);
                llamadas.remove(llamada);
                llamadasAtendidas++;
                /**LLAMADOS LA CLASE THREAD PARA CREAR UN NUEVO HILO DE PROCESAMIENTO*/
                new DispatchCalls(llamada, empleadoQueAtendera, "THREAD" + numeroThread,this);
                this.administradorLLamadas();
            }
        }

    }

    /**METODO QUE RETORNA UN EMPLEADO DISPONIBLES
 * LA BUSQUEDA SE HACE EN ORDER JENRARQUICO ASCENDENTE*/
    public Empleado buscarEmpleadoDisponible() {
        /**BUSCAMOS SI HAY EMPLEADOS OPERADORES DISPONIBLES*/
        for (int i = 0; i < empleadosOperadores.size(); i++) {
            if (!empleadosOperadores.get(i).isOcupado()) {
                return empleadosOperadores.get(i);
            }
        }
        /**BUSCAMOS SI HAY EMPLEADOS SUPERVISORES DISPONIBLES*/
        for (int i = 0; i < empleadosSupervisores.size(); i++) {
            if (!empleadosSupervisores.get(i).isOcupado()) {
                return empleadosSupervisores.get(i);
            }
        }
        /**BUSCAMOS SI HAY EMPLEADOS DIRECTORES DISPONIBLES*/
        for (int i = 0; i < empleadosDirectores.size(); i++) {
            if (!empleadosDirectores.get(i).isOcupado()) {
                return empleadosDirectores.get(i);
            }
        }
        /**DE NO ENCONTRAR NINGUNO RETORNAMOS NULL*/
        return null;
    }
    /**METODO QUE SE EJECUTA UNA VEZ UNA LLAMADA HA TERMINADO PARA QUE LAS PENDIENTES SEAN ATENDIDAS*/
    public void empleadoLiberado() {
        this.administradorLLamadas();
    }
}

/**CLASE THREAD QUE SE ENCARGA DE GENERAR EL HILO DE PROCESAMIENTO QUE ATIENDE UNA LLAMADA*/
class DispatchCalls extends Thread {

    Llamada llamada;
    Empleado empleado;
    String numeroThread;
    Dispatcher dispatcher;
    /**COMO PARTE DEL EJERCICIO GENERAMOS UN NUMERO ALEATORIO ENTRE 5000 Y 10000 QUE
     * REPRESENTA LA DURACION DE LA LLAMADA*/
    int tiempoLlamada = (int) (Math.random() * 5500 + 5000);

    @Override
    public void run() {

        this.dispatchCall();
    }

    /**CONTRUCTOR DE LA CLASE QUE SE ENCARGA DE ASIGNAR VARIABLES E INICIAR EL HILO*/
    public DispatchCalls(Llamada llamada, Empleado empleado, String numeroThread,Dispatcher dispatcher) {
        this.llamada = llamada;
        this.empleado = empleado;
        this.numeroThread = numeroThread;
        this.dispatcher=dispatcher;
        this.start();
    }
    /**METODO QUE SIMULA A UN EMPLEADO ATENDIENDO UNA LLAMADA*/
    public void dispatchCall() {
        LlamadaEnProceso llamadaEnProceso = new LlamadaEnProceso(llamada, empleado);
        try {
            /**ACA DORMIMOS EL HILO DURANTE EL TIEMPO ALEATORIO PRODUCIDO ANTERIORMENTE
             * CON EL FIN DE SIMULAR LA DURACION DE LA LLAMADA*/
            this.sleep(tiempoLlamada);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(llamadaEnProceso.getLlamada().getNombreCliente() + " - " + llamadaEnProceso.getEmpleado().getTipo() + " - " + llamadaEnProceso.getEmpleado().getIdentificador());
        System.out.println(" TIEMPO LLAMADA " + tiempoLlamada);
        /**UNA VEZ EL HILO SE DESPIERTA, QUIERE DECIR QUE LA LLAMDA TERMINO
         * MOTIVO POR EL CUAL PONEMOS COMO DISPONIBLE AL EMPLEADO ASIGNADO*/
        empleado.setOcupado(false);
        /**AVISAMOS AL METODO PRINCIPAL QUE UN HILO HA TERMINADO SU EJECUCION Y POR ENDE UN EMPLEADO ESTA DISPONIBLE*/
        dispatcher.empleadoLiberado();
        System.out.println(" --------------------- ");


    }


}
