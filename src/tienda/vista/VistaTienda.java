package tienda.vista;

import excepciones.producto.CodProductoInexistenteException;
import java.util.List;
import java.util.Scanner;
import producto.control.GestionarProductos;
import producto.dominio.Producto;
import util.CodigoError;
import util.Color;
import util.MenuHacerPedido;
import util.MenuModificarProducto;
import util.MenuPrincipal;

public class VistaTienda
{    
    public static boolean cerrarPrograma()
    {
        mostarMensaje("Desea inicar sesión?\n\'Sí\', registrarme.\n\'No\' cierra el programa.\n");
        
        return preguntar("Elección: ");
    }
    public static void bienvenidaEmpleado(String nombre)
    {
        mostarMensaje(String.format("%nBienvenide %s%n%n", nombre));
    }
    /*Metodo del Menú principal*/
    public static MenuPrincipal OpcionesMenuPincipal()
    {
        borrarPantalla();
        mostarMensaje(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%n",
                "-------------Menú principal------------",
                "   1. Hacer pedido", 
                "   2. Modificar producto",
                "   3. Cambiar contraseña de empleado",
                "   4. Cerrar sesión",
                "---------------------------------------"));

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
    /*Metodos del Menú pedido*/
    public static MenuHacerPedido OpcionesHacerPedido()
    {
        borrarPantalla();
        mostarMensaje(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%n",
                "---------Acciones de la Cesta----------",
                "   1. Añadir Producto a la Cesta",
                "   2. Ver el Precio total de la Cesta",
                "   3. Imprimir factura",
                "   4. Finalizar pedido",
                "---------------------------------------"));
        
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
    /*Sub menus del MenuHacerPedido*/
    public static int OpcionAgregarProducto(GestionarProductos listaProductos)
    {
        borrarPantalla();
        mostarMensaje(String.format("%s%n%s%n%s%n%s%n%n",
                "\nQué producto quieres añadir?",
                "------------Productos----------------",
                listaProductos.mostrarProductos(),
                "-------------------------------------"));
        
        return pedirCodigoProducto(listaProductos);
    }
    public static void opcionCosteTotalCesta(float precio)
    {
        mostarMensaje("\nLa cesta actual cuesta " + precio + "€.\n\n");
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
        mostarMensaje(factura);
    }
    /*Metodos del Menú para modificar producto*/
    public static MenuModificarProducto OpcionesModificarProducto()
    {
        borrarPantalla();
        mostarMensaje(String.format("%s%n%s%n%s%n%s%n%s%n%s%n%n",
                "-------Modificación del producto-------",
                "   1. Modificar codigo",
                "   2. Modificar nombre",
                "   3. Modificar precio",
                "   4. Terminar modificación",
                "---------------------------------------"));
        
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
    /*Sub metodos de la modificación del producto*/
    public static int elegirCodigoProductoModificar(GestionarProductos listaProductos)
    {
        borrarPantalla();
        mostarMensaje(listaProductos.mostrarProductos() + "\n");
        mostarMensaje("Introduzca el codigo de producto a modificar.\n");
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
                scan.next();
                throw new CodProductoInexistenteException("Este codigo de producto no exite.", CodigoError.CODIGO_EMPLE_INCORRECTO);
            }
        }
        return codigo;
    }
    
    /*Metodos varios*/
    private static int pedirOpcionEnRango(int min, int max)
    {
        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError)
        {
            mostarMensaje("Seleccione una opción: ");
            if (leerTeclado.hasNextInt())
            {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) 
                {
                    mostarMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]\n", Color.ERROR);
                }
            } 
            else
            {
                mostarMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]\n", Color.ERROR);
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
            mostarMensaje("Seleccione una opción: ");
            if (leerTeclado.hasNextInt())
            {
                opcion = leerTeclado.nextInt();
                existe = gestion.obtenerProductoPorCodigo(opcion) == null ? false : true;
                if(!existe)
                {
                    mostarMensaje("Este codigo no existe. Vuelva a intentarlo.\n");
                }
                else
                {
                    mostarMensaje(String.format(
                        "El producto(%d) ha sido añadido con exito.%n", opcion));
                }
            }
            else
            {
                mostarMensaje("Error, codigo no válida.\n");
                leerTeclado.next();
            }
        }
        //leerTeclado.next();//no se si funciona esto
        return opcion;
    }
    
    public static void mostarMensaje(String mensaje)
    {
        System.out.print(mensaje + Color.SERIE);
    }
    public static void mostarMensaje(String mensaje, Color color)
    {
        System.out.print(color + mensaje + Color.SERIE);
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
        mostarMensaje(pregunta);
        while(!datosCorrectos)
        {
            respuesta = scan.nextLine().toLowerCase();
            if(!respuesta.equals("si") && !respuesta.equals("no"))
                mostarMensaje("Respuesta invalida. Escriba \"Si\" o \"No\"\n");
            else
                datosCorrectos = true;
        }
        return  respuesta.equals("si") ? true : false;
    }
    
}
