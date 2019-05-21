package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import tienda.vista.VistaTienda;
import util.Color;

public class ConexionBBDD
{
    private static final String PLACE;
    private static final String HOST;
    private static final String PORT;
    private static final String DATA_BASE_NAME;
    private static final String USER;
    private static final String PASSWORD;
    private static final String UTL_PARAMETRES;
    private static final String URL;
    
    static
    {
        PLACE = "jdbc:mysql://";
        HOST = "localhost";
        PORT = "3306";
        DATA_BASE_NAME = "tienda";
        USER = "root";
        PASSWORD = "";
        UTL_PARAMETRES = "?useUnicode=true&serverTimezone=UTC";
        URL = String.format("%s%s:%s/%s%s", 
        PLACE, HOST, PORT, DATA_BASE_NAME, UTL_PARAMETRES);
        //cargarDriver();
    }
    
    public static Connection conectar()
    {
        //Conexion con la base de datos
        Connection conexion = null;
        try
        {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException ex)
        {
            VistaTienda.mostarMensaje("La conexión no se ha podido establecer con: " + 
                DATA_BASE_NAME, Color.ERROR);
            //ex.printStackTrace();
            System.exit(1);
        }      
        return conexion;
    }
    
    private static void cargarDriver()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            VistaTienda.mostarMensaje("No se ha podido cargar el diver JDBC MySQL", Color.ERROR);
            System.exit(1);
        }
    }
}
