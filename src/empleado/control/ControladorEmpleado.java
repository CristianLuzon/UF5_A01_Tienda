package empleado.control;

import empleado.dao.*;
import empleado.dominio.Empleado;
import excepciones.empleado.ErrorAccediendoArchivoEmpleados;
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
        catch (ErrorAccediendoArchivoEmpleados ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause() + "\n", Color.ERROR);
            VistaTienda.esperarEnter();
            System.exit(1);
        }
        return null;
    }
    public void cambiarContrasena(List<Empleado> empleados)
    {
        try
        {
            empleadoDAO.CambiarContrasena(empleados);
        } 
        catch (ErrorAccediendoArchivoEmpleados e)
        {
            VistaTienda.mostarMensaje(e.getMessage() + e.getCause() + "\n", Color.ERROR);
            VistaTienda.esperarEnter();
            System.exit(1);
        }
    }
}
