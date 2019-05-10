package persistencia.imp;

import persistencia.def.ProductoDAO;
import dominio.Producto;

import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class ProductoDAOImp implements ProductoDAO
{
    private final Path archivoProductos;
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
    
    
    public ProductoDAOImp()
    {
        archivoProductos = Paths.get("productos.txt");
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }
    
    @Override
    public List<Producto> leerProductos()
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
            System.out.println("Error de formato en " + archivoProductos);
            System.exit(1);
        }
        catch (IOException ex)
        {
            System.out.println("Error de lectura en " + archivoProductos);
            System.exit(1);
        }
        return productos;
    }

    @Override
    public boolean actualizarProductos(List<Producto> empleados)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
