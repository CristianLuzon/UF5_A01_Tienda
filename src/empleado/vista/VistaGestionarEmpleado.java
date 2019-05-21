package empleado.vista;

import java.util.Scanner;
import tienda.vista.VistaTienda;

public class VistaGestionarEmpleado
{
    public static void loginBienvenida()
    {
        VistaTienda.borrarPantalla();
        VistaTienda.mostarMensaje(String.format("%s%n%s%n%s%n%n", "Le damos la bienvenida a la tienda.",
                "Debe introducir sus credenciales para poder acceder al sistema.", 
                "Registro..."));
    }
    public static int loginCodigo()
    {
        Scanner scan = new Scanner(System.in);
        VistaTienda.mostarMensaje("Introduzca su codigo de usuario: ");
        while (!scan.hasNextInt())
        {
            VistaTienda.mostarMensaje("Necesito un caracter numerico.\n" +
                            "Introduzca su codigo de usuario: ");
            scan.next();
        }
        return scan.nextInt();
    }
    public static String loginContrasena()
    {
        Scanner scan = new Scanner(System.in);
        VistaTienda.mostarMensaje("Introduzca su contraseña: ");
        String contrasena = "";
        
        while(contrasena.isBlank())
        {
            contrasena = scan.next();
        }
        return contrasena;
    }
    public static void cambiarContrasena(String nombre)
    {
        VistaTienda.mostarMensaje(
            "Por favor "+nombre+". Introduza su nueva contraseña.\n");
    }
}
