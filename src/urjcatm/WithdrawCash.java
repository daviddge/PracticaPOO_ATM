/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.*;

/**
 *
 * @author dadig
 */

public class WithdrawCash extends TitledOperation{
    
    //Constructor
    public WithdrawCash(OperationContext op){
        super(op);
    }
  
    @Override
    //método principal
    public boolean doOperation(){
        //inicialización del cajero y servidor
        ATM atm = super.getOperationContext().getAtm();
        OperationContext context = super.getOperationContext();
        UrjcBankServer server = context.getServer();
        String idioma = super.getOperationContext().getIdiom();
        
        //quitar las opciones del ATM
        for (int i = 0; i < 6; i++)
            atm.setOption(i, null);
        
        try {
            //Mostrar por pantalla operacion
            long accountId = atm.getCardNumber();
            int saldoDisponible = server.avaiable(accountId);
            atm.setTitle(getTitle(idioma));//llama al metodo que contiene el mensaje de sacar dinero
            atm.setInputAreaText(getBalance(idioma) + ": " + saldoDisponible + "€"); //mostrar el saldo disponible por pantalla y llama al metodo que contiene el mensaje de saldo disponible.
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        //captura la cantidad de dinero a retirar
        AtmNumberCapturer ATM_Nc = new AtmNumberCapturer(); //captura la cantisas de dinero solicitada por el usuario en el ATM
        int dinero;
        try {
            dinero = ATM_Nc.captureAmount(atm);
        } catch (NumberFormatException e) {
            atm.setInputAreaText(getError(idioma));//llama al metodo que contiene el mensaje de error
            atm.waitEvent(30);
            return true;
        }
    
        if (dinero % 10 != 0){ //Indica que el dinero a retirar tiene que ser multiplo de 10
            atm.setInputAreaText(getNoMoney(idioma));//llama al metodo que contiene el mensaje de sin fondos
            atm.waitEvent(30);
            return true;
        }
        //Comprobar fondos y retirar dinero
        try {
            long accountId = atm.getCardNumber(); //
            if (dinero <= server.avaiable(accountId)){ //comprueba que la cantidad a retirar no sea mayor que el saldo disponible 
                server.doOperation(accountId, -dinero); //actualiza el saldo disponible después de sacar dinero
                atm.setInputAreaText(getSuccessfulRetirement(idioma));//llama al metodo que contiene el mensaje de retiro exitoso
                atm.expelAmount(dinero, 30);
            } else {
                atm.setInputAreaText(getNoMoney(idioma));//llama al metodo que contiene el mensaje de sin fondos
                atm.waitEvent(30);
                return true;
            }
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
        
    }

    // Método para obtener el mensaje de sacar dinero según el idioma
    private String getTitle(String idioma) {
        return switch (idioma) {
            case "ES" -> "Sacar dinero";
            case "EN" -> "Withdraw Cash";
            case "CA" -> "Treure diners";
            case "EU" -> "Dirua hartu";
            default -> "Últimas operaciones del usuario";
        };
    }
                
    @Override
    public String getTitle(){
        String idioma = super.getOperationContext().getIdiom();
        return switch (idioma) {
            case "ES" -> "Sacar dinero";
            case "EN" -> "Withdraw Cash";
            case "CA" -> "Treure diners";
            case "EU" -> "Dirua hartu";
            default -> "Últimas operaciones del usuario";
        };
    }

    // Método para obtener el mensaje cuando no hay fondos suficientes según el idioma
    private String getNoMoney(String idioma) {
        return switch (idioma) {
            case "ES" -> "Fondos insuficientes.";
            case "EN" -> "Not enough money.";
            case "CA" -> "Diners insuficients.";
            case "EU" -> "Diru nahikoa.";
            default -> "Fondos insuficientes.";
        };
    }

    // Método para obtener el mensaje de error según el idioma
    private String getSuccessfulRetirement(String idioma) { 
        return switch (idioma) {
            case "ES" -> "Retiro exitoso.";
            case "EN" -> "Successful withdrawal.";
            case "CA" -> "Retir exitós.";
            case "EU" -> "Erretiratzea arrakastatsua.";
            default -> "Retiro exitoso.";
        };
    }
    // Método para obtener el mensaje del saldo disponible según el idioma
    private String getBalance(String idioma) { 
        return switch (idioma) {
            case "ES" -> "Saldo disponible";
            case "EN" -> "Available balance";
            case "CA" -> "Saldo disponible";
            case "EU" -> "Saldo erabilgarria";
            default -> "Saldo disponible";
        };
    }
    // Método para obtener el mensaje de error al ingresar la cantidad según el idioma
    private String getError(String idioma) { 
        return switch (idioma) {
            case "ES" -> "Error al ingresar la cantidad";
            case "EN" -> "Error when entering quantity";
            case "CA" -> "Error en ingressar la quantitat";
            case "EU" -> "Errore bat kantitatea sartzean";
            default -> "Error al ingresar la cantidad";
        };
    }

}
    

    
