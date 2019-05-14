package producto.dao;

import conexion.ConexionBBDD;
import producto.dao.ProductoDAO;
import producto.dominio.Producto;

import java.sql.*;
import java.text.*;
import java.util.*;

public class ProductoDAOImp implements ProductoDAO
{
//    private static final Path archivoProductos;
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
    
//    static
//    {
//        archivoProductos = Paths.get("productos.txt");
//    }
    
    public ProductoDAOImp()
    {
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }

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
            System.out.println("La petición a fallado\n" +
                this.getClass().getName());
        }
        return productos;
    }
    //Leer de archivo
//    public List<Producto> leerProductos()
//    {
//        List<Producto> productos = new ArrayList<>();
//                
//        try(BufferedReader archivo = Files.newBufferedReader(archivoProductos))
//        {
//            while(archivo.readLine() != null)
//            {
//                //Codigo
//                archivo.readLine();
//                lineaDato = archivo.readLine().trim();
//                numero = formatoNumero.parse(lineaDato);
//                int codigo = numero.intValue();
//                
//                //Nombre
//                archivo.readLine();
//                lineaDato = archivo.readLine().trim();
//                String nombre = lineaDato;
//                
//                //Descripcion
//                archivo.readLine();
//                lineaDato = archivo.readLine().trim();
//                String descripcion = lineaDato;
//                
//                //Precio
//                archivo.readLine();
//                lineaDato = archivo.readLine().trim();
//                numero = formatoNumero.parse(lineaDato);
//                double precio = numero.doubleValue();
//                
//                //Añadir producto
//                productos.add(new Producto(codigo, nombre, descripcion, precio));
//            }
//        }
//        catch (ParseException ex)
//        {
//            System.out.println("Error de formato en " + archivoProductos);
//            System.exit(1);
//        }
//        catch (IOException ex)
//        {
//            System.out.println("Error de lectura en " + archivoProductos);
//            System.exit(1);
//        }
//        return productos;
//    }

    @Override
    public boolean actualizarProductos(List<Producto> empleados)
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
            System.out.println("La petición a fallado\n" +
                this.getClass().getName());
            return false;
        }
        return true;
    }
}
