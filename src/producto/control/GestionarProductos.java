package producto.control;

import excepciones.producto.CodProductoRepetidoException;
import java.util.List;
import java.util.Scanner;
import producto.dominio.Producto;
import producto.vista.VistaGestionarProducto;
import tienda.vista.VistaTienda;
import util.CodigoError;
import util.Color;

public class GestionarProductos
{
    private List<Producto> productos;
    private ControladorProducto controlador;
    
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
    public int modificarCodigo(int codigoProducto) throws CodProductoRepetidoException
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
                        producto.setCodigo(nuevoCodigo);
                        controlador.actualizarProductos(productos);
                        datosCorrectos = true;
                        VistaTienda.mostarMensaje("Codigo del producto cambiada con exito.\n", Color.CORRECTO);
                        VistaTienda.esperarEnter();
                        return nuevoCodigo;
                    }
                    else
                    {
                        throw new CodProductoRepetidoException("Este codigo de producto ya existe", CodigoError.CODIGO_PRODUCTO_REPETIDO);
                    }
                }
                else
                {
                    VistaTienda.mostarMensaje("El codigo es identica al anterior!\n", Color.ERROR);
                    VistaTienda.esperarEnter();
                }
            }
            else
            {
                VistaTienda.mostarMensaje("Valor incorrecto\n", Color.ERROR);
                VistaTienda.esperarEnter();
            }
        }
        return -1;
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
                    producto.setNombre(nuevoNombre);
                    controlador.actualizarProductos(productos);
                    datosCorrectos = true;
                    VistaTienda.mostarMensaje("Nombre del producto cambiado con exito.\n", Color.CORRECTO);
                    VistaTienda.esperarEnter();
                }
                else
                {
                    VistaTienda.mostarMensaje("Este nombre de producto ya existe\n", Color.ERROR);
                    VistaTienda.esperarEnter();
                }
            }
            else
            {
                VistaTienda.mostarMensaje("Ese Nombre es identica al anterior!\n", Color.ERROR);
                VistaTienda.esperarEnter();
            }
        } 
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
                producto.setPrecio(nuevoPrecio);
                controlador.actualizarProductos(productos);
                datosCorrectos = true;
                VistaTienda.mostarMensaje("Precio cambiada con exito.\n", Color.CORRECTO);
                VistaTienda.esperarEnter();
            }
            else
            {
                VistaTienda.mostarMensaje("Valor incorrecto, no es un numero entero.\n", Color.ERROR);
                VistaTienda.esperarEnter();
            }
        }
    }
}
