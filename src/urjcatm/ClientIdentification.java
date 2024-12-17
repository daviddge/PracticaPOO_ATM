/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import javax.naming.CommunicationException;
import sienens.ATM;


/**
 *
 * @author dadig
 */
public class ClientIdentification extends AtmOperation{
    //Constructor
    public ClientIdentification(OperationContext op){
        super(op);
    }
    
    //Metodos
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        setLayoutInsertPassword();
        for (int cont = 0; cont < 6; cont++) //Vaciar options
            atm.setOption(cont, null);
        
        long card = (long) atm.getCardNumber();
        AtmNumberCapturer Atm_Nc = new AtmNumberCapturer();
        return comprobarTarjeta(Atm_Nc.capturePassword(atm),card);
    }
    private boolean comprobarTarjeta(int pin, long card){
        try{
            return super.getOperationContext().getServer().testPassword(pin, card);
        }catch(CommunicationException e){
            System.out.println(e.getMessage());
            return false;
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    private void setLayoutInsertPassword(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        switch(idioma){
            case("ES"):
                atm.setTitle("Inserte contraseña");
                atm.setInputAreaText("Contraseña");
                break;
            case("EN"):
                atm.setTitle("Insert password");
                atm.setInputAreaText("Password");
                break;
            case("CA"):
                atm.setTitle("Insereix contrasenya");
                atm.setInputAreaText("Contrasenya");
                break;
            case("EU"):
                atm.setTitle("Pasahitza inserratu");
                atm.setInputAreaText("Pasahitza");
                break;
            default:
                atm.setTitle("Inserte contraseña");
                atm.setInputAreaText("Contraseña");             
        }
    }
}