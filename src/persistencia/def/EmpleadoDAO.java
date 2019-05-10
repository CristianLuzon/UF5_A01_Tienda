package persistencia.def;

import dominio.Empleado;

import java.util.List;

public interface EmpleadoDAO
{
    List<Empleado> leerEmpleados(); /*Read*/
    
    boolean actualizarEmpleados(List<Empleado> empleados);
    
    
}
