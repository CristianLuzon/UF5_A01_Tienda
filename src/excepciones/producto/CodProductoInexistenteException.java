package excepciones.producto;

import util.CodigoError;

public class CodProductoInexistenteException extends RuntimeException
{
    private int codigoError;
    
    public CodProductoInexistenteException( String mensaje)
    {
        super(mensaje);
    }
    public CodProductoInexistenteException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public CodProductoInexistenteException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
     public CodProductoInexistenteException( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
