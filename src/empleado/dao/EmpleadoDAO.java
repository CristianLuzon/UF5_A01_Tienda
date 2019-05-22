package empleado.dao;

import empleado.dominio.Empleado;
import java.util.List;

public interface EmpleadoDAO
{
    List<Empleado> leerEmpleados(); /*Read*/
    
    void CambiarContrasena(List<Empleado> empleados);/*Update password*/
}
