package producto.control;

import java.util.List;
import java.util.Scanner;
import producto.dominio.Producto;
import producto.vista.VistaGestionarProducto;
import tienda.vista.VistaTienda;
import util.Color;

public class GestionarProductos
{
    List<Producto> productos;
    ControladorProducto controlador;
    
    public GestionarProductos()
    {
        controlador = new ControladorProducto();
        productos = controlador.leerProductos();
    }
    

    public String mostrarProductos()
    {
        String listarProductos = "";
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            listarProductos += productos.get(i).toString();
            listarProductos += "\n";
        }
        return listarProductos;
    }
    public Producto obtenerProductoPorCodigo(int codigo)
    {
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            if(codigo == productos.get(i).getCodigo())
                return productos.get(i);
        }
        return null;
    }
    
    public boolean codigoProductoExiste(int codigo)
    {
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            if(codigo == productos.get(i).getCodigo())
                return true;
        }
        return false;
    }
    public boolean nombreProductoExiste(String nombre)
    {
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            if(nombre.equals(productos.get(i).getNombre()))
                return true;
        }
        return false;
    }
    
    public void modificarCodigo(int codigoProducto)
    {
        VistaGestionarProducto.modificarCodigoProducto();
        
        Scanner scan = new Scanner(System.in);
        boolean datosCorrectos = false;
        int nuevoCodigo = 0;
        Producto producto = obtenerProductoPorCodigo(codigoProducto);
        
        while (!datosCorrectos)
        {            
            VistaTienda.mostarMensaje("nuevo Codigo: ");
            
            if(scan.hasNextInt())
            {
                nuevoCodigo = scan.nextInt();
                
                if(nuevoCodigo != producto.getCodigo())
                {
                    if(!codigoProductoExiste(nuevoCodigo))
                    {
                        controlador.modificarCodigo(producto, nuevoCodigo);
                        datosCorrectos = true;
                    }
                    else
                    {
                        /*Excepción*/
                        System.out.println("Este codigo de producto ya existe");
                    }
                }
                else
                {
                    /*Excepción*/
                    System.out.println("El codigo es identica al anterior!");
                }
            }
            else
            {
                /*Excepción*/
                System.out.println("Valor incorrecto");
                scan.next();
            }
        }
        VistaTienda.mostarMensaje("Codigo del producto cambiada con exito.", Color.CORRECTO);
    }
    public void modificarNombre(int codigoProducto)
    {
        VistaGestionarProducto.modificarNombreProducto();
        
        Scanner scan = new Scanner(System.in);
        boolean datosCorrectos = false;
        String nuevoNombre = "";
        Producto producto = obtenerProductoPorCodigo(codigoProducto);
        
        while (!datosCorrectos)
        {   
            VistaTienda.mostarMensaje("Nuevo nombre: ");
            nuevoNombre = scan.nextLine();
            
            if(!nuevoNombre.equals(producto.getNombre()))
            {
                if(!nombreProductoExiste(nuevoNombre))
                {
                    controlador.modificarNombre(producto, nuevoNombre);
                    datosCorrectos = true;
                }
                else
                {
                    /*Excepción*/
                    System.out.println("Este nombre de producto ya existe");
                }
            }
            else
            {
                /*Excepción*/
                System.out.println("Ese Nombre es identica al anterior!");
            }
        }
        VistaTienda.mostarMensaje("Nombre del producto cambiado con exito.", Color.CORRECTO);
    }
    public void modificarPrecio(int codigoProducto)
    {
        VistaGestionarProducto.modificarPrecioProducto();
        
        Scanner scan = new Scanner(System.in);
        boolean datosCorrectos = false;
        float nuevoPrecio = 0.0f;
        Producto producto = obtenerProductoPorCodigo(codigoProducto);
        
        while (!datosCorrectos)
        {
            VistaTienda.mostarMensaje("Nuevo precio: ");
            if(scan.hasNextFloat())
            {
                nuevoPrecio = scan.nextFloat();
                
                if(nuevoPrecio != producto.getPrecio())
                {
                    controlador.modificarPrecio(producto, nuevoPrecio);
                    datosCorrectos = true;
                }
                else
                {
                    /*Excepción*/
                    System.out.println("El precio es identica al anterior!");
                }
            }
            else
            {
                /*Excepción*/
                System.out.println("Incorrecto");
                scan.next();
            }
        }
        VistaTienda.mostarMensaje("Precio cambiada con exito.", Color.CORRECTO);
    }
}
