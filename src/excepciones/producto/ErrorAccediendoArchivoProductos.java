package excepciones.producto;

import excepciones.empleado.*;
import util.CodigoError;

public class ErrorAccediendoArchivoProductos extends RuntimeException
{
    private int codigoError;
    
    public ErrorAccediendoArchivoProductos( String mensaje)
    {
        super(mensaje);
    }
    public ErrorAccediendoArchivoProductos(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ErrorAccediendoArchivoProductos( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    public ErrorAccediendoArchivoProductos( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
