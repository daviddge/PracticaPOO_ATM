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
public class ErrorExit extends AtmOperation{
    //Constructor
    public ErrorExit(OperationContext op){
        super(op);
    }
    //Metodos
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        setLayoutErrorExit();
        //return atm.expelCreditCard(30);
        return true;
    }
    private void setLayoutErrorExit(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Error de conexion");
                break;
            case ("EN"):
                atm.setTitle("Connection Error");

                break;
            case ("CA"):
                atm.setTitle("Error de connexió");
                break;
            case ("EU"):
                atm.setTitle("Konexio-errorea");
                break;
            default:
                atm.setTitle("Error de conexion");
        }
    }
}
