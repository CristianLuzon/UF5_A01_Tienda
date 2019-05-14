package tienda.vista;

import java.util.Scanner;
import util.Color;

public class VistaTienda
{
    public static enum MenuPrincipal
    {
        HACER_PEDIDO,
        MODIFICAR_PRODUCTO,
        CAMBIAR_PRODUCTO,
        CAMBIAR_CONTRASENA,
        CERRAR_SESION
        
    }
    //MenuPrincipal menuPrincipal;
    
    public static MenuPrincipal OpcionesMenuPincipal()
    {
        borrarPantalla();
        System.out.println("--Menú principal -------------------");
        System.out.println("   1. Hacer pedido");
        System.out.println("   2. Modificar producto");
        System.out.println("   3. Cambiar contraseña de empleado");
        System.out.println("   4. Cerrar sesión");
        System.out.println("------------------------------------");

        int opcion = pedirOpcionEnRango(1, 4);
        MenuPrincipal menu = null;

        switch (opcion) {
            case 1:
                menu = MenuPrincipal.HACER_PEDIDO;
                break;
            case 2:
                menu = MenuPrincipal.MODIFICAR_PRODUCTO;
                break;
            case 3:
                menu = MenuPrincipal.CAMBIAR_CONTRASENA;
                break;
            case 4:
                menu = MenuPrincipal.CERRAR_SESION;
                break;
        }
        return menu;
    }

    private static int pedirOpcionEnRango(int min, int max)
    {
        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError) {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt()) {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) {
                    mostarMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                }
            } else {
                mostarMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                leerTeclado.next();
            }
        }
        return opcion;
    }
    
    public static void mostarMensaje(String mensaje)
    {
        System.out.println(mensaje + Color.DEFAULT);
    }
    public static void mostarMensaje(String mensaje, Color color)
    {
        System.out.println(color + mensaje + Color.DEFAULT);
    }
    
    private static void borrarPantalla()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
}
    
}
