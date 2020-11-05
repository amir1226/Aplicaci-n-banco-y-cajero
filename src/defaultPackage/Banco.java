package defaultPackage;

import java.util.List;

/**
 *
 * @author Amir
 */
public class Banco {
    
    private String nombreBanco;
    
    private List <Usuario> usuarios;
    
    private List <Cuenta> cuentas;
    
    public String obtenerIDUsuario(){
        String ID;
        return ID;
    }
    
    public String obtenerIDCuenta(){
        return "a";
    }
    
    //Agregar cuenta a la lista
    public void agregarCuenta(Cuenta laCuenta){
        this.cuentas.add(laCuenta);
    }
}
