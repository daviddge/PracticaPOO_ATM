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
        waitForClient(atm);
        IdiomSelection IdiomOperation = new IdiomSelection(super.getOperationContext());
        IdiomOperation.doOperation();
        clientIdentification(atm);
        OptionMenu optionMenu = new OptionMenu(super.getOperationContext());
        optionMenu.doOperation();
        
        
        return true;
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
    public void clientIdentification(ATM atm){
        ClientIdentification identificationOperation = new ClientIdentification(super.getOperationContext());
        int intentos = 3;

        while (intentos > 0 && !identificationOperation.doOperation()){
            --intentos;
            atm.setTitle("Contraseña incorrecta");
            atm.setInputAreaText("Intentos restantes: "+intentos);
            try {   //Pasan 1 seg para cargar siguiente Title
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //NO SE EJECUTA?
        if (intentos == 0){
            //Retener tarjeta
            System.out.println("Retener tarjeta");
            atm.retainCreditCard(true);
            atm.setTitle("Tarjeta retenida");
            atm.setInputAreaText("");

            //...
        }else{
            atm.setTitle("Contraseña correcta");
            atm.setInputAreaText("");
        }
    }
}
