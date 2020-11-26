/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultPackage.Logica;

import java.util.Date;

/**
 *
 * @author Amir
 */
public class Movimiento {
    
    private double cantidad;
    
    private Date marcaTiempo;
    
    private String info;
    
    private Cuenta cuentaMov;
    
    
    public Movimiento(double cantidad, Cuenta laCuenta){
        this.cantidad = cantidad;
        this.marcaTiempo = new Date();
        this.info = "";
        
    }
    
    public Movimiento(double cantidad, String info, Cuenta laCuenta){
        this(cantidad,laCuenta);
        this.info = info;
        
    }
    
    double getCantidad(){
        return cantidad;
    }
    
    String getResumenMovimiento(){
        
        if(this.cantidad >= 0){
            return String.format("%s : $%.03f : %s \n", this.marcaTiempo.toString(),
                    this.cantidad, this.info);
        }else{
            return String.format("%s : $(%.03f) : %s \n", this.marcaTiempo.toString(),
                    this.cantidad, this.info);
        }
    }
}
