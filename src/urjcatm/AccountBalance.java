/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.UrjcBankServer;
/**
 *
 * @author dadig
 */
public class AccountBalance extends TitledOperation{
    //Constructor
    public AccountBalance(OperationContext op){
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
            atm.setTitle("Consulta de saldo");
            String saldoFormateado = String.format("%,d", saldoDisponible) + "€";
            atm.setInputAreaText("Saldo disponible: " + saldoFormateado);
            atm.waitEvent(30); 
            return true;

        } catch (CommunicationException e) {
            atm.setInputAreaText("Error al obtener el saldo. Inténtelo de nuevo más tarde.");
            return false;
        }
    }
    @Override
    public String getTitle(){
        return "String0";
    }
    
}
