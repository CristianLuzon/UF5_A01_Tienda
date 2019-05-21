package empleado.control;

import empleado.dao.*;
import empleado.dominio.Empleado;
import excepciones.tienda.ErrorAccediendoATiendaException;
import java.util.List;
import tienda.vista.VistaTienda;
import util.Color;

public class ControladorEmpleado
{
    private static EmpleadoDAO empleadoDAO;
    
    static
    {
        empleadoDAO = new EmpleadoDAOImp();
    }
    public List<Empleado> leerEmpleados()
    {
        try
        {
            return empleadoDAO.leerEmpleados();
        }
        catch (ErrorAccediendoATiendaException ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause(), Color.ERROR);
            VistaTienda.esperarEnter();
        }
        return null;
    }
    public boolean actualizarEmpleados(List<Empleado> empleados)
    {
        return empleadoDAO.actualizarEmpleados(empleados);
    }
    public Empleado obtenerEmpleado(int tCodigo)
    {
        return empleadoDAO.obtenerEmpleado(tCodigo);
    }
    public void cambiarContrasena(Empleado empleado, String nuevaContrasena)
    {
        empleadoDAO.CambiarContrasena(empleado, nuevaContrasena);
    }
}
