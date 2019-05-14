package producto.control;

import java.util.List;
import producto.dao.*;
import producto.dominio.Producto;

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
    public boolean actualizarEmpleados(List<Producto> productos)
    {
        return productoDAO.actualizarProductos(productos);
    }
}
