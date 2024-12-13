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
        
        for(int i=0; i < 6; i++)
            atm.setOption(i, null);
        
        try {
        long accountId = atm.getCardNumber();
        int saldoDisponible = server.avaiable(accountId);
        atm.setTitle("Introduzca la cantidad a retirar");
        atm.setInputAreaText("Saldo disponible: " + saldoDisponible + "€");
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        char event = atm.waitEvent(30);
        String cadena = "";
        
        while (event >= '0' && event <= '9') {
            cadena += event;
            atm.setInputAreaText(cadena + " €");
            event = atm.waitEvent(30);
        }
    
        int dinero;
        try {
            dinero = Integer.parseInt(cadena);
        } catch (NumberFormatException e) {
            atm.setInputAreaText("Error al ingresar la cantidad.");
            return false;
        }
    
        if (dinero % 10 != 0){
            atm.setInputAreaText("Cantidad no disponible");
            return false;
        }
    
        try {
            long accountId = atm.getCardNumber();
            if(dinero <= server.avaiable(accountId)){
                atm.setInputAreaText("Retiro exitoso. Retire su dinero.");
            } else {
                atm.setInputAreaText("Fondos insuficientes.");
                return false;
            }
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
        
    }

    @Override
    public String getTitle() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
    
