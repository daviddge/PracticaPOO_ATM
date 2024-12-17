/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urjcatm;

import java.util.List;
import java.util.ArrayList;
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
        String idioma = super.getOperationContext().getIdiom();
        try{
            operaciones = server.getLastOperations(accountId);
            atm.setTitle(getTitle());
            if (operaciones.isEmpty()) {
                atm.setInputAreaText(getNoOperationsMessage(idioma));
            }
            List<String> operationsText = new ArrayList<>();
            for (int i = 0; i < operaciones.size(); i++) {
                Operation op = operaciones.get(i); 
                operationsText.add(getOperationDetails(idioma, op));
            }

            atm.print(operationsText);
            try {   //Pasan 1.5seg para cargar siguiente Title
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch(CommunicationException e) {
            atm.setInputAreaText(getErrorMessage(idioma));
            return false;
        }
        return true;
    }
    // Método para obtener el título según el idioma
    @Override
    public String getTitle() {
        String idioma = super.getOperationContext().getIdiom();
        switch (idioma) {
            case "ES":
                return "Últimas operaciones del usuario";
            case "EN":
                return "Last user operations";
            case "CA":
                return "Últimes operacions de l'usuari";
            case "EU":
                return "Erabiltzailearen azken operazioak";
            default:
                return "Últimas operaciones del usuario";
        }
    }

    // Método para obtener el mensaje cuando no hay operaciones según el idioma
    private String getNoOperationsMessage(String idioma) {
        switch (idioma) {
            case "ES":
                return "No hay operaciones recientes.";
            case "EN":
                return "No recent operations.";
            case "CA":
                return "No hi ha operacions recents.";
            case "EU":
                return "Ez dago operaziorik berririk.";
            default:
                return "No hay operaciones recientes.";
        }
    }

    // Método para obtener el mensaje de error según el idioma
    private String getErrorMessage(String idioma) {
        switch (idioma) {
            case "ES":
                return "Error al obtener las últimas operaciones.";
            case "EN":
                return "Error retrieving the last operations.";
            case "CA":
                return "Error en obtenir les últimes operacions.";
            case "EU":
                return "Errorea azken operazioak lortzean.";
            default:
                return "Error al obtener las últimas operaciones.";
        }
    }
     private String getOperationDetails(String idioma,Operation op) {
        List<String> operationText = new ArrayList<>();
        switch (idioma) {
            case "ES":
                operationText.add("Fecha: "+op.getDate()+"\n");
                operationText.add("Detalles: "+op.getDetail()+"\n");
                operationText.add("Cantidad: "+op.getAmount()+"€\n");
                break;
            case "EN":
                operationText.add("Date: "+op.getDate()+"\n");
                operationText.add("Details: "+op.getDetail()+"\n");
                operationText.add("Amount: "+op.getAmount()+"€\n");
                break;
            case "CA":
                operationText.add("Data: "+op.getDate()+"\n");
                operationText.add("Detalls: "+op.getDetail()+"\n");
                operationText.add("Quantitat: "+op.getAmount()+"€\n");
                break;
            case "EU":
                operationText.add("Data: "+op.getDate()+"\n");
                operationText.add("Xehetasunak: "+op.getDetail()+"\n");
                operationText.add("Kantitatea: "+op.getAmount()+"€\n");
                break;
            default:
                operationText.add("Fecha: "+op.getDate()+"\n");
                operationText.add("Detalles: "+op.getDetail()+"\n");
                operationText.add("Cantidad: "+op.getAmount()+"€\n");
        }
        return operationText.toString();
    }
    
}
