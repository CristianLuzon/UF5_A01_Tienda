package persistencia.imp;

import dominio.Empleado;
import persistencia.def.EmpleadoDAO;

import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class EmpleadoDAOImp implements EmpleadoDAO
{
    private final Path archivoEmpleados;
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
    
    
    public EmpleadoDAOImp()
    {
        archivoEmpleados = Paths.get("empleados.txt");
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }
    
    @Override
    public List<Empleado> leerEmpleados()
    {
        List<Empleado> empleados = new ArrayList<>();
        
        try(BufferedReader archivo = Files.newBufferedReader(archivoEmpleados))
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
                
                //Apellido
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                String apellido = lineaDato;
                
                //Contraseña
                archivo.readLine();
                lineaDato = archivo.readLine().trim();
                String contrasena = lineaDato;
                
                //Añadir producto
                empleados.add(new Empleado(codigo, nombre, apellido, contrasena));
            }
        }
        catch (ParseException ex)
        {
            System.out.println("Error de formato en " + archivoEmpleados);
            System.exit(1);
        }
        catch (IOException ex)
        {
            System.out.println("Error de lectura en " + archivoEmpleados);
            System.exit(1);
        }
        return empleados;
    }

    @Override
    public boolean actualizarEmpleados(List<Empleado> empleados)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}