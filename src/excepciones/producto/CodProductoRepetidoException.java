package excepciones.producto;

import util.CodigoError;

public class CodProductoRepetidoException extends RuntimeException
{
    private int codigoError;
    
    public CodProductoRepetidoException( String mensaje)
    {
        super(mensaje);
    }
    public CodProductoRepetidoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public CodProductoRepetidoException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
