/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

/**
 *
 * @author dadig
 */
public abstract class TitledOperation extends AtmOperation{
    public TitledOperation(OperationContext op){
        super(op);
    }
    //Metodos
    public abstract String getTitle();
        
    
}