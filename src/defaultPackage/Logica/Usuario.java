
package defaultPackage.Logica;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amir
 */
public class Usuario {
    
    private String nombre;
    
    private String apellido;
    
    private String idUsuario;
    
    // El MD5 hash del pin del usuario
    private byte pinHash[];
    
    private List<Cuenta> cuentas;
    
    //Contructor
    public Usuario(String nombre, String apellido, String pin, Banco elBanco){
        this.nombre = nombre;
        this.apellido = apellido;
        this.idUsuario = elBanco.obtenerIDUsuario();
        
        // Guardar el MD5 hash del pin
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Crear lista de cuentas
        this.cuentas = new ArrayList<>();
        
        System.out.printf("Usuario %s %s creado con ID %s. \n", nombre, apellido, 
                idUsuario);
    }
    
    
    //Agregar cuenta a la lista
    public void agregarCuenta(Cuenta laCuenta){
        this.cuentas.add(laCuenta);
    }

    String getIdUsuario(){
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }
     
    
    // Validar si el Pin ingresado corresponde al pin del usuario
    public boolean validarPin(String pin){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte pinIngresado[];
            pinIngresado = md.digest(pin.getBytes());
            return MessageDigest.isEqual(this.pinHash, pinIngresado);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    // Texto resumen de las cuentas
    void imprimirResumenCuentas() {
        
        System.out.printf("\nResumen cuentas de %s \n",this.nombre);
        for(int i = 0; i<this.cuentas.size();i++){
            System.out.printf("%d. %s\n", i+1, this.cuentas.get(i).resumenCuenta());
        }
        
        System.out.println();
    }
    
    int numeroCuentas(){
        return this.cuentas.size();
    }

    void imprimirHistorialMovimientosCuenta(int indiceCuenta) {
        
        this.cuentas.get(indiceCuenta).imprimirHistorialMovimientos();
    }

    double obtenerBalanceCuenta(int indiceCuenta) {
        return this.cuentas.get(indiceCuenta).obtenerBalance();
    }

    String getIdCuentas(int icuentaOrigen) {
        return this.cuentas.get(icuentaOrigen).getIdCuenta();
    }

    
    void agregarTransaccion(int idCuenta, double cantidad, String info) {
        this.cuentas.get(idCuenta).realizarTransaccion(cantidad,info);
    }
}
