package excepciones.tienda;

import util.CodigoError;

public class RangoIncorrectoException extends RuntimeException
{
    private int codigoError;
    
    public RangoIncorrectoException( String mensaje)
    {
        super(mensaje);
    }
    public RangoIncorrectoException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public RangoIncorrectoException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    public RangoIncorrectoException( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
