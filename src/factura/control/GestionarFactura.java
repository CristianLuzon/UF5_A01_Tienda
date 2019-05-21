package factura.control;

import excepciones.factura.ErrorAccediendoAFacturaException;
import java.io.*;
import java.nio.*;
import java.nio.file.*;
import util.CodigoError;

public class GestionarFactura
{
    public static final Path archivoFactura = Paths.get("factura.txt");
    /*Crear archivo con la string de la factura.*/
    public static boolean crearArchivoFactura(String contenido) throws ErrorAccediendoAFacturaException
    {      
        try(BufferedWriter bWriter = Files.newBufferedWriter(archivoFactura, StandardOpenOption.CREATE))
        {
            bWriter.write(contenido);
        }
        catch (IOException ex)
        {
            throw new ErrorAccediendoAFacturaException(
                    "Error al crear el archivo factura.\n", ex, CodigoError.ERROR_CREANDO_FACTURA);
        }
        return true;
    }
}
