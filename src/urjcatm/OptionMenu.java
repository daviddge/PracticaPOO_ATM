/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.ArrayList;
import java.util.List;
import sienens.ATM;

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
    public boolean doOperation() {
    ATM atm = super.getOperationContext().getAtm();
    atm.setTitle("Seleccione una operación:");
    atm.setOption(0, "Sacar dinero");
    atm.setOption(1, "Obtener últimas operaciones");
    atm.setOption(2, "Consultar saldo");
    atm.setOption(3, "Cambiar contraseña");
    atm.setOption(5, "Terminar");

    char event = atm.waitEvent(30);
    switch(event) {
        case 'A': // Si el usuario presiona 'A', selecciona "Sacar dinero"
            WithdrawCash withdrawCash = new WithdrawCash(super.getOperationContext());
            return withdrawCash.doOperation(); // Ejecuta la operación de "Sacar dinero"
        
        case 'B': // Si el usuario presiona 'B', selecciona "Obtener últimas operaciones"
            LastOperations lastOperations = new LastOperations(super.getOperationContext());
            return lastOperations.doOperation(); // Ejecuta la operación de "Obtener últimas operaciones"
        
        case 'C': // Si el usuario presiona 'C', selecciona "Consultar saldo"
            AccountBalance accountBalance = new AccountBalance(super.getOperationContext());
            return accountBalance.doOperation(); // Ejecuta la operación de "Consultar saldo"
        
        case 'D': // Si el usuario presiona 'D', selecciona "Cambiar contraseña"
            ChangePassword changePassword = new ChangePassword(super.getOperationContext());
            return changePassword.doOperation(); // Ejecuta la operación de "Cambiar contraseña"
        
        case 'E': // Si el usuario presiona 'E', selecciona "Terminar"
            System.out.println("Operación terminada.");
            return true; // Aquí podrías agregar la lógica para terminar la operación o cerrar la sesión

    }
    return true;
}

}
