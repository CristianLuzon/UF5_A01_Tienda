package empleado.control;

import empleado.dao.*;
import empleado.dominio.Empleado;
import java.util.List;

public class ControladorEmpleado
{
    private static EmpleadoDAO empleadoDAO;
    
    static
    {
        empleadoDAO = new EmpleadoDAOImp();
    }
    public List<Empleado> leerEmpleados()
    {
        return empleadoDAO.leerEmpleados();
    }
    public boolean actualizarEmpleados(List<Empleado> empleados)
    {
        return empleadoDAO.actualizarEmpleados(empleados);
    }
    public Empleado obtenetEmpleado(int tCodigo)
    {
        return empleadoDAO.obtenerEmpleado(tCodigo);
    }
}
