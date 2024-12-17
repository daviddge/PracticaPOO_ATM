/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.ArrayList;
import java.util.List;
import sienens.ATM;

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
        setLayoutOperationMenu();
        

        char event = atm.waitEvent(30);
        while (event < 'A' || event > 'F' || event == 'E')
            event = atm.waitEvent(30);
        
        boolean success = false;
        switch(event) {
            case ('A') -> {
                // Si el usuario presiona 'A', selecciona "Sacar dinero"
                WithdrawCash withdrawCash = new WithdrawCash(super.getOperationContext());
                success = withdrawCash.doOperation();
            }
            case ('B') -> {
                // Si el usuario presiona 'B', selecciona "Obtener últimas operaciones"
                LastOperations lastOperations = new LastOperations(super.getOperationContext());
                success = lastOperations.doOperation();
            }
            case ('C') -> {
                // Si el usuario presiona 'C', selecciona "Consultar saldo"
                AccountBalance accountBalance = new AccountBalance(super.getOperationContext());
                success = accountBalance.doOperation();
            }
            case ('D') -> {
                // Si el usuario presiona 'D', selecciona "Cambiar contraseña"
                ChangePassword changePassword = new ChangePassword(super.getOperationContext());
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

        if (!success) {
            System.out.println("La operacion no se pudo completar.");
        return false;
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

    private void setLayoutOperationMenu(){            
        String idioma = super.getOperationContext().getIdiom();
        ATM atm = super.getOperationContext().getAtm();
        for (int i = 1; i < 6; i++)
            atm.setOption(i, null);     
        atm.setInputAreaText("");
        switch (idioma) {
        case ("ES"):
            atm.setTitle("Seleccione una operacion");
            atm.setOption(0,"Sacar dinero");
            atm.setOption(1,"Últimas operaciones");
            atm.setOption(2,"Consultar saldo");
            atm.setOption(3,"Cambiar contraseña");
            atm.setOption(5,"Terminar");
            break;
        case ("EN"):
            atm.setTitle("Select an operation");
            atm.setOption(0,"Withdraw Money");
            atm.setOption(1,"Last Operations");
            atm.setOption(2,"Check Balance");
            atm.setOption(3,"Change Password");
            atm.setOption(5,"Finish");
            break;
        case ("EU"):
            atm.setTitle("Hautatu eragiketa bat");
            atm.setOption(0,"Dirua hartu");
            atm.setOption(1,"Azken eragiketak");
            atm.setOption(2,"Balantzea egiaztatu");
            atm.setOption(3,"Pasahitza aldatu");
            atm.setOption(5,"Amaitu");
            break;
        case ("CA"):
            atm.setTitle("Seleccioney una operació");
            atm.setOption(0,"Treure diners");
            atm.setOption(1,"Ultimes operacions");
            atm.setOption(2,"Consultar saldo");
            atm.setOption(3,"Canviar contrasenya");
            atm.setOption(5,"Acabar");
            break;
        default:
            atm.setTitle("Seleccione una operacion");
            atm.setOption(0,"Sacar dinero");
            atm.setOption(1,"Últimas operaciones");
            atm.setOption(2,"Consultar saldo");
            atm.setOption(3,"Cambiar contraseña");
            atm.setOption(5,"Terminar");
        }
    }
}

