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
        atm.setTitle("Inserte contraseña");
        atm.setInputAreaText("Contraseña");
        for (int cont = 0; cont < 6; cont++) //Vaciar options
            atm.setOption(cont, null);
        
        long card = (long) atm.getCardNumber();
        int intentos = 3;
        return comprobarTarjeta(obtenerPin(atm),card);
    }
    
    private int obtenerPin(ATM atm) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            char input = atm.waitEvent(30);
            texto.append(input);
            atm.setInputAreaText(texto.toString());
        }
        return Integer.parseInt(texto.toString());
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
}