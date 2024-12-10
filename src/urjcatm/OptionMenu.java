/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.List;
/**
 *
 * @author dadig
 */
public class OptionMenu extends AtmOperation{
    //Atributos
    private List<AtmOperation> operationList;
    
    //Constructor
    public OptionMenu(OperationContext op){
        super(op);
        
    }
    
    //Metodos
    @Override
    public boolean doOperation(){
        /* Añadir a la lista
        LastOperations operation2 = new LastOperations(super.getOperationContext());
        ChangePassword operation3 = new ChangePassword(super.getOperationContext());
        */
        
        
        return true;
    }
}