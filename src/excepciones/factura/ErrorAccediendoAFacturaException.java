package excepciones.factura;

import excepciones.producto.*;
import util.CodigoError;

public class ErrorAccediendoAFacturaException extends RuntimeException
{
    private int codigoError;
    
    public ErrorAccediendoAFacturaException( String mensaje)
    {
        super(mensaje);
    }
    public ErrorAccediendoAFacturaException(String mensaje, Throwable causa)
    {
        super(mensaje, causa);
    }
    public ErrorAccediendoAFacturaException( String message, Throwable cause, CodigoError codigoError)
    {
        super(message, cause);
        this.codigoError = codigoError.getCodigoError();
    }
    public ErrorAccediendoAFacturaException( String message, CodigoError codigoError)
    {
        super(message);
        this.codigoError = codigoError.getCodigoError();
    }

    public int getCodigoError()
    {
        return codigoError;
    }
}
