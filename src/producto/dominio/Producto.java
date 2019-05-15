package producto.dominio;

public class Producto
{
    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;

    /*Constructores*/
    public Producto()
    {
        this(0, "", "", 0.0f);
    }
    public Producto(int codigo, String nombre, String descripcion, double precio)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    /*Geters & Setters*/
    public int getCodigo()
    {
        return codigo;
    }
    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    public double getPrecio()
    {
        return precio;
    }
    public void setPrecio(float precio)
    {
        this.precio = precio;
    }
    
    @Override
    public String toString()
    {
        return String.format("%d\t%s\t%.2f", 
            getCodigo(), getNombre(), getPrecio());
    }
    
    public String paraFactura()
    {
        return String.format("%d %s %.2f %s", 
            getCodigo(), getNombre(), getPrecio(), getDescripcion());
    }
}
