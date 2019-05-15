package producto.control;

import java.util.List;
import producto.dominio.Producto;

public class GestionarProductos
{
    List<Producto> productos;
    ControladorProducto controlador;
    
    public GestionarProductos()
    {
        controlador = new ControladorProducto();
        productos = controlador.leerProductos();
    }
    
    /*private List<Producto> mostrarProductos()
    {
        return productos;
    }*/
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
    public Producto obtenerProductoCodigo(int codigo)
    {
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            if(codigo == productos.get(i).getCodigo())
                return productos.get(i);
        }
        return null;
    }
    
    public boolean productoExiste(int codigo)
    {
        for (int i = 0, t = productos.size(); i < t; i++)
        {
            if(codigo == productos.get(i).getCodigo())
                return true;
        }
        return false;
    }
}
