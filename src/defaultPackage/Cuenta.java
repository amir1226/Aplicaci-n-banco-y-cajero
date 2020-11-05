/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultPackage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amir
 */
public class Cuenta {
    
    private String nombreCuenta;
    
    private double saldo;
    
    private String idCuenta;
    
    private Usuario duenio;
    
    private List<Movimiento> movimientos;

    public Cuenta(String nombreCuenta, Usuario duenio, Banco elBanco) {
        this.nombreCuenta = nombreCuenta;
        this.duenio = duenio;
        this.saldo = 0;
        this.idCuenta = elBanco.obtenerIDCuenta();
        this.movimientos = new ArrayList<>();
        
        //Agregar cuenta a las listas de usuario y banco
        
        duenio.agregarCuenta(this);
        elBanco.agregarCuenta(this);
    }

    String getIdCuenta() {
        return idCuenta;
    }

    
    
}
