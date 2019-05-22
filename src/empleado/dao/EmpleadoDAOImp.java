package empleado.dao;
import empleado.dominio.Empleado;
import empleado.dao.EmpleadoDAO;
import excepciones.empleado.ErrorAccediendoArchivoEmpleados;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import tienda.vista.VistaTienda;
import util.CodigoError;
import util.Color;

public class EmpleadoDAOImp implements EmpleadoDAO
{
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
    private static final Path archivoEmpleados;
        
    /*Constructor*/
    static 
    {
        archivoEmpleados = Paths.get("empleados.txt");
    }
    public EmpleadoDAOImp()
    {
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }
    /*Metodo para leer los empleados del archivo empleados.txt*/
    @Override
    public List<Empleado> leerEmpleados() throws ErrorAccediendoArchivoEmpleados
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
        catch(ParseException ex)
        {
            throw new ErrorAccediendoArchivoEmpleados(
                    "Error de formato en " + archivoEmpleados + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
        catch(IOException ex)
        {
            throw new ErrorAccediendoArchivoEmpleados(
                    "Error de lectura en " + archivoEmpleados + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
        return empleados;
    }
    /*Metodo para cambiar la contraseña del empelado en en programa y
    re escribir el archivo de empelados, con los nuevos dato.*/
    @Override
    public void CambiarContrasena(List<Empleado> empleados) throws ErrorAccediendoArchivoEmpleados
    {      
        try(BufferedWriter archivo = Files.newBufferedWriter(archivoEmpleados))
        {
            for (Empleado emp : empleados)
            {
                archivo.write(emp.toFile() + "\n\r");
            }
        }
        catch(IOException ex)
        {
            throw new ErrorAccediendoArchivoEmpleados(
                    "Error de escritura en " + archivoEmpleados + ":\n", ex, CodigoError.ERROR_ARCHIVO_EMPLEADOS);
        }
    }
}


