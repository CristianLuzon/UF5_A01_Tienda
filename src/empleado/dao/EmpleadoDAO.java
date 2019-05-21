package empleado.dao;

import empleado.dominio.Empleado;
import java.util.List;

public interface EmpleadoDAO
{
    List<Empleado> leerEmpleados(); /*Read*/
    
    boolean actualizarEmpleados(List<Empleado> empleados);/*Update*/
    
    Empleado obtenerEmpleado(int tCodigo);/*Get*/
    
    void CambiarContrasena(Empleado empleado, String nuevaContrasena);/*Update password*/
}
