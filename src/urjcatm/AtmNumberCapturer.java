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
public class AtmNumberCapturer {
 
    //Metodos
    public int captureAmount(ATM atm){
        StringBuilder texto = new StringBuilder();
        int i = 0;
        char input = 0;
        while (input != 'Y') {
            input = atm.waitEvent(30);
            if (input >= '0' && input <= '9'){
                texto.append(input);
                i++;
            }else if (input == '-' && texto.length() > 0){
                texto.deleteCharAt(texto.length()-1);
                i--;
            }else if (input == 'N'){
                return -1;
            }else{
                //Si pulsa cualquier otra tecla no ocurre nada
            }
            atm.setInputAreaText(texto.toString());
        }
        if (texto.length() > 0){
            return Integer.parseInt(texto.toString());
        }else{
            return 0;
        }
    }
    public int capturePassword(ATM atm){
        StringBuilder texto = new StringBuilder();
        StringBuilder asteriscos = new StringBuilder();
        int i = 0;
        char input = 0;
        while (input != 'Y') {
            input = atm.waitEvent(30);
            if (input >= '0' && input <= '9'){
                texto.append(input);
                asteriscos.append('*');
                i++;
            }else if (input == '-' && asteriscos.length() > 0){
                texto.deleteCharAt(texto.length()-1);
                asteriscos.deleteCharAt(asteriscos.length()-1);
                i--;
            }else if (input == 'N'){
                return -1;
            }else{
                //Si pulsa cualquier otra tecla no ocurre nada
            }
            atm.setInputAreaText(asteriscos.toString());
        }
        if (texto.length() > 0){
            return Integer.parseInt(texto.toString());
        }else{
            return 0;
        }
    }
}
