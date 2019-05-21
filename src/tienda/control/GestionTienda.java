package tienda.control;

import empleado.control.GestionarEmpleados;
import empleado.dominio.Empleado;
import excepciones.empleado.*;
import excepciones.producto.CodProductoInexistenteException;
import excepciones.producto.CodProductoRepetidoException;
import excepciones.tienda.ErrorAccediendoATiendaException;
import java.util.*;
import producto.control.GestionarProductos;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;
import util.Color;

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
    
    /*Metodo del inicio de sesión*/
    public void iniciar()
    {
        while(VistaTienda.cerrarPrograma())
        {
            boolean loginCorrecto = false;

            while(!loginCorrecto)
            {
                try
                {
                    gestionEmpleados.login();
                    loginCorrecto = true;
                }
                catch (CodEmpleErrorException ex)
                {
                    VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
                    VistaTienda.esperarEnter();
                }
                catch (ContraEmpleErrorException ex)
                {
                    VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
                    VistaTienda.esperarEnter();
                }
                catch(ErrorAccediendoATiendaException ex)
                {
                    VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
                    VistaTienda.esperarEnter();
                }
                catch (Exception ex)
                {
                    VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
                    VistaTienda.esperarEnter();
                }
            }
            empleadoOnline = gestionEmpleados.getEmpleadoOnline();
            VistaTienda.bienvenidaEmpleado(empleadoOnline.getNombre());
            MostarMenuPrincipal();
        }
    }
    /*Metodo del menu principal*/
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
                    mostrarMenuModificarProducto();
                } break;
                case CAMBIAR_CONTRASENA:
                {
                    gestionEmpleados.cambiarContrasena();
                } break;
                case CERRAR_SESION:
                {
                    VistaTienda.mostarMensaje("Cerrar sesion.\n");
                    cerrarSesion = true;
                } break;
            }
        }
    }
    /*Metodos del menú hacer pedido*/
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
            Producto nuevoProducto = gestionProductos.obtenerProductoPorCodigo(codigoProducto);
            cesta.add(nuevoProducto);
            costeTotalCesta += nuevoProducto.getPrecio();
            /*Se pregunta al empleado si quiere añadir más productos a la cesta o salir.*/
            seguirComprando = VistaTienda.preguntar(
                    "Desea seguir añadiendo productos a la cesta? (\"Si\" o \"No\"): ");   
        }
    }
    /*metodo para vaciar la lista cesta, que contiene los producto.*/
    public void vaciarCesta()
    {
        cesta.removeAll(cesta);
        costeTotalCesta = 0.0f;
    }
    
    /*Metodo para cambiar la contraseña*/
    public void mostrarMenuCambiarContrasena()
    {
        gestionEmpleados.cambiarContrasena();
    }
    
    /*Metodo para comprobar la existencia de un producto por su codigo.*/
    public boolean productoExiste(int codigo)
    {
        return gestionProductos.codigoProductoExiste(codigo);
    }
    /*Metodos de menú de la modificación del producto*/
    public void mostrarMenuModificarProducto()
    {
        int productoEleguido = -1;
        boolean productoExiste = false;
        /*Preguntamos por un codigo de producto hasta que se de uno existente.*/
        while(!productoExiste)
        {
            try
            {
                productoEleguido = VistaTienda.elegirCodigoProductoModificar(gestionProductos);
                
                if (productoExiste(productoEleguido))
                    productoExiste = true;
            } 
            catch (CodProductoInexistenteException ex)
            {
                VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
            }
        }
        /*Preguntamos lo que se va a modificar del producto.*/
        boolean terminiarModifiacion = false;
        while (!terminiarModifiacion)
        {            
            switch(VistaTienda.OpcionesModificarProducto())
            {
                case MODIFICAR_CODIGO:
                {
                    try
                    {
                        productoEleguido = gestionProductos.modificarCodigo(productoEleguido);
                    }
                    catch (CodProductoRepetidoException ex)
                    {
                        VistaTienda.mostarMensaje(ex.getMessage(), Color.ERROR);
                        VistaTienda.esperarEnter();
                    }
                } break;
                case MODIFICAR_NOMBRE:
                {
                    gestionProductos.modificarNombre(productoEleguido);
                } break;
                case MODIFICAR_PRECIO:
                {
                    gestionProductos.modificarPrecio(productoEleguido);
                } break;
                case TERMINAR_MODIFICACION:
                {
                    terminiarModifiacion = true;
                } break;
            }
        }
    }
}
