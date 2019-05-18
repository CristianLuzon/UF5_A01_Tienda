package empleado.vista;

import java.util.Scanner;

public class VistaControladorEmpleado
{
    
    public static void loginBienvenida()
    {
        System.out.println(String.format("%s%n%s%n%s", "Le damos la a la tienda.",
                "Debe introducir sus credenciales para poder acceder al sistema.", 
                "Registro...\n"));
    }
    public static int loginCodigo()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca su codigo de usuario: ");
        while (!scan.hasNextInt())
        {
            System.out.println("Necesito un caracter numerico.\n" +
                            "Introduzca su codigo de usuario: ");
            scan.next();
        }
        return scan.nextInt();
    }
    public static String loginContrasena()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Introduzca su contraseña: ");
        String contrasena = "";
        
        while(contrasena.isBlank())
        {
            contrasena = scan.next();
        }
        return contrasena;
    }
    public static void cambiarContrasena(String nombre)
    {
        System.out.print(
            "Por favor "+nombre+". Introduza su nueva contraseña.\nNueva contraseña: ");
    }
}
