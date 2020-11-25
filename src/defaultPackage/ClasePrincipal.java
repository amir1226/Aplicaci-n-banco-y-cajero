
package defaultPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Amir
 */
public class ClasePrincipal {
       
       public ArrayList<Banco> listaBancos;
       
       public static void main(String args[]){
           
        VentanaPrincipal ventana = new VentanaPrincipal();
           
        Scanner sc = new Scanner(System.in);
                
        Banco primerBanco = new Banco("El Honesto");
        
        Usuario primerUsuario = primerBanco.agregarUsuario("Pepito", "Perez", "1234");
        
        Cuenta primeraCuenta = new Cuenta("Corriente", primerUsuario, primerBanco);
        
        Usuario elUsuario;
        
        while (true){
            elUsuario = Cajero.menuPrincipal(primerBanco, sc);
            
            Cajero.imprimirMenuUsuario(elUsuario, sc);
        }
        
    }

    public ClasePrincipal() {
        this.listaBancos = new ArrayList<>();
    }
}
