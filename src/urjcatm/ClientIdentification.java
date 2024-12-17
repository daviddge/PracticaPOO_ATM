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
        int intentos = 3;
        boolean conexion = true;
        AtmNumberCapturer Atm_Nc = new AtmNumberCapturer();
        setLayoutInsertPassword();
        int pin = Atm_Nc.capturePassword(atm);
        
        while (intentos > 0 && conexion && !comprobarTarjeta(pin,atm.getCardNumber())){
            //Comprobar si se ha perdido la conexion
            if (!super.getOperationContext().getServer().comunicationAvaiable()){
                conexion = false;
            }else if (pin == -1){
                return false;
            }else{
                --intentos;
                setLayoutIncorrectPassword(intentos);//Mostrar contraseña incorrecta
            }
            try {   //Pasan 1 seg para cargar siguiente Title
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (intentos > 0){
                setLayoutInsertPassword();
                pin = Atm_Nc.capturePassword(atm);
            }
        }
        
        if (conexion){
            if (intentos == 0){
                //Retener tarjeta
                atm.retainCreditCard(true);
                setLayoutCardHeld();
                try {   //Pasan 99 seg para cargar siguiente Title
                    Thread.sleep(99000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }else{
                return true;
            }
        }else{
            ErrorExit errorOperation = new ErrorExit(super.getOperationContext());
            errorOperation.doOperation();
            try {   //Pasan 1.5 seg para cargar siguiente Title
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    private boolean comprobarTarjeta(int pin, long card){
        //Comunica con el servidor para comprobar contraseña
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
        for (int cont = 0; cont < 6; cont++) //Vaciar options
            atm.setOption(cont, null);
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
    private void setLayoutCardHeld() {
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES"):
                atm.setTitle("Tarjeta retenida");
                atm.setInputAreaText("Por favor, ");
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
    private void setLayoutIncorrectPassword(int intentos){
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        switch(idioma){
            case("ES"):
                atm.setTitle("Contraseña incorrecta");
                atm.setInputAreaText("Intentos restantes: " + intentos);
                break;
            case("EN"):
                atm.setTitle("Incorrect password");
                atm.setInputAreaText("Remaining attempts: " + intentos);
                break;
            case("CA"):
                atm.setTitle("Contrasenya incorrecta");
                atm.setInputAreaText("Intents restants: " + intentos);
                break;
            case("EU"):
                atm.setTitle("Pasahitz okerra");
                atm.setInputAreaText("Gainerako saiakerak: " + intentos);
                break;
            default:
                atm.setTitle("Contraseña incorrecta");
                atm.setInputAreaText("Intentos restantes: " + intentos);                
        }
    }
}