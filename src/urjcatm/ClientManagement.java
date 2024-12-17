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
public class ClientManagement extends AtmOperation{
    //Constructor
    public ClientManagement(OperationContext op){
        super(op);
    }
    
    //Metodos heredados
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        atm.retainCreditCard(false);
        
        //Obtener tarjeta de credito
        waitForClient(atm);
        //Seleccionar idioma
        IdiomSelection IdiomOperation = new IdiomSelection(super.getOperationContext());
        IdiomOperation.doOperation();
        //Identificar cliente
        if (clientIdentification(atm)){
            OptionMenu optionMenu = new OptionMenu(super.getOperationContext());
            while (optionMenu.doOperation()){}
        }
        ClientGoodbye goodbyeOperation = new ClientGoodbye(super.getOperationContext());
        if (goodbyeOperation.doOperation()){
                setLayoutGoodbye();
                return true;
            }else{
                atm.retainCreditCard(true);
                setLayoutCardHeld();
                return false;
        }
    }
    //Metodos
    public void presentOptions(){
    }
    public void waitForClient(ATM atm){//Obtener tarjeta credito
        atm.setTitle("Bienvenido");
        atm.setInputAreaText("");
        
        for (int cont = 0; cont < 6; cont++) //Vaciar options
            atm.setOption(cont, null);
    
        try {   //Pasan 1.5seg para cargar siguiente Title
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //Obtener tarjeta
        atm.setTitle("Introduzca la tarjeta");
        atm.setInputAreaText("");
        char input = atm.waitEvent(30);
        while (input != 1) {
            input = atm.waitEvent(30);
        }
        //Retener tarjeta (no permanente)
        atm.retainCreditCard(false);
    }
    public boolean clientIdentification(ATM atm){
        ClientIdentification identificationOperation = new ClientIdentification(super.getOperationContext());
        return identificationOperation.doOperation();
    }
    
    private void setLayoutGoodbye(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("¡Vuelva pronto!");
                break;
            case ("EN"):
                atm.setTitle("Come back soon!");
                break;
            case ("CA"):
                atm.setTitle("Torni aviat!");
                break;
            case ("EU"):
                atm.setTitle("Itzul zaitez laster!");
                break;
            default:
                atm.setTitle("¡Vuelva pronto!");
        }
        try {   //Pasan 2 seg para cargar siguiente Title
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void setLayoutCardHeld() {
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Tarjeta retenida");
                break;
            case ("EN"):
                atm.setTitle("Card held");
                break;
            case ("CA"):
                atm.setTitle("Targeta retinguda");
                break;
            case ("EU"):
                atm.setTitle("Atxikitako txartela");
                break;
            default:
                atm.setTitle("Tarjeta retenida");
        }
    }
    
}
