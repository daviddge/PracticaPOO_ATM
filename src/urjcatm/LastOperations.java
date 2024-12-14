/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.List;
import javax.naming.CommunicationException;
import sienens.ATM;
import urjc.Operation;
import urjc.UrjcBankServer;
import urjcatm.OperationContext;

/**
 *
 * @author dadig
 */
public class LastOperations extends TitledOperation{
    public LastOperations(OperationContext op){
        super(op);
    }
    @Override
    public boolean doOperation(){
        ATM atm = super.getOperationContext().getAtm();
        OperationContext context = super.getOperationContext();
        UrjcBankServer server = context.getServer();
        
        for(int i=0; i < 6; i++)
            atm.setOption(i, null);

        long accountId = atm.getCardNumber();
        List<Operation> operaciones;
        try{
        operaciones = server.getLastOperations(accountId);
             if (operaciones.isEmpty()) {
                atm.setInputAreaText("No hay operaciones recientes.");

             }
             StringBuilder operationsText = new StringBuilder();
             for (int i = 0; i < operaciones.size(); i++) {
             Operation op = operaciones.get(i); 
             operationsText.append("Fecha: ").append(op.getDate())
                           .append(", Detalles: ").append(op.getDetail())
                           .append(", Cantidad: ").append(op.getAmount()).append("€\n");
                }

                atm.setInputAreaText(operationsText.toString());



        }catch(CommunicationException e) {
            atm.setInputAreaText("Error al obtener las últimas operaciones.");
            return false;
        }
        return false;
    }
    @Override
    public String getTitle(){
        return "String0";
    }
    
}
