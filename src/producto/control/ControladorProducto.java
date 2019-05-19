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
    public void modificarCodigo(Producto producto, int nuevoCodigo)
    {
        productoDAO.modificarCodigo(producto, nuevoCodigo);
    }
    public void modificarNombre(Producto producto, String nuevoNombre)
    {
        productoDAO.modificarNombre(producto, nuevoNombre);
    }
    public void modificarPrecio(Producto producto, float nuevoPrecio)
    {
        productoDAO.modificarPrecio(producto, nuevoPrecio);
    }
}
