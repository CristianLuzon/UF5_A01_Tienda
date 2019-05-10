package base;

import dominio.Empleado;
import dominio.Producto;
import java.util.ArrayList;

import java.util.List;

public class Main
{
    
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    
    public void run()
    {
       cargarProductos();        
    }
    
    public void cargarEmpleados()
    {
        
    }
    public void cargarProductos()
    {
        List<Producto> productos = new Producto().leerEmpleados();
        for (Producto product : productos)
            System.out.println(product);
        
        System.out.println("\n\n");
        
        List<Empleado> empleados = new Empleado().leerEmpleados();
        for (Empleado emple : empleados)
            System.out.println(emple);
    }
}
