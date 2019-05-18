package excepciones.producto;

import util.CodigoError;

public class NombreProductoRepetidoException extends RuntimeException 
{
    private int codigoError;
    
    public NombreProductoRepetidoException( String mensaje)
    {
        super(mensaje);
    }
    public NombreProductoRepetidoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public NombreProductoRepetidoException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    
    public int getCodigoError()
    {
        return codigoError;
    }
}
