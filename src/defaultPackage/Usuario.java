
package defaultPackage;
import java.util.ArrayList;

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
    
    private ArrayList<Cuenta> cuentas;
}
