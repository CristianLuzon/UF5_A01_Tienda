package empleado.dao;

import conexion.ConexionBBDD;
import empleado.dominio.Empleado;
import empleado.dao.EmpleadoDAO;
import excepciones.tienda.ErrorAccediendoATiendaException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import tienda.vista.VistaTienda;
import util.CodigoError;
import util.Color;

public class EmpleadoDAOImp implements EmpleadoDAO
{
    private NumberFormat formatoNumero;
    private Number numero;
    private String lineaDato;
        
    /*Constructor*/
    public EmpleadoDAOImp()
    {
        formatoNumero = NumberFormat.getInstance(Locale.FRANCE);
        numero = 0;
        lineaDato = "";
    }
    /*Metodo para leer los empleados de la base de datos*/
    @Override
    public List<Empleado> leerEmpleados() throws ErrorAccediendoATiendaException
    {
        List<Empleado> empleados = new ArrayList<>();
        String query = "select * from empleados";
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);)
        {            
            while (resultado.next())
            {                
                int codigo = resultado.getInt("e_codigo");
                String nombre = resultado.getString("e_nombre");
                String apellido = resultado.getString("e_apellidos");
                String contrasena = resultado.getString("e_password");
                
                empleados.add(new Empleado(codigo, nombre, apellido, contrasena));
            }
        }
        catch (SQLException ex)
        {
            throw new ErrorAccediendoATiendaException(
                    "La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
        return empleados;
    }
    @Override
    public boolean actualizarEmpleados(List<Empleado> empleados)
    {
        Empleado empleado = null;
        String query = "select * from empleados where e_codigo = ";
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);)
        {   
            resultado.next();
            int codigo = resultado.getInt("e_codigo");
            String nombre = resultado.getString("e_nombre");
            String apellido = resultado.getString("e_apellidos");
            String contrasena = resultado.getString("e_password");
            empleado = new Empleado(codigo, nombre, apellido, contrasena);
        }
        catch(SQLException ex)
        {
            throw new ErrorAccediendoATiendaException(
                    "La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
        return true;
    }
    @Override
    public Empleado obtenerEmpleado(int tCodigo) throws ErrorAccediendoATiendaException
    {
        Empleado empleado = null;
        String query = "select * from empleados where e_codigo = " + tCodigo;
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(query);)
        {   
            resultado.next();
            String nombre = resultado.getString("e_nombre");
            String apellido = resultado.getString("e_apellidos");
            String contrasena = resultado.getString("e_password");
            empleado = new Empleado(tCodigo, nombre, apellido, contrasena);
        }
        catch (SQLException ex)
        {
            throw new ErrorAccediendoATiendaException("La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
        return empleado;
    }
    /*Metodo para cambiar la contraseña del empelado en en programa y en la BBDDD.*/
    @Override
    public void CambiarContrasena(Empleado empleado, String nuevaContrasena)
    {
        String query = String.format(
                "update empleados set e_password = '%s' where e_nombre = '%s';",
                nuevaContrasena, empleado.getNombre());
        
        try(Connection conexion = ConexionBBDD.conectar();
            Statement sentencia = conexion.createStatement();)
        {
            sentencia.executeUpdate(query);
            empleado.setContrasena(nuevaContrasena);
        }
        catch (SQLException ex)
        {
            throw new ErrorAccediendoATiendaException(
                    "La petición a fallado... ", ex, CodigoError.ERROR_DE_ACCESO_A_BBDD);
        }
    }
}