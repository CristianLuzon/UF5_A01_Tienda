package producto.dao;

import empleado.dominio.Empleado;
import excepciones.empleado.ErrorAccediendoArchivoEmpleados;
import excepciones.producto.ErrorAccediendoArchivoProductos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import producto.dao.ProductoDAO;
import producto.dominio.Producto;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.CodigoError;

public class ProductoDAOImp implements ProductoDAO
{  
    private static final Path archivoProductos;
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
    /*Constructor*/
    static
    {
        archivoProductos = Paths.get("productos.txt");
    }
    public ProductoDAOImp()
    {
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }
    /*Metodo para cargar todos los productos de la BBDD en el programa.*/
    @Override
    public List<Producto> leerProductos() throws ErrorAccediendoArchivoProductos
    {
        List<Producto> productos = new ArrayList<>();
                
        try(BufferedReader archivo = Files.newBufferedReader(archivoProductos))
        {
            while(archivo.readLine() != null)
            {
                //Codigo
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                numero = formatoNumero.parse(lineaDato);
                int codigo = numero.intValue();
                
                //Nombre
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                String nombre = lineaDato;
                
                //Descripcion
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                String descripcion = lineaDato;
                
                //Precio
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                numero = formatoNumero.parse(lineaDato);
                double precio = numero.doubleValue();
                
                //Añadir producto
                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }
        }
        catch (ParseException ex)
        {
            throw new ErrorAccediendoArchivoProductos(
                    "Error de formato en " + archivoProductos + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
        catch (IOException ex)
        {
            throw new ErrorAccediendoArchivoProductos(
                    "Error de lectura en " + archivoProductos + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
        return productos;
    }
    @Override
    public void actualizarProductos(List<Producto> productos) throws ErrorAccediendoArchivoProductos
    {
        try(BufferedWriter archivo = Files.newBufferedWriter(archivoProductos))
        {
            for (Producto prod : productos)
            {
                archivo.write(prod.toFile() + "\n\r");
            }
        }
        catch(IOException ex)
        {
            throw new ErrorAccediendoArchivoProductos(
                    "Error de escritura en " + archivoProductos + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
    }
}
