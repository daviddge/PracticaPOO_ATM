/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.ATM;

/**
 *
 * @author dadig
 */
public class ChangePassword extends TitledOperation{
    //Constructor
    public ChangePassword(OperationContext op){
        super(op);
    }
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        long card = atm.getCardNumber();
        AtmNumberCapturer Atm_nb = new AtmNumberCapturer();
        for (int i = 0; i < 6; i++)
            atm.setOption(i, null);
        atm.setTitle(getTitle());
        try {
            if (super.getOperationContext().getServer().changePassword(Atm_nb.captureAmount(atm), card)){
                setLayoutPasswordChanged(); //Contraseña valida
            }else{
                setLayoutPasswordNotChanged(); //Contraseña no valida
            }
        } catch (CommunicationException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {   //Pasan 2 seg para cargar siguiente Title
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public String getTitle(){
        String idioma = super.getOperationContext().getIdiom();
        switch (idioma) {
            case "ES":
                return "Inserte contraseña";
            case "EN":
                return "Insert password";
            case "CA":
                return "Insereix contrasenyaTreure diners";
            case "EU":
                return "Pasahitza inserratu";
            default:
                return "Inserte contraseña";
        }
    }
    private void setLayoutPasswordChanged(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Contraseña cambiada");
                break;
            case ("EN"):
                atm.setTitle("Password changed");
                break;
            case ("CA"):
                atm.setTitle("Contrasenya canviada");
                break;
            case ("EU"):
                atm.setTitle("Pasahitza aldatuta");
                break;
            default:
                atm.setTitle("Contraseña cambiada");;
        }
    }
    private void setLayoutPasswordNotChanged(){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Contraseña no valida");
                break;
            case ("EN"):
                atm.setTitle("Invalid password");
                break;
            case ("CA"):
                atm.setTitle("Contrasenya no valida");
                break;
            case ("EU"):
                atm.setTitle("Baliozkotu gabeko pasahitza");
                break;
            default:
                atm.setTitle("Contraseña no valida");;
        }
        
    }
    
}