package tienda.control;

import empleado.control.GestionarEmpleados;
import empleado.dominio.Empleado;
import java.util.*;
import producto.control.GestionarProductos;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;

public class GestionTienda
{
    private Empleado empleadoOnline;
    private List<Producto> cesta;
    private GestionarEmpleados gestionEmpleados;
    private GestionarProductos gestionProductos;
    
    public GestionTienda()
    {
        empleadoOnline = null;
        cesta = new ArrayList<>();
        gestionEmpleados = new GestionarEmpleados();
        gestionProductos = new GestionarProductos();
    }
    
    public void iniciar()
    {
        boolean loginCorrecto = false;
        
        while(!loginCorrecto)
        {
            try
            {
                gestionEmpleados.login();
                loginCorrecto = true;
            }
            catch (Exception ex)
            {
                System.out.println("Login incorrecto");
                /*Crear los catch para las diferentes excepciones personalizas.
                Revisar la tarea de excepciones personalizadas y craelas en el package de "excepciones"*/
            }
        }
        empleadoOnline = gestionEmpleados.getEmpleadoOnline();
        System.out.println(String.format("Bienvenido %s", 
                empleadoOnline.getNombre()));
        
        //Impirmir menu principal
        MostarMenuPrincipal();
    }
    
    public void MostarMenuPrincipal()
    {
        boolean cerrarSesion = false;
        while(!cerrarSesion)
        {
            switch(VistaTienda.OpcionesMenuPincipal())
            {
                case HACER_PEDIDO:
                {
                    MostarMenuHacerPedido();
                }break;
                case MODIFICAR_PRODUCTO:
                {
                    System.out.println("Mod Producto");
                }break;
                case CAMBIAR_PRODUCTO:
                {
                    System.out.println("cambiar producto");
                }break;
                case CAMBIAR_CONTRASENA:
                {
                    System.out.println("Cambiar pass");
                }break;
                case CERRAR_SESION:
                {
                    System.out.println("Cerrar sesion");
                    cerrarSesion = true;
                }break;
            }
        }
    }
    public void MostarMenuHacerPedido()
    {
        boolean seguirComprando = true;
        while(seguirComprando)
        {
            int codigoProducto = VistaTienda.OpcionesHacerPedido(gestionProductos);
            cesta.add(gestionProductos.obtenerProductoCodigo(codigoProducto));
            
            //IF quieres añadir más productos a la cesta o terminar?
            seguirComprando = VistaTienda.preguntar(
                    "Desea seguir añadiendo productos a la cesta?");   
        }
    }
    

    
    public boolean productoExiste(int codigo)
    {
        return gestionProductos.productoExiste(codigo);
    }
}
