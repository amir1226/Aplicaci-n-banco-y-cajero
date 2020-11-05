package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Amir
 */
public class Banco {
    
    private String nombreBanco;
    
    private List <Usuario> usuarios;
    
    public List <Cuenta> cuentas;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        this.usuarios = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }
    
    //Generar el ID para un usuario
    public String obtenerIDUsuario(){
        Random rand = new Random();
        String id = "";
        boolean unico;
        int n = 6;
        
        //Generar un IDUsuario que no se repita
        do{
            for (int i=0; i<n; i++ ){
                id += ((Integer)rand.nextInt(10)).toString();
            }
            
            unico = false;
            for(Usuario us:this.usuarios){
                if(id.compareTo(us.getIdUsuario()) == 0){
                    unico =true;
                    break;
                }
            }
                
        }while(unico);
                
        return id;
    }
    
     //Generar el ID para una cuenta 
    public String obtenerIDCuenta(){
        Random rand = new Random();
        String id = "";
        boolean unico;
        int n = 12;
        
        //Generar un IDCuenta que no se repita
        do{
            for (int i=0; i<n; i++ ){
                id += ((Integer)rand.nextInt(10)).toString();
            }
            
            unico = false;
            for(Cuenta cuent:this.cuentas){
                if(id.compareTo(cuent.getIdCuenta()) == 0){
                    unico =true;
                    break;
                }
            }
                
        }while(unico);
                
        return id;
    }
    
    //Agregar cuenta a la lista
    public void agregarCuenta(Cuenta laCuenta){
        this.cuentas.add(laCuenta);
    }
    
    //Agregar usuario en el banco y por defecto crea una cuenta de ahorros.
    public Usuario agregarUsuario(String nombre, String apellido, String pin){
        
        Usuario nuevoUsuario = new Usuario( nombre, apellido, pin, this);
        this.usuarios.add(nuevoUsuario);
    
        // Por defecto se crea una cuenta de ahorros.
        Cuenta nuevaCuenta = new Cuenta("Ahorros", nuevoUsuario, this);
        agregarCuenta(nuevaCuenta);
        
        return nuevoUsuario;
    }
    
    //Validar información de ingreso.
    public Usuario login(String idUsuario, String pin){
        for(Usuario u : this.usuarios){
            if(u.getIdUsuario().compareTo(idUsuario) == 0 && u.validarPin(pin)){
                return u;
            }
        }
        return null;
    }
}
