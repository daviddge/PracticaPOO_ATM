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
        atm.setOption(0, "español");
        atm.setOption(1, "inglés");
        atm.setOption(2, null);
        atm.setOption(3, "catalán");
        atm.setOption(4, "euskera");
        atm.setOption(5, null);


        char event = atm.waitEvent(30);
        switch(event){
            case('A'):
                super.getOperationContext().setIdiom("español");
                break;
            case('B'):
                super.getOperationContext().setIdiom("inglés");
                break;
            case('D'):
                super.getOperationContext().setIdiom("catalán");
                break;
            case('E'):
                super.getOperationContext().setIdiom("euskera");
                break;
            default:
                System.out.println("Idioma no detectado");
                return false;
        }
        return true;
    }
}
