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
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        OperationContext context = super.getOperationContext();
        UrjcBankServer server = context.getServer();
        String idioma = super.getOperationContext().getIdiom();
        
        for (int i = 0; i < 6; i++)
            atm.setOption(i, null);
        
        try {
            //Mostrar por pantalla operacion
            long accountId = atm.getCardNumber();
            int saldoDisponible = server.avaiable(accountId);
            atm.setTitle(getTitle());
            atm.setInputAreaText(getBalance(idioma) + ": " + saldoDisponible + "€");
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        AtmNumberCapturer ATM_Nc = new AtmNumberCapturer();
        int dinero;
        try {
            dinero = ATM_Nc.captureAmount(atm);
        } catch (NumberFormatException e) {
            atm.setInputAreaText(getError(idioma));
            atm.waitEvent(30);
            return true;
        }
    
        if (dinero % 10 != 0){
            atm.setInputAreaText(getNoMoney(idioma));
            atm.waitEvent(30);
            return true;
        }
        //Comprobar y retirar dinero
        try {
            long accountId = atm.getCardNumber();
            if (dinero <= server.avaiable(accountId)){
                server.doOperation(accountId, -dinero);
                atm.setInputAreaText(getSuccessfulRetirement(idioma));
                atm.expelAmount(dinero, 30);
                //atm.waitEvent(0);
            } else {
                atm.setInputAreaText(getNoMoney(idioma));
                atm.waitEvent(30);
                return true;
            }
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
        
    }
    @Override
    public String getTitle(){
        String idioma = super.getOperationContext().getIdiom();
        switch (idioma) {
            case "ES":
                return "Sacar dinero";
            case "EN":
                return "Withdraw Cash";
            case "CA":
                return "Treure diners";
            case "EU":
                return "Dirua hartu";
            default:
                return "Últimas operaciones del usuario";
        }
    }

    // Método para obtener el mensaje cuando no hay operaciones según el idioma
    private String getNoMoney(String idioma) {
        switch (idioma) {
            case "ES":
                return "Fondos insuficientes.";
            case "EN":
                return "Not enough money.";
            case "CA":
                return "Diners insuficients.";
            case "EU":
                return "Diru nahikoa.";
            default:
                return "Fondos insuficientes.";
        }
    }

    // Método para obtener el mensaje de error según el idioma
    private String getSuccessfulRetirement(String idioma) {
        switch (idioma) {
            case "ES":
                return "Retiro exitoso.";
            case "EN":
                return "Successful withdrawal.";
            case "CA":
                return "Retir exitós.";
            case "EU":
                return "Erretiratzea arrakastatsua.";
            default:
                return "Retiro exitoso.";
        }
    }
    
    private String getBalance(String idioma) {
        switch (idioma) {
            case "ES":
                return "Saldo disponible";
            case "EN":
                return "Available balance";
            case "CA":
                return "Saldo disponible";
            case "EU":
                return "Saldo erabilgarria";
            default:
                return "Saldo disponible";
        }
    }
    
    private String getError(String idioma) {
        switch (idioma) {
            case "ES":
                return "Error al ingresar la cantidad";
            case "EN":
                return "Error when entering quantity";
            case "CA":
                return "Error en ingressar la quantitat";
            case "EU":
                return "Errore bat kantitatea sartzean";
            default:
                return "Error al ingresar la cantidad";
        }
    }

}
    
