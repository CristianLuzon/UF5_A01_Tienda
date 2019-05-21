package empleado.control;

import empleado.dominio.Empleado;
import empleado.vista.VistaGestionarEmpleado;
import excepciones.empleado.CodEmpleErrorException;
import excepciones.empleado.ContraEmpleErrorException;
import java.util.Scanner;
import tienda.vista.VistaTienda;
import util.CodigoError;
import util.Color;

public class GestionarEmpleados
{
    private ControladorEmpleado controlador;
    private Empleado empleadoOnline;
    
    public GestionarEmpleados()
    {
        controlador = new ControladorEmpleado();
        empleadoOnline = null;
    }
    
    public void login() throws CodEmpleErrorException, ContraEmpleErrorException
    {
        boolean empleValido = false;
        boolean contraValida = false;
        
        VistaGestionarEmpleado.loginBienvenida();
        
        int codigoEntrada = VistaGestionarEmpleado.loginCodigo();
        String contraEntrada = VistaGestionarEmpleado.loginContrasena();
        
        empleadoOnline = controlador.obtenerEmpleado(codigoEntrada);
        
        if(empleadoOnline != null)
        {
            empleValido = true;
            if(contraEntrada.equals(empleadoOnline.getContrasena()))
                contraValida = true;
        }
        
        if (!empleValido)
            throw new CodEmpleErrorException("Este codigo de empleado no existe", CodigoError.CODIGO_EMPLE_INCORRECTO);
        else if(!contraValida)
            throw new ContraEmpleErrorException("Contraseña incorrecta.\n", CodigoError.CONTRA_EMPLE_INCORRECTO);
    }
    
    public void cambiarContrasena()
    {
        VistaGestionarEmpleado.cambiarContrasena(empleadoOnline.getNombre());
        
        Scanner scan = new Scanner(System.in);
        boolean datosCorrectos = false;
        String nuevaContrasena = "";
        String reNuevaContrasena = "";
        
        while (!datosCorrectos)
        {
            VistaTienda.mostarMensaje("Nueva contraseña: ");
            nuevaContrasena = scan.next();
            VistaTienda.mostarMensaje("Repetir contraseña: ");
            reNuevaContrasena = scan.next();
            
            if(nuevaContrasena.equals(reNuevaContrasena))
            {
                if(!nuevaContrasena.equals(empleadoOnline.getContrasena()))
                {
                    controlador.cambiarContrasena(empleadoOnline, nuevaContrasena);
                    datosCorrectos = true;
                    VistaTienda.mostarMensaje("Contraseña cambiada con exito.\n", Color.CORRECTO);
                    VistaTienda.esperarEnter();
                }
                else
                {
                    VistaTienda.mostarMensaje("La contraseña es identica a la anterior!", Color.ERROR);
                    VistaTienda.esperarEnter();
                }
            }
            else
            {
               VistaTienda.mostarMensaje("La contraseña no coincide! Vuelva a intentarlo.", Color.ERROR);
               VistaTienda.esperarEnter();
            }
        }
    }
    
    public Empleado getEmpleadoOnline()
    {
        return empleadoOnline;
    }
}
