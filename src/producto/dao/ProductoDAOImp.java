package producto.dao;

import conexion.ConexionBBDD;
import excepciones.tienda.ErrorAccediendoATiendaException;
import producto.dao.ProductoDAO;
import producto.dominio.Producto;
import java.sql.*;
import java.text.*;
import java.util.*;
import util.CodigoError;

public class ProductoDAOImp implements ProductoDAO
{  
    /*Metodo para cargar todos los productos de la BBDD en el programa.*/
    @Override
    public List<Producto> leerProductos()
    {
        List<Producto> productos = new ArrayList<>();
        String query = "select * from productos";
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);)
        {
            
            
            while (resultado.next())
            {                
                int codigo = resultado.getInt("p_codigo");
                String nombre = resultado.getString("p_nombre");
                String descripcion = resultado.getString("p_descripcion");
                double precio = resultado.getDouble("p_precio");
                
                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }
        }
        catch (SQLException ex)
        {
            throw new ErrorAccediendoATiendaException("La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
        return productos;
    }
    @Override
    public void actualizarProductos(List<Producto> empleados) throws ErrorAccediendoATiendaException
    {
        List<Producto> productos = new ArrayList<>();
        String query = "inset into Productos values";
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);)
        {
            while (resultado.next())
            {                
                int codigo = resultado.getInt("p_codigo");
                String nombre = resultado.getString("p_nombre");
                String descripcion = resultado.getString("p_descripcion");
                double precio = resultado.getDouble("p_precio");
                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }
        }
        catch (SQLException ex)
        {
           throw new ErrorAccediendoATiendaException("La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
    }
    @Override
    public void modificarCodigo(Producto producto, int nuevoCodigo) throws ErrorAccediendoATiendaException
    {
        String query = String.format(
                "update productos set p_codigo = '%d' where p_codigo = %d;",
                nuevoCodigo, producto.getCodigo());

        updateQueryProducto(query);
        producto.setCodigo(nuevoCodigo);
    }
    @Override
    public void modificarNombre(Producto producto, String nuevoNombre) throws ErrorAccediendoATiendaException
    {
        String query = String.format(
                "update productos set p_nombre = '%s' where p_codigo = %d;",
                nuevoNombre, producto.getCodigo());
        
            updateQueryProducto(query);
            producto.setNombre(nuevoNombre);
    }
    @Override
    public void modificarPrecio(Producto producto, float nuevoPrecio) throws ErrorAccediendoATiendaException
    {
        String query = String.format(
                "update productos set p_precio = %s where p_codigo = %d;",
                Float.toString(nuevoPrecio).replace(',', '.'), producto.getCodigo());

        updateQueryProducto(query);
        producto.setPrecio(nuevoPrecio);
    }
    /*Metodo global para acuatlizar los campos de la tabla productos*/
    public void updateQueryProducto(String query) throws ErrorAccediendoATiendaException
    {
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();)
        {   
            sentencia.executeUpdate(query);
        }
        catch (SQLException ex)
        {
            throw new ErrorAccediendoATiendaException("La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
    }
}
