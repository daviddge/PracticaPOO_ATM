/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;
import sienens.ATM;

/**
 *
 * @author dadig
 */
public class ClientGoodbye extends AtmOperation{
    //Constructor
    public ClientGoodbye(OperationContext op){
        super(op);
    }
    //Metodos
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        setLayoutPickUpCard();
        return atm.expelCreditCard(30);
    }
    private void setLayoutPickUpCard(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        for (int i = 0; i < 6; i++)
            atm.setOption(i,null);
//        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Por favor, recoja su tarjeta");
                break;
            case ("EN"):
                atm.setTitle("Please pick up your card");
                break;
            case ("CA"):
                atm.setTitle("Per favor, reculli la seva targeta");
                break;
            case ("EU"):
                atm.setTitle("Mesedez, jaso zure txartela");
                break;
            default:
                atm.setTitle("Por favor, recoja su tarjeta");
        }
    }
    
}