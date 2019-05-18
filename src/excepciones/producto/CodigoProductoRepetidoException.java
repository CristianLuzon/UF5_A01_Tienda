package excepciones.producto;

import util.CodigoError;

public class CodigoProductoRepetidoException extends RuntimeException
{
    private int codigoError;
    
    public CodigoProductoRepetidoException( String mensaje)
    {
        super(mensaje);
    }
    public CodigoProductoRepetidoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public CodigoProductoRepetidoException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
