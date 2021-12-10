package org.javabasics.service;

import org.javabasics.controller.Controller;
import org.javabasics.model.Disponibile;
import org.javabasics.model.Magazine;
import org.javabasics.model.MagazineSet;
import org.javabasics.model.Subscription;
import org.javabasics.model.SubscriptionSet;
import org.javabasics.model.User;
import org.javabasics.model.UserSet;

public class Service {

    private static Service service;

    private MagazineSet magazineSet;
    private SubscriptionSet subscriptionSet;
    private UserSet userSet;

    private Service(){

        magazineSet = new MagazineSet();
        subscriptionSet = new SubscriptionSet();
        userSet = new UserSet();

        magazineSet.importFromCsv("/org/javabasics/csv/riviste.csv");
        subscriptionSet.importFromCsv("/org/javabasics/csv/abbonamenti.csv");
        userSet.importFromCsv("/org/javabasics/csv/utenti.csv");

    }

    public static Service getInstance(){
        if(service == null)
            service = new Service();
        return service;
    }

    public String request(int code){
        
        String result = "";
        
        switch(code){
            case 0:
                result = "ARRIVEDERCI";
                break;
            case 1:
                result = magazineSet.toString();
                break;
            case 2:
                result = addSubscription();
                break;
            case 3:
                result = removeSubscription();
                break;
            case 4:
                result = addUser();
                break;
            case 5:
                result = exportFreeMagazine();
                break;
            case 6:
                result = userSet.toString();
                break;
            case 7:
                result = subscriptionSet.toString();
                break;
            default:
                result = "ISTRUZIONE NON VALIDA";
                break;
        }

        return result;
    }

    private String addUser(){
        String result = "";
        Controller controller = Controller.getInstance();
        User newUser = controller.readUser();
        if(userSet.add(newUser)){
            result = "UTENTE INSERITO CORRETTAMENTE";
        }
        else{
            result = "IMPOSSIBILE INSERIRE L'UTENTE: L'ID UTENTE DEVE ESSERE UNICO ";
        }
        return result;
    }

    private String addSubscription(){
        Controller controller = Controller.getInstance();
        Subscription newSubscription = controller.readSubscription(subscriptionSet.getNextId());
        int magazineId = newSubscription.getIdRivista();
        int userId = newSubscription.getIdUtente();
        if(!magazineSet.existId(magazineId) || !userSet.existId(userId))
            return "IMPOSSIBILE INSERIRE L'ABBONAMENTO: L'ID UTENTE O L'ID RIVISTA NON ESISTONO";
        Magazine mag = magazineSet.getById(magazineId);
        if(!mag.isFree())
            return "IMPOSSIBILE INSERIRE L'ABBONAMENTO: LA RIVISTA NON E' DISPONIBILE";
        if(subscriptionSet.add(newSubscription)){
            mag.setDisponibile(Disponibile.NO);
            return "ABBONAMENTO INSERITO CORRETTAMENTE";
        }
        else
            return "IMPOSSIBILE INSERIRE L'ABBONAMENTO: L'ID UTENTE DEVE ESSERE UNICO ";
    }

    private String removeSubscription(){
        Controller controller = Controller.getInstance();
        controller.printMessage("Inserisci id abbonamento:");
        int subId = controller.readInt();
        if(!subscriptionSet.existId(subId))
            return "IMPOSSIBILE DISDIRE L'ABBONAMENTO: L'ID ABBONAMENTO NON ESISTE ";
        Subscription sub = subscriptionSet.getById(subId);
        Magazine mag = magazineSet.getById(sub.getIdRivista());
        mag.setDisponibile(Disponibile.SI);
        subscriptionSet.remove(sub);
        return "ABBONAMENTO DISDETTO CON SUCCESSO";
    }

    private String exportFreeMagazine(){
            if(magazineSet.exportFreeMagToCsv())
                return "ESPORTAZIONE AVVENUTA CON SUCCESSO";
            else
                return "IMPOSSIBILE SCRIVERE SU FILE";
    }

}
