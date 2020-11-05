
package defaultPackage;

/**
 *
 * @author Amir
 */
public class Cajero {
    
    public static void main(String args[]){
        Banco bbva = new Banco("BBVA");
        System.out.println(bbva.agregarUsuario("Amir", "Sadour", "1234"));
    }
}
