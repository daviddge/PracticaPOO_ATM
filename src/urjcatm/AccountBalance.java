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
        String idioma = super.getOperationContext().getIdiom();
        
        for(int i=0; i < 6; i++)
            atm.setOption(i, null);
        try {
            long accountId = atm.getCardNumber();
            int saldoDisponible = server.avaiable(accountId);
            atm.setTitle(getTitle());
            String saldoFormateado = String.format("%,d", saldoDisponible) + "€";
            atm.setInputAreaText(getBalance(idioma)+ ": " + saldoFormateado);
            atm.waitEvent(30); 
            return true;

        } catch (CommunicationException e) {
            atm.setInputAreaText("Error al obtener el saldo. Inténtelo de nuevo más tarde.");
            return false;
        }
    }
    @Override
    public String getTitle(){ 
        String idioma = super.getOperationContext().getIdiom();
        switch (idioma) {
            case "ES":
                return "Consulta de saldo";
            case "EN":
                return "Balance inquiry";
            case "CA":
                return "Consulta de saldo";
            case "EU":
                return "Balantzearen kontsulta";
            default:
                return "Consulta de saldo";
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
    
    
}
