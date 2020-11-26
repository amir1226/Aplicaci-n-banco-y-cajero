
package defaultPackage.Logica;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Amir
 */
public class Cajero {
    
    /**
     *
     * @param elBanco
     * @param sc
     * @return
     */
    
    ArrayList<Banco> listaBancos = new ArrayList<>();
    
    public static Usuario menuPrincipal(Banco elBanco, Scanner sc) {
        String idUsuario;
        String pin;
        Usuario autorizaUsuario;
        byte intentos = 0;
        
        System.out.printf("\nBienvenido a %s\n\n", elBanco.getNombreBanco());
        
        /* Menú del banco, vuelve a solicitar información en caso que la 
        información sea incorrecta. */
        do {    
            System.out.print("Ingresa el ID del usuario: ");
            idUsuario = sc.nextLine();
            System.out.print("Ingresa la clave: ");
            pin = sc.nextLine();
            
            autorizaUsuario = elBanco.login(idUsuario, pin);
            
            if (autorizaUsuario == null) {
                System.out.println("Información incorrecta. Vuelva a intentar "
                        + "por favor.");
                intentos +=1;
            }
            
        } while (autorizaUsuario == null);
        
        return autorizaUsuario;
    }

    /**
     *
     * @param elUsuario
     * @param sc
     */
    protected static void imprimirMenuUsuario(Usuario elUsuario, Scanner sc) {
        
      elUsuario.imprimirResumenCuentas();
      
      byte opcion = 0;
        
        do{
        System.out.printf("Bienvenido %s, tienes las siguientes opciones: \n", 
                elUsuario.getNombre());
        System.out.println(" 1. Mostrar historial de transacciones");
        System.out.println(" 2. Retirar dinero");
        System.out.println(" 3. Depositar dinero");
        System.out.println(" 4. Transferencia");
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

    private static void mostrarHistorial(Usuario elUsuario, Scanner sc) {
        
        int indiceCuenta = -1;
        
        while(indiceCuenta <0 || indiceCuenta > elUsuario.numeroCuentas()){
            System.out.printf("Ingresa el índice (1-%d) de la cuenta" +
                    "de la transacción que quiere ver:", elUsuario.numeroCuentas());
            
            indiceCuenta = sc.nextInt()-1;
            
            if(indiceCuenta <0 || indiceCuenta > elUsuario.numeroCuentas()){
                System.out.println("Indice fuera del rango. Intenta de nuevo.");
            }
        }
        
        elUsuario.imprimirHistorialMovimientosCuenta(indiceCuenta);
        
    }


    private static int solicitarCuenta(Usuario elUsuario, boolean retirar, Scanner sc){
        
        int indice;
        do{
            //En caso que se vaya a retirar fondos de una cuenta
            if (retirar){
                System.out.printf("\"Ingresa el índice (1-%d) de la cuenta\" +\n" +
                        " a partir de la cual quiere retirar fondos: ",elUsuario.numeroCuentas());
            }else{
                System.out.printf("\"Ingresa el índice (1-%d) de la cuenta\" +\n" +
                        " a la cual quiere adicionar fondos: ", elUsuario.numeroCuentas());
            }
            
            //En caso que se vaya a adicionar fondos de una cuenta
            indice = sc.nextInt()-1;
            if(indice <0 || indice > elUsuario.numeroCuentas()){
                System.out.println("Indice fuera del rango. Intenta de nuevo.");
            }
            
        }while(indice <0 || indice > elUsuario.numeroCuentas());
        
        return indice;
    }
        
           
    // Transferir fondos de una cuenta a otra
    private static void transferirFondos(Usuario elUsuario, Scanner sc) {
        
        int icuentaOrigen = solicitarCuenta(elUsuario, true, sc);
        int icuentaDestino = solicitarCuenta(elUsuario, false, sc);
        double cantidad;
        double balance;
        
        balance = elUsuario.obtenerBalanceCuenta(icuentaOrigen);
          
        
        //Solicitar valor a transferir
        
        do{
            System.out.printf("Ingresa la cantidad que desea transferir (máx $%.03f): $",
                    balance);
            cantidad = sc.nextDouble();
            
            if(cantidad < 0){
                System.out.println("Cantidad debe ser mayor a 0");
            } else if(cantidad > balance){
                System.out.printf("La cantidad no puede ser mayor a $%.03f.\n",balance);
            }           
        }while(cantidad < 0 || cantidad > balance);
        
        elUsuario.agregarTransaccion(icuentaOrigen, -1*cantidad, String.format(
                "Transferencia de cuenta %s",elUsuario.getIdCuentas(icuentaDestino)));
        
        elUsuario.agregarTransaccion(icuentaDestino, cantidad, String.format(
                "Transferencia a cuenta %s",elUsuario.getIdCuentas(icuentaOrigen)));
        
    }

    
    private static void retitarFondos(Usuario elUsuario, Scanner sc) {
        int idCuenta;
        double cantidad;
        double balance;
        String info;
        
        //Solicitar cuenta de origen
        idCuenta = solicitarCuenta(elUsuario, true, sc);
        
        balance = elUsuario.obtenerBalanceCuenta(idCuenta);
        
        do{
            System.out.printf("Ingresa la cantidad que desea retirar (máx $%.03f): $",
                    balance);
            cantidad = sc.nextDouble();
            
            if(cantidad < 0){
                System.out.println("Cantidad debe ser mayor a 0");
            } else if(cantidad > balance){
                System.out.printf("La cantidad no puede ser mayor a $%.03f.\n",balance);
            }           
        }while(cantidad < 0 || cantidad > balance);
        
        sc.nextLine();
        
        System.out.println("Ingresa información de la transacción:");
        info = sc.nextLine();
        
        elUsuario.agregarTransaccion(idCuenta, -1*cantidad, String.format(
                "Retiro: %s", info));

    }
       
    private static void ingresarFondos(Usuario elUsuario, Scanner sc) {
        int idCuenta;
        double cantidad;
        String info;
        
        //Solicitar cuenta de origen
        idCuenta = solicitarCuenta(elUsuario, false, sc);
       
        System.out.print("Ingresa la cantidad que desea depositar: $"); 
        cantidad = sc.nextDouble();
            
        sc.nextLine();
        
        System.out.println("Ingresa información de la transacción:");
        info = sc.nextLine();
        
        elUsuario.agregarTransaccion(idCuenta, cantidad, String.format(
                "Deposito: %s", info));
       
    }
    
    public void agregarBanco(Banco elBanco){
        this.listaBancos.add(elBanco);
    }

    public ArrayList<Banco> getListaBancos() {
        return listaBancos;
    }
    
    
}

