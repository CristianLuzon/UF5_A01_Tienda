package empleado.control;

import empleado.dominio.Empleado;
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Bienvenido a la tienda");
        
        System.out.print("Introduzca su codigo de usuario: ");
        while (!scan.hasNextInt())
        {
            System.out.println("Necesito un caracter numerico.");
            System.out.print("Introduzca su codigo de usuario: ");
            scan.next();
        }
        int codigoEntrada = scan.nextInt();
        
        System.out.print("Introduzca la contraseña: ");
        String contraEntrada = scan.next();
        
        empleadoOnline = controlador.obtenetEmpleado(codigoEntrada);
        
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
    
    public Empleado getEmpleadoOnline()
    {
        return empleadoOnline;
    }
}
