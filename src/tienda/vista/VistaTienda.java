package tienda.vista;

import java.util.Scanner;
import producto.control.GestionarProductos;
import util.Color;
import util.MenuPrincipal;

public class VistaTienda
{
//    public static enum MenuPrincipal
//    {
//        HACER_PEDIDO,
//        MODIFICAR_PRODUCTO,
//        CAMBIAR_PRODUCTO,
//        CAMBIAR_CONTRASENA,
//        CERRAR_SESION
//        
//    }
    //public MenuPrincipal menuPrincipal;
    
    public static MenuPrincipal OpcionesMenuPincipal()
    {
        borrarPantalla();
        System.out.println("------------Menú principal-----------");
        System.out.println("   1. Hacer pedido");
        System.out.println("   2. Modificar producto");
        System.out.println("   3. Cambiar contraseña de empleado");
        System.out.println("   4. Cerrar sesión");
        System.out.println("-------------------------------------");

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
    
    public static int OpcionesHacerPedido(GestionarProductos listaProductos)
    {
        borrarPantalla();
        System.out.println("\nQué producto quieres añadir?");
        System.out.println("------------Productos----------------");
        System.out.print(listaProductos.mostrarProductos());
        System.out.println("-------------------------------------");
        
        return pedirCodigoProducto(listaProductos);
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
    
    private static int pedirCodigoProducto(GestionarProductos gestion)
    {
        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean existe = false;

        while (!existe)
        {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt())
            {
                opcion = leerTeclado.nextInt();
                existe = gestion.obtenerProductoCodigo(opcion) == null ? false : true;
                if(!existe)
                {
                    System.out.println("Este codigo no existe. Vuelva a intentarlo.");
                }
                else
                {
                    System.out.println(String.format(
                        "El producto(%d) ha sido añadido con exito.", opcion));
                }
            }
            else
            {
                mostarMensaje("Error, codigo no válida.");
                leerTeclado.next();
            }
        }
        //leerTeclado.next();//no se si funciona esto
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
    
    public static boolean preguntar(String pregunta)
    {
        Scanner scan = new Scanner(System.in);
        boolean datosCorrectos = false;
        String respuesta = "";
        System.out.println(pregunta);
        while(!datosCorrectos)
        {
            respuesta = scan.nextLine().toLowerCase();
            if(!respuesta.equals("si") && !respuesta.equals("no"))
                System.out.println("Respuesta invalida. Escriba \"Si\" o \"No\"");
            else
                datosCorrectos = true;
        }
        return  respuesta.equals("si") ? true : false;
    }
    
}
