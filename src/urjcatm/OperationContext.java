/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import sienens.ATM;
import urjc.UrjcBankServer;


/**
 *
 * @author dadig
 */
public class OperationContext {
    //Atributos
    private ATM atm;
    private UrjcBankServer server;
    private String idiom;
    
    //Constructor
    public OperationContext(ATM atm, UrjcBankServer server){
        this.atm = atm;
        this.server = server;
        idiom = "español";
    }
    
    //Metodos
    public UrjcBankServer getServer(){
        return server;
    }
    public ATM getAtm(){
        return atm;
    }
    public String getIdiom(){
        return idiom;
    }
    public void setIdiom(String idiom){
        this.idiom = idiom;
    }
}
