import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Dispatcher {

    public static void main(String[] arg) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.administradorLLamadas();

    }

    Empleado empleadoQueAtendera;
    int llamadasAtendidas = 0;
    List<Empleado> empleadosOperadores = Collections.synchronizedList(new ArrayList<Empleado>());
    List<Empleado> empleadosSupervisores = Collections.synchronizedList(new ArrayList<Empleado>());
    List<Empleado> empleadosDirectores = Collections.synchronizedList(new ArrayList<Empleado>());
    ArrayList<Llamada> llamadas = new ArrayList<Llamada>();

    public Dispatcher() {

    }

    public void administradorLLamadas() {

        for (int i = 0; i < llamadas.size(); i++) {

            this.asignarLlamadas(llamadas.get(i), "THREAD" + i);
            System.out.println("LLAMADAS ATENDIDAS " + llamadasAtendidas);
            System.out.println("LLAMADAS RESTANTES " + llamadas.size());
        }

    }

    public void asignarLlamadas(Llamada llamada, String numeroThread) {
        llamadasAtendidas++;
        empleadoQueAtendera = this.buscarEmpleadoDisponible();
        if (empleadoQueAtendera == null) {
            System.out.println("NO HAY EMPLEADOS");

        } else {
            empleadoQueAtendera.setOcupado(true);
            new DispatchCalls(llamada, empleadoQueAtendera, numeroThread);


        }
    }

    public Empleado buscarEmpleadoDisponible() {

        for (int i = 0; i < empleadosOperadores.size(); i++) {
            if (!empleadosOperadores.get(i).isOcupado()) {
                return empleadosOperadores.get(i);
            }
        }
        for (int i = 0; i < empleadosSupervisores.size(); i++) {
            if (!empleadosSupervisores.get(i).isOcupado()) {
                return empleadosSupervisores.get(i);
            }
        }
        for (int i = 0; i < empleadosDirectores.size(); i++) {
            if (!empleadosDirectores.get(i).isOcupado()) {
                return empleadosDirectores.get(i);
            }
        }
        return null;
    }
}

class DispatchCalls extends Thread {

    Llamada llamada;
    Empleado empleado;
    String numeroThread;

    int tiempoLlamada = (int) (Math.random() * 5500 + 5000);

    @Override
    public void run() {

        this.dispatchCall();
    }

    public DispatchCalls(Llamada llamada, Empleado empleado, String numeroThread) {
        this.llamada = llamada;
        this.empleado = empleado;
        this.numeroThread = numeroThread;
        System.out.println("THREAD STARTS " + numeroThread);
        this.start();
    }

    public void dispatchCall() {
        LlamadaEnProceso llamadaEnProceso = new LlamadaEnProceso(llamada, empleado);
        try {
            this.sleep(tiempoLlamada);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(llamadaEnProceso.getLlamada().getNombreCliente() + " - " + llamadaEnProceso.getEmpleado().getTipo() + " - " + llamadaEnProceso.getEmpleado().getIdentificador());

        System.out.println(" TIEMPO LLAMADA " + tiempoLlamada);
        empleado.setOcupado(false);
        System.out.println(" --------------------- ");


    }


}
