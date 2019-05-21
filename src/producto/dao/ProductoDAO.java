package producto.dao;

import producto.dominio.Producto;

import java.util.List;

public interface ProductoDAO
{
    List<Producto> leerProductos(); /*Read*/
    
    void actualizarProductos(List<Producto> empleados);/*Update*/
    
    public void modificarCodigo(Producto producto, int nuevoCodigo);/*Update codigo*/
    public void modificarNombre(Producto producto, String nuevoNombre);/*Update nombre*/
    public void modificarPrecio(Producto producto, float nuevoPrecio);/*Update precio*/
}
