package producto.vista;

import tienda.vista.VistaTienda;

public class VistaGestionarProducto
{
    public static void modificarCodigoProducto()
    {
        VistaTienda.mostarMensaje("Para cambiar el codigo del producto debes introducir un codigo que no este ya en uso, ni sea el mismo que el actual.\n");
    }
    public static void modificarNombreProducto()
    {
        VistaTienda.mostarMensaje("Para cambiar el nombre del producto debes introducir un nombre que no este ya en uso, ni sea el mismo que el actual.\n");
    }
    public static void modificarPrecioProducto()
    {
        VistaTienda.mostarMensaje("Procura introducir bien la información del dato decimal actual.\n");
    }
}
