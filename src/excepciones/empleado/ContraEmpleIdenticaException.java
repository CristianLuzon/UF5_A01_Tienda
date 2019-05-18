package excepciones.empleado;

import util.CodigoError;

public class ContraEmpleIdenticaException extends RuntimeException
{
    private int codigoError;
    
    public ContraEmpleIdenticaException( String mensaje)
    {
        super(mensaje);
    }
    public ContraEmpleIdenticaException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ContraEmpleIdenticaException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
