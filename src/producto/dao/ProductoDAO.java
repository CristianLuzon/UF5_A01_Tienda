package producto.dao;

import producto.dominio.Producto;

import java.util.List;

public interface ProductoDAO
{
    List<Producto> leerProductos(); /*Read*/
    
    boolean actualizarProductos(List<Producto> empleados);/*Update*/
    
}
