package util;

public enum CodigoError
{
    /*Errores de Empleado*/
    CODIGO_EMPLE_INCORRECTO(1),
    CONTRA_EMPLE_INCORRECTO(2),
    CONTRA_EMPLE_IDENTICA(3),
    /*Errores de Producto*/
    CODIGO_PRODUCTO_REPETIDO(4),
    NOMBRE_PRODUCTO_REPETIDO(5),
    /*Errores Generales*/
    VALOR_INCORRECTO(6),
    PRECIO_INCOHERENTE(7);
    
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
