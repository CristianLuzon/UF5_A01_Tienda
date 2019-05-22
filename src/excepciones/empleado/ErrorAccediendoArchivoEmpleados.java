package excepciones.empleado;

import util.CodigoError;

public class ErrorAccediendoArchivoEmpleados extends RuntimeException
{
    private int codigoError;
    
    public ErrorAccediendoArchivoEmpleados( String mensaje)
    {
        super(mensaje);
    }
    public ErrorAccediendoArchivoEmpleados(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ErrorAccediendoArchivoEmpleados( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    public ErrorAccediendoArchivoEmpleados( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
