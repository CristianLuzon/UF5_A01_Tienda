package empleado.control;

import empleado.dominio.Empleado;
import empleado.vista.VistaGestionarEmpleado;
import java.util.Scanner;

public class GestionarEmpleados
{
    private ControladorEmpleado controlador;
    private Empleado empleadoOnline;
    
    public GestionarEmpleados()
    {
        controlador = new ControladorEmpleado();
        empleadoOnline = null;
    }
    
    public void login() throws Exception
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
            throw new Exception("Empleado inexistente");
        else if(!contraValida)
            throw new Exception("Contraseña incorrecta");
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
            nuevaContrasena = scan.next();
            System.out.print("Repetir contraseña: ");
            reNuevaContrasena = scan.next();
            
            if(nuevaContrasena.equals(reNuevaContrasena))
            {
                if(!nuevaContrasena.equals(empleadoOnline.getContrasena()))
                {
                    controlador.cambiarContrasena(empleadoOnline, nuevaContrasena);
                    datosCorrectos = true;
                }
                else
                {
                    /*Excepción*/
                    System.out.print("La contraseña es identica a la anterior!\nContraseña: ");
                }
            }
            else
            {
                /*Excepción*/
                System.out.println("La contraseña no coincide! Vuelva a intentarlo.\nContraseña: ");
            }
        }
        System.out.println("Contraseña cambiada con exito.");
    }
    
    public Empleado getEmpleadoOnline()
    {
        return empleadoOnline;
    }
}
