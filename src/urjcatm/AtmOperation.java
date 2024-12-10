/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

/**
 *
 * @author dadig
 */
public abstract class AtmOperation {
    //Atributos
    private OperationContext context;
    
    //Constructor
    public AtmOperation(OperationContext op){
        context = op;
    }
    
    //Metodos
    public abstract boolean doOperation();
    public OperationContext getOperationContext(){
        return context;
    }
    
    
}
