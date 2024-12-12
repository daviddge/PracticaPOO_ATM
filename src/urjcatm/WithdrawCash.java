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
public class WithdrawCash extends TitledOperation{
    //Constructor
    public WithdrawCash(OperationContext op){
        super(op);
    }
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        
        for(int i=0; i < 6; i++)
            atm.setOption(i, null);
        
        atm.setTitle("Ingrese la cantidad a retirar");
        char event = atm.waitEvent(30);
        String cadena = "";
        while (event >= '0' && event <= '9') {
            cadena += event;
            atm.setInputAreaText(cadena + " €");
            event = atm.waitEvent(30);
        }
        if (event % 10 != 0){
            atm.setInputAreaText("Cantidad no disponiblee");
        }
        return true;
        
    }
    @Override
    public String getTitle(){
        return "String0";
    }

}
