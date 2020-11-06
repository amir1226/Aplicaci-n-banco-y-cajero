
package defaultPackage;

import java.util.Scanner;

/**
 *
 * @author Amir
 */
public class Cajero {
    
    public static Usuario menuPrincipal(Banco elBanco, Scanner sc) {
        String idUsuario;
        String pin;
        Usuario autorizaUsuario;
        byte intentos = 0;
        byte maxIntentos = 6;
        
        System.out.printf("\nBienvenido a %s\n\n", elBanco.getNombreBanco());
        
        /* Menú del banco, vuelve a solicitar información en caso que la 
        información sea incorrecta. */
        do {    
            System.out.print("Ingrese el ID del usuario: ");
            idUsuario = sc.nextLine();
            System.out.print("Ingrese la clave: ");
            pin = sc.nextLine();
            
            autorizaUsuario = elBanco.login(idUsuario, pin);
            
            if (autorizaUsuario == null) {
                System.out.println("Información incorrecta. Vuelva a intentar "
                        + "por favor.");
                intentos +=1;
            }
            
        } while (autorizaUsuario == null && intentos < maxIntentos );
        
        return autorizaUsuario;
    }

    private static void imprimirMenuUsuario(Usuario elUsuario, Scanner sc) {
        
      elUsuario.imprimirResumenCuentas();
      
      byte opcion = 0;
        
        do{
        System.out.printf("Bienvenido %s, tienes las siguientes opciones: \n", 
                elUsuario.getNombre());
        System.out.println(" 1. Mostrar historial de transacciones");
        System.out.println(" 2. Retirar dinero");
        System.out.println(" 3. Depositar dinero");
        System.out.println(" 4. Trasnferencia");
        System.out.println(" 5. Salir");
        System.out.println();
        System.out.print("Ingresa la opción que deseas: ");
        opcion = sc.nextByte();
        
        if (opcion < 1 || opcion >5){
            System.out.print("\nOpción incorrecta. Por favor selecciona un valor entre 1 y 5. ");
            }
        }while(opcion < 1 || opcion >5);
        
        switch(opcion){
            case 1:
                Cajero.mostrarHistorial(elUsuario,sc);
                break;
            case 2:
                Cajero.retitarFondos(elUsuario,sc);
                break;
            case 3:
                Cajero.ingresarFondos(elUsuario,sc);
                break;        
            case 4:
                Cajero.transferirFondos(elUsuario,sc);
                break;  
        }
        
        if (opcion != 5){
            Cajero.imprimirMenuUsuario(elUsuario,sc);
        }
    }   
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
                
        Banco primerBanco = new Banco("BBVA");
        
        Usuario primerUsuario = primerBanco.agregarUsuario("Pepito", "Perez", "1234");
        
        Cuenta primeraCuenta = new Cuenta("Corriente", primerUsuario, primerBanco);
        
        Usuario elUsuario;
        
        while (true){
            elUsuario = Cajero.menuPrincipal(primerBanco, sc);
            
            Cajero.imprimirMenuUsuario(elUsuario, sc);
        }
        
    }

    private static void mostrarHistorial(Usuario elUsuario, Scanner sc) {
        
        int indiceCuenta = -1;
        
        while(indiceCuenta <0 || indiceCuenta > elUsuario.numeroCuentas()){
            System.out.printf("Ingrese el índice (1-%d) de la cuenta" +
                    "de la transacción que quiere ver:", elUsuario.numeroCuentas());
            
            indiceCuenta = sc.nextInt()-1;
            
            if(indiceCuenta <0 || indiceCuenta > elUsuario.numeroCuentas()){
                System.out.println("Indice fuera del rango. Intenta de nuevo.");
            }
        }
        
        elUsuario.imprimirHistorialMovimientosCuenta(indiceCuenta);
        
    }

    private static void retitarFondos(Usuario elUsuario, Scanner sc) {
        
        int
    }

}

