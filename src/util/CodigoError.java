package util;

public enum CodigoError
{
    /*Errores de Empleado*/
    CODIGO_EMPLE_INCORRECTO(1),
    CONTRA_EMPLE_INCORRECTO(2),
    CONTRA_EMPLE_IDENTICA(3),
    /*Errores de Producto*/
    CODIGO_PRODUCTO_REPETIDO(4),
    CODIGO_PRODUCTO_INEXISTENTE(5),
    NOMBRE_PRODUCTO_REPETIDO(6),
    /*Errores Generales*/
    VALOR_INCORRECTO(7),
    PRECIO_INCOHERENTE(8);
    
    private int codigo;
    
    private CodigoError(int codigo)
    {
        this.codigo = codigo;
    }
    
    public int getCodigoError()
    {
        return codigo;
    }
}
