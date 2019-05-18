package excepciones.empleado;

import util.CodigoError;

public class CodEmpleErrorException extends RuntimeException
{
    private int codigoError;
    
    public CodEmpleErrorException( String mensaje)
    {
        super(mensaje);
    }
    public CodEmpleErrorException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public CodEmpleErrorException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
