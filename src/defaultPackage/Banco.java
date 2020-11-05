package defaultPackage;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Amir
 */
public class Banco {
    
    private String nombreBanco;
    
    private List <Usuario> usuarios;
    
    private List <Cuenta> cuentas;
    
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
}
