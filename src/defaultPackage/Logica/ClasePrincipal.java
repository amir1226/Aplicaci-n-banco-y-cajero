
package defaultPackage.Logica;

import defaultPackage.Graficas.VentanaPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Amir
 */
public class ClasePrincipal {
       
       public static void main(String args[]){
           
        ArrayList<Banco> listaBancos = new ArrayList<>();
           
        VentanaPrincipal ventana = new VentanaPrincipal();
           
        Scanner sc = new Scanner(System.in);
                
        Banco primerBanco = new Banco("El Honesto");
        
        listaBancos.add(primerBanco);
        
        System.out.println(Arrays.toString(listaBancos.toArray()));
        
        Usuario primerUsuario = primerBanco.agregarUsuario("Pepito", "Perez", "1234");
        
        Cuenta primeraCuenta = new Cuenta("Corriente", primerUsuario, primerBanco);
        
        Usuario elUsuario;
        
        while (true){
            elUsuario = Cajero.menuPrincipal(primerBanco, sc);
            
            Cajero.imprimirMenuUsuario(elUsuario, sc);
        }
        
    }
}