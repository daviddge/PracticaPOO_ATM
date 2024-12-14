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
    @Override
    public boolean doOperation() {
        ATM atm = super.getOperationContext().getAtm();
        atm.setTitle("Seleccione una operación:");
        atm.setOption(0, "Sacar dinero");
        atm.setOption(1, "Obtener últimas operaciones");
        atm.setOption(2, "Consultar saldo");
        atm.setOption(3, "Cambiar contraseña");
        atm.setOption(5, "Terminar");
        atm.setInputAreaText("");

        char event = atm.waitEvent(30);
        while (event < 'A' || event > 'F' || event == 'E')
            event = atm.waitEvent(30);
        switch(event) {
            case ('A') -> {
                // Si el usuario presiona 'A', selecciona "Sacar dinero"
                WithdrawCash withdrawCash = new WithdrawCash(super.getOperationContext());
                //return withdrawCash.doOperation(); // Ejecuta la operación de "Sacar dinero"
                boolean success = withdrawCash.doOperation();
                if(!success){
                    return false;
                }
            }
            case ('B') -> {
                // Si el usuario presiona 'B', selecciona "Obtener últimas operaciones"
                LastOperations lastOperations = new LastOperations(super.getOperationContext());
                //return lastOperations.doOperation(); // Ejecuta la operación de "Obtener últimas operaciones"
                lastOperations.doOperation();
            }
            case ('C') -> {
                // Si el usuario presiona 'C', selecciona "Consultar saldo"
                AccountBalance accountBalance = new AccountBalance(super.getOperationContext());
                //return accountBalance.doOperation(); // Ejecuta la operación de "Consultar saldo"
                accountBalance.doOperation();
            }
            case ('D') -> {
                // Si el usuario presiona 'D', selecciona "Cambiar contraseña"
                ChangePassword changePassword = new ChangePassword(super.getOperationContext());
                //return changePassword.doOperation(); // Ejecuta la operación de "Cambiar contraseña"
                changePassword.doOperation();
            }
            case ('F') -> {
                // Si el usuario presiona 'E', selecciona "Terminar"
                System.out.println("Operacion terminada.");
                return false; // Aquí podrías agregar la lógica para terminar la operación o cerrar la sesión
            }
            default -> {
                System.out.println("Operation_error");
                return false;
            }

        }
        return anotherOperation(atm);
    }
    private boolean anotherOperation(ATM atm){
        setLayoutAnotherOperation();
        char input = atm.waitEvent(30);
        while (input != 'A' && input != 'D')
            input = atm.waitEvent(30);
        return (input == 'A');
    }
    private void setLayoutAnotherOperation(){            
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        for (int i = 1; i < 6; i++)
            atm.setOption(i, null);     
        atm.setInputAreaText("");
        switch (idioma) {
            case ("ES") -> {
                atm.setTitle("¿Desea realizar otra operacion?");
                atm.setOption(0,"Si");
                atm.setOption(3,"No");
            }
            case ("EN") -> {
                atm.setTitle("Would you like to make another trade?");
                atm.setOption(0,"Yes");
                atm.setOption(3,"No");
            }
            case ("CA") -> {
                atm.setTitle("Apakah do tuju dohot manangih perdagangan na lain?");
                atm.setOption(0,"Si");
                atm.setOption(3,"No");
            }
            case ("EU") -> {
                atm.setTitle("Beste eragiketa bat egin nahi duzu?");
                atm.setOption(0,"Bai");
                atm.setOption(3,"Ez");
            }
            default -> {
                atm.setTitle("¿Desea realizar otra operacion?");
                atm.setOption(0,"Si");
                atm.setOption(3,"No");
            }
        }
    
    }

}
