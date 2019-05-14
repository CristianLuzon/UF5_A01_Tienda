package empleado.dominio;

public class Empleado
{
    private int codigo;
    private String nombre;
    private String apellidos;
    private String contrasena;
    
    /*Constructores*/
   
    public Empleado()
    {
        this(0, "", "", "");
    }
    public Empleado(int codigo, String nombre, String apellidos, String contrasena)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
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
    public String getApellidos()
    {
        return apellidos;
    }
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }
    public String getContrasena()
    {
        return contrasena;
    }
    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }
    
    
    
    @Override
    public String toString()
    {
        return String.format("%d %s %s %s", 
            getCodigo(), getNombre(), getApellidos(), getContrasena());
    }
    
}
