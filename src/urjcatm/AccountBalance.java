/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

/**
 *
 * @author dadig
 */
public class AccountBalance extends TitledOperation{
    //Constructor
    public AccountBalance(OperationContext op){
        super(op);
    }
    @Override
    public boolean doOperation(){
        return true;
    }
    @Override
    public String getTitle(){
        return "String0";
    }
    
}
