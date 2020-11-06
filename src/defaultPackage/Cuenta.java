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
        System.out.println("Cuenta " + this.nombreCuenta + " agregada al usuario " 
                + duenio.getIdUsuario());
        elBanco.agregarCuenta(this);
        System.out.println("Cuenta " + this.nombreCuenta + " agregada al banco " 
                + elBanco.getNombreBanco());
    }

    String getIdCuenta() {
        return idCuenta;
    }

    public double obtenerBalance() {
        double balance = 0;
        for (Movimiento mov: this.movimientos){
            balance += mov.getCantidad();
        }
        
        return balance;
    }

    
    String resumenCuenta() {
       
        double balance = this.obtenerBalance();
        
        if (balance >= 0){
            return String.format("%s : $.03f : $s", this.idCuenta, balance, 
                    this.nombreCuenta);
        } else{
            return String.format("%s : $(.03f) : $s", this.idCuenta, balance, 
                    this.nombreCuenta);
        }
    }

    void imprimirHistorialMovimientos() {
        System.out.printf("\nHistorial de transacciones para la cuenta %s\n",
                this.idCuenta);
        
        for (int tran = this.movimientos.size()-1; tran >= 0; tran--){
            System.out.printf(this.movimientos.get(tran).getResumen());
        }
        System.out.println();
    }

    
    
}
