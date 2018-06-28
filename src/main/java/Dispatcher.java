import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


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
    /**METODO QUE SE ENCARGA DE RECORRER LAS LLAMADAS Y ENVIARLAS AL ASIGNADOR*/
    public void administradorLLamadas() {

        for (int i = 0; i < llamadas.size(); i++) {

            this.asignarLlamadas(llamadas.get(i), "THREAD" + i);
            System.out.println("LLAMADAS ATENDIDAS " + llamadasAtendidas);
            System.out.println("LLAMADAS RESTANTES " + llamadas.size());
        }

    }
    /**METODO QUE SE ENCARGA DE CREAR ASIGNAR UNA LLAMADA A UN EMPLEADO A TRAVEZ DE UN HILO*/

    public void asignarLlamadas(Llamada llamada, String numeroThread) {
        llamadasAtendidas++;
        /**BUSCAMOS SI HAY EMPLEADOS DISPONIBLES PARA ATENDER LAS LLAMADAS*/
        empleadoQueAtendera = this.buscarEmpleadoDisponible();
        if (empleadoQueAtendera == null) {
            System.out.println("NO HAY EMPLEADOS");

        } else {
            /**CAMBIAMOS EL ESTADO DEL EMPLEADO A OCUPADO PARA QUE NO VAYA A SER ASIGNADO A OTRO HILO DE PROCESAMIENTO*/
            empleadoQueAtendera.setOcupado(true);
            /**LLAMADOS LA CLASE THREAD PARA CREAR UN NUEVO HILO DE PROCESAMIENTO*/
            new DispatchCalls(llamada, empleadoQueAtendera, numeroThread);


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
}

/**CLASE THREAD QUE SE ENCARGA DE GENERAR EL HILO DE PROCESAMIENTO QUE ATIENDE UNA LLAMADA*/
class DispatchCalls extends Thread {

    Llamada llamada;
    Empleado empleado;
    String numeroThread;
    /**COMO PARTE DEL EJERCICIO GENERAMOS UN NUMERO ALEATORIO ENTRE 5000 Y 10000 QUE
     * REPRESENTA LA DURACION DE LA LLAMADA*/
    int tiempoLlamada = (int) (Math.random() * 5500 + 5000);

    @Override
    public void run() {

        this.dispatchCall();
    }

    /**CONTRUCTOR DE LA CLASE QUE SE ENCARGA DE ASIGNAR VARIABLES E INICIAR EL HILO*/
    public DispatchCalls(Llamada llamada, Empleado empleado, String numeroThread) {
        this.llamada = llamada;
        this.empleado = empleado;
        this.numeroThread = numeroThread;
        System.out.println("THREAD STARTS " + numeroThread);
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
        System.out.println(" --------------------- ");


    }


}
