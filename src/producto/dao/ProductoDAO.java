package producto.dao;

import producto.dominio.Producto;

import java.util.List;

public interface ProductoDAO
{
    List<Producto> leerProductos(); /*Read*/
    
    void actualizarProductos(List<Producto> empleados);/*Update all*/
}
