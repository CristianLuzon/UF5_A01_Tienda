package excepciones.empleado;

import util.CodigoError;

public class ContraEmpleErrorException extends RuntimeException
{
    private int codigoError;
    
    public ContraEmpleErrorException( String mensaje)
    {
        super(mensaje);
    }
    public ContraEmpleErrorException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ContraEmpleErrorException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
