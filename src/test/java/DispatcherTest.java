import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DispatcherTest {


    /**TEST UNITARIO QUE SE ENCARGA DE COMPROBAR QUE LOS HILOS SE ESTEN ASIGNADO DE MANERA ADEUCADA*/
    @org.junit.jupiter.api.Test
    void administradorLLamadas() {
        Dispatcher dispatcher = new Dispatcher();
        /**EMPLEADO DE PRUEBA*/
        Empleado empleado1=new Empleado("OPERARIO",false,"OPERARIO 1");
        Empleado empleado2=new Empleado("OPERARIO",false,"OPERARIO 2");
        Empleado empleado3=new Empleado("OPERARIO",false,"OPERARIO 3");
        Empleado empleado4=new Empleado("OPERARIO",false,"OPERARIO 4");
        Empleado empleado5=new Empleado("OPERARIO",false,"OPERARIO 5");
        Empleado empleado6=new Empleado("OPERARIO",false,"OPERARIO 6");
        Empleado empleado7=new Empleado("SUPERVISOR",false,"SUPERVISOR 1");
        Empleado empleado8=new Empleado("SUPERVISOR",false,"SUPERVISOR 2");
        Empleado empleado9=new Empleado("DIRECTOR",false,"SUPERVISOR 3");
        Empleado empleado10=new Empleado("DIRECTOR",false,"DIRECTOR 1");
        /**LLAMADAS DE PRUEBA*/
        Llamada llamada1=new Llamada("CARLOS");
        Llamada llamada2=new Llamada("JUAN");
        Llamada llamada3=new Llamada("ANDRES");
        Llamada llamada4=new Llamada("MARIO");
        Llamada llamada5=new Llamada("VIVIANA");
        Llamada llamada6=new Llamada("ANGELICA");
        Llamada llamada7=new Llamada("ANDREA");
        Llamada llamada8=new Llamada("TATIANA");
        Llamada llamada9=new Llamada("MARCOS");
        Llamada llamada10=new Llamada("JULIAN");
        /**AGREGAMOS LOS EMPLEADOS DE PRUEBA A LAS LISTAS CORRESPONDIENTES*/
        dispatcher.empleadosOperadores.add(empleado1);
        dispatcher.empleadosOperadores.add(empleado2);
        dispatcher.empleadosOperadores.add(empleado3);
        dispatcher.empleadosOperadores.add(empleado4);
        dispatcher.empleadosOperadores.add(empleado5);
        dispatcher.empleadosOperadores.add(empleado6);
        dispatcher.empleadosSupervisores.add(empleado7);
        dispatcher.empleadosSupervisores.add(empleado8);
        dispatcher.empleadosSupervisores.add(empleado9);
        dispatcher.empleadosDirectores.add(empleado10);
        /**AGREGAMOS LAS LLAMADAS DE PRUEBA A LAS LISTAS CORRESPONDIENTES*/
        dispatcher.llamadas.add(llamada1);
        dispatcher.llamadas.add(llamada2);
        dispatcher.llamadas.add(llamada3);
        dispatcher.llamadas.add(llamada4);
        dispatcher.llamadas.add(llamada5);
        dispatcher.llamadas.add(llamada6);
        dispatcher.llamadas.add(llamada7);
        dispatcher.llamadas.add(llamada8);
        dispatcher.llamadas.add(llamada9);
        dispatcher.llamadas.add(llamada10);
        /**INVOCAMOS EL METODO QUE ADMINISTRA LLAMADAS*/
        dispatcher.administradorLLamadas();
        /**COMO EN NUESTRA PRUEBA TENEMOS 10 LLAMADAS EL ATRIBUTO LLAMADAS ATENDIDAS DEBE TENER UN VALOR DE 10*/
        assertEquals(10,dispatcher.llamadasAtendidas);
    }

    /**TEST UNITARIO QUE NOS CONFIRMA QUE UN EMPLEADO DISPONIBLE SI ESTE SIENDO ENCONTRADO*/
    @Test
    void buscarEmpleadoDisponible() {
        Dispatcher dispatcher = new Dispatcher();
        Empleado test=new Empleado("test",false,"test 1");
        dispatcher.empleadosOperadores.add(test);
        assertNotNull(dispatcher.buscarEmpleadoDisponible());
    }
}