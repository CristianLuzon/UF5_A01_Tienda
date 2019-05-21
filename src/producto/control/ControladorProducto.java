package producto.control;

import excepciones.tienda.ErrorAccediendoATiendaException;
import java.util.List;
import producto.dao.*;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;
import util.Color;

public class ControladorProducto
{
    private static ProductoDAO productoDAO;

    static
    {
        productoDAO = new ProductoDAOImp();
    }
    
    public List<Producto> leerProductos()
    {
        return productoDAO.leerProductos();
    }
    public void actualizarProducto(List<Producto> productos)
    {
        try
        {
            productoDAO.actualizarProductos(productos);
        }
        catch (ErrorAccediendoATiendaException ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause(), Color.ERROR);
            VistaTienda.esperarEnter();
        }
    }
    public void modificarCodigo(Producto producto, int nuevoCodigo)
    {
        try
        {
            productoDAO.modificarCodigo(producto, nuevoCodigo);
        }
        catch (ErrorAccediendoATiendaException ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause(), Color.ERROR);
            VistaTienda.esperarEnter();
        }
    }
    public void modificarNombre(Producto producto, String nuevoNombre)
    {
        try
        {
            productoDAO.modificarNombre(producto, nuevoNombre);
        }
        catch (ErrorAccediendoATiendaException ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause(), Color.ERROR);
            VistaTienda.esperarEnter();
        }
    }
    public void modificarPrecio(Producto producto, float nuevoPrecio)
    {
        try
        {
            productoDAO.modificarPrecio(producto, nuevoPrecio);
        }
        catch (ErrorAccediendoATiendaException ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause(), Color.ERROR);
            VistaTienda.esperarEnter();
        }
    }
}
