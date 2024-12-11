/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package urjcatm;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.UrjcBankServer;
/**
 *
 * @author dadig
 */
public class UrjcAtm {

    public static void main(String[] args) {
        //Creacion Atm y Server
        ATM atm = new ATM();
        UrjcBankServer bankServer = new UrjcBankServer();
        //Inicio operacion
        OperationContext context = new OperationContext(atm,bankServer);
        ClientManagement client = new ClientManagement(context);
        client.doOperation();
    }
}
