
package defaultPackage;
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
        
        System.out.printf("Usuario %s %s creado con ID %s. \n ", nombre, apellido, idUsuario);
    }
    
    //Agregar cuenta a la lista
    public void agregarCuenta(Cuenta laCuenta){
        this.cuentas.add(laCuenta);
    }

    String getIdUsuario(){
        return idUsuario;
    }
    
}
