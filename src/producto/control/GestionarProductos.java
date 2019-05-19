package producto.control;

import java.util.List;
import java.util.Scanner;
import producto.dominio.Producto;
import producto.vista.VistaGestionarProducto;

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
            scan.next();
            
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
                    System.out.print("El codigo es identica al anterior!\nNuevo Codigo: ");
                }
            }
        }
        System.out.println("Contraseña cambiada con exito.");
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
            nuevoNombre = scan.next();
                
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
                System.out.print("Nombre es identica al anterior!\nNuevo Nombre: ");
            }

        }
        System.out.println("Nombre del producto cambiado con exito.");
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
            scan.next();
            
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
                    System.out.print("El precio es identica al anterior!\nNuevo Precio: ");
                }
            }
        }
        System.out.println("Precio cambiada con exito.");
    }
}
