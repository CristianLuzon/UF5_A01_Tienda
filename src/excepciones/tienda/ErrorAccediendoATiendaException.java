package excepciones.tienda;

import util.CodigoError;

public class ErrorAccediendoATiendaException extends RuntimeException
{
    private int codigoError;
    
    public ErrorAccediendoATiendaException( String mensaje)
    {
        super(mensaje);
    }
    public ErrorAccediendoATiendaException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ErrorAccediendoATiendaException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    public ErrorAccediendoATiendaException( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
