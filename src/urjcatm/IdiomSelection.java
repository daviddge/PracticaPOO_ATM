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
public class IdiomSelection extends AtmOperation{
    //Constructor
    public IdiomSelection(OperationContext op){
        super(op);
    }
    //Metodos
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        atm.setTitle("Seleccione idioma");
        atm.setOption(0, "Español");
        atm.setOption(1, "Inglés");
        atm.setOption(2, null);
        atm.setOption(3, "Catalán");
        atm.setOption(4, "Euskera");
        atm.setOption(5, null);

        
        char event = atm.waitEvent(30);
        if (event == 'N')
            return false;
        while(event <'A' || event >'F'){
            event = atm.waitEvent(30);
        }
        switch(event){
            case('A'):
                super.getOperationContext().setIdiom("ES");
                break;
            case('B'):
                super.getOperationContext().setIdiom("EN");
                break;
            case('D'):
                super.getOperationContext().setIdiom("CA");
                break;
            case('E'):
                super.getOperationContext().setIdiom("EU");
                break;
            default:
                System.out.println("language_not_detected");
                return false;
        }
        return true;
    }
}
