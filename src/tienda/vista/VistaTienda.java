package tienda.vista;

import java.util.List;
import java.util.Scanner;
import producto.control.GestionarProductos;
import producto.dominio.Producto;
import util.Color;
import util.MenuHacerPedido;
import util.MenuModificarProducto;
import util.MenuPrincipal;

public class VistaTienda
{    
    public static void bienvenidaEmpleado(String nombre)
    {
        System.out.println(String.format("%nBienvenide %s%n", nombre));
    }
    public static MenuPrincipal OpcionesMenuPincipal()
    {
        borrarPantalla();
        System.out.println("-------------Menú principal------------");
        System.out.println("   1. Hacer pedido");
        System.out.println("   2. Modificar producto");
        System.out.println("   3. Cambiar contraseña de empleado");
        System.out.println("   4. Cerrar sesión");
        System.out.println("---------------------------------------");

        int opcion = pedirOpcionEnRango(1, 4);
        MenuPrincipal menu = null;

        switch (opcion)
        {
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
    /*Metodos del pedido*/
    public static MenuHacerPedido OpcionesHacerPedido()
    {
        borrarPantalla();
        System.out.println("---------Acciones de la Cesta----------");
        System.out.println("   1. Añadir Producto a la Cesta");
        System.out.println("   2. Ver el Precio total de la Cesta");
        System.out.println("   3. Imprimir factura");
        System.out.println("   4. Finalizar pedido");
        System.out.println("---------------------------------------");
        
        int opcion = pedirOpcionEnRango(1, 4);
        MenuHacerPedido pedido = null;
        
        switch (opcion)
        {
            case 1:
                pedido = MenuHacerPedido.AGREGAR_PRODUCTO;
                break;
            case 2:
                pedido = MenuHacerPedido.COSTE_CESTA;
                break;
            case 3:
                pedido = MenuHacerPedido.IMPRIMIR_FACTURA;
                break;
            case 4:
                pedido = MenuHacerPedido.TERMINAR_PEDIDO;
                break;
        }
        return pedido;
    }
    /*Sub menus y metodos del MenuHacerPedido*/
    public static int OpcionAgregarProducto(GestionarProductos listaProductos)
    {
        borrarPantalla();
        System.out.println("\nQué producto quieres añadir?");
        System.out.println("------------Productos----------------");
        System.out.print(listaProductos.mostrarProductos());
        System.out.println("-------------------------------------");
        
        return pedirCodigoProducto(listaProductos);
    }
    public static void opcionCosteTotalCesta(float precio)
    {
        System.out.println("\nLa cesta actual cuesta " + precio + "€.\n");
    }
    public static void opcionImprimirFactura(List<Producto> cesta, float total, String empleado)
    {
        String factura = "\nFactura simplificada:\n----------------------------------------\n";
        for(int i = 0, t = cesta.size(); i < t; i++)
        {
            factura += String.format(
                    "Código:\t\t%s%nNombre:\t\t%s%nDescripción:\t%s%nPrecio:\t\t%s€%n", 
                    cesta.get(i).getCodigo(), cesta.get(i).getNombre(),
                    cesta.get(i).getDescripcion(), cesta.get(i).getPrecio());
        }
        factura +="----------------------------------------\n";
        factura += String.format("Precio total: %.2f€%nAtendido por: %s%n",
                total, empleado);
        System.out.println(factura);
    }
    /**/
    public static MenuModificarProducto OpcionesModificarProducto()
    {
        borrarPantalla();
        System.out.println("-------Modificación del producto-------");
        System.out.println("   1. Modificar codigo");
        System.out.println("   2. Modificar nombre");
        System.out.println("   3. Modificar precio");
        System.out.println("   4. Terminar modificación");
        System.out.println("---------------------------------------");
        
        int opcion = pedirOpcionEnRango(1, 4);
        MenuModificarProducto modifiacion = null;

        switch (opcion)
        {
            case 1:
                modifiacion = MenuModificarProducto.MODIFICAR_CODIGO;
                break;
            case 2:
                modifiacion = MenuModificarProducto.MODIFICAR_NOMBRE;
                break;
            case 3:
                modifiacion = MenuModificarProducto.MODIFICAR_PRECIO;
                break;
            case 4:
                modifiacion = MenuModificarProducto.TERMINAR_MODIFICACION;
                break;
        }
        return modifiacion;
    }
    /*Metodos de la modificación del producto*/
    public static int elegirCodigoProductoModificar(GestionarProductos listaProductos)
    {
        borrarPantalla();
        System.out.println(listaProductos.mostrarProductos());
        System.out.println("Introduzca el codigo de producto a modificar.");
        Scanner scan = new Scanner(System.in);
        int codigo = 0;
        boolean hayError = true;

        while (hayError)
        {
            System.out.print("Codigo: ");
            if (scan.hasNextInt())
            {
                codigo = scan.nextInt();
                hayError = false;
            } 
            else
            {
                mostarMensaje("Error, codigo incorrecto", Color.ERROR);
                scan.next();
            }
        }
        return codigo;
    }

    private static int pedirOpcionEnRango(int min, int max)
    {
        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError)
        {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt())
            {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) 
                {
                    mostarMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                }
            } 
            else
            {
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
                existe = gestion.obtenerProductoPorCodigo(opcion) == null ? false : true;
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
        System.out.println(mensaje + Color.SERIE);
    }
    public static void mostarMensaje(String mensaje, Color color)
    {
        System.out.println(color + mensaje + Color.SERIE);
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
