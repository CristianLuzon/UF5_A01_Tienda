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
    float costeTotalCesta;
    private GestionarEmpleados gestionEmpleados;
    private GestionarProductos gestionProductos;
    
    public GestionTienda()
    {
        empleadoOnline = null;
        cesta = new ArrayList<>();
        costeTotalCesta = 0.0f;
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
        VistaTienda.bienvenidaEmpleado(empleadoOnline.getNombre());
        
        MostarMenuPrincipal();
    }
    /*Impirmir menu principal*/
    public void MostarMenuPrincipal()
    {
        boolean cerrarSesion = false;
        while(!cerrarSesion)
        {
            switch(VistaTienda.OpcionesMenuPincipal())
            {
                case HACER_PEDIDO:
                {
                    mostrarMenuHacerPedido();
                } break;
                case MODIFICAR_PRODUCTO:
                {
                    System.out.println("Mod Producto");
                } break;
                case CAMBIAR_PRODUCTO:
                {
                    System.out.println("cambiar producto");
                } break;
                case CAMBIAR_CONTRASENA:
                {
                    gestionEmpleados.cambiarContrasena();
                } break;
                case CERRAR_SESION:
                {
                    System.out.println("Cerrar sesion");
                    cerrarSesion = true;
                } break;
            }
        }
    }
    /*Metodos del pedido*/
    public void mostrarMenuHacerPedido()
    {
        boolean terminarPedido = false;
        while (!terminarPedido)
        {            
            switch(VistaTienda.OpcionesHacerPedido())
            {
                case AGREGAR_PRODUCTO:
                {
                    agregarProducto();
                } break;
                case COSTE_CESTA:
                {
                    VistaTienda.opcionCosteTotalCesta(costeTotalCesta);
                } break;
                case IMPRIMIR_FACTURA:
                {
                    VistaTienda.opcionImprimirFactura(cesta, 
                            costeTotalCesta, empleadoOnline.getNombre());
                } break;
                case TERMINAR_PEDIDO:
                {
                    vaciarCesta();
                    terminarPedido = true;
                } break;
            }
        }
    }
    /*Sub menus y metodos del MenuHacerPedido*/
    public void agregarProducto()
    {
        boolean seguirComprando = true;
        while(seguirComprando)
        {
            /*Identificamos el producto por su codigo, lo guardamo y agregamos a la cesta.
            Tambien sumamos su precio al coste total de la cesta.*/
            int codigoProducto = VistaTienda.OpcionAgregarProducto(gestionProductos);
            Producto nuevoProducto = gestionProductos.obtenerProductoCodigo(codigoProducto);
            cesta.add(nuevoProducto);
            costeTotalCesta += nuevoProducto.getPrecio();
            /*Se pregunta al empleado si quiere añadir más productos a la cesta o salir.*/
            seguirComprando = VistaTienda.preguntar(
                    "Desea seguir añadiendo productos a la cesta? (\"Si\" o \"No\")");   
        }
    }
    public void vaciarCesta()
    {
        cesta.removeAll(cesta);
        costeTotalCesta = 0.0f;
    }
    
    public void mostrarMenuCambiarContrasena()
    {
        gestionEmpleados.cambiarContrasena();
    }
    
    /*Metodo para comprobar la existencia de un producto.*/
    public boolean productoExiste(int codigo)
    {
        return gestionProductos.productoExiste(codigo);
    }
}
