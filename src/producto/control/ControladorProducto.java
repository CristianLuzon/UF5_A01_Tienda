package producto.control;

import excepciones.producto.ErrorAccediendoArchivoProductos;
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
        try
        {
            return productoDAO.leerProductos();
        }
        catch (ErrorAccediendoArchivoProductos ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause() + "\n", Color.ERROR);
            VistaTienda.esperarEnter();
            System.exit(1);
        }
        return null;
    }
    public void actualizarProductos(List<Producto> productos) 
    {
        try
        {
            productoDAO.actualizarProductos(productos);
        }
        catch (ErrorAccediendoArchivoProductos ex)
        {
            VistaTienda.mostarMensaje(ex.getMessage() + ex.getCause() + "\n", Color.ERROR);
            VistaTienda.esperarEnter();
            System.exit(1);
        }
    }
}
