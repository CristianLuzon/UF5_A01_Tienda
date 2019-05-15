package app.main;

import tienda.control.GestionTienda;

public class Main
{
    public static void main(String[] args)
    {
        Main app = new Main();
        app.run();
    }
    
    public void run()
    {
        GestionTienda tienda = new GestionTienda();
        tienda.iniciar();
    }
}
