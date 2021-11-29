package org.javabasics.service;

import java.io.IOException;
import java.time.LocalDate;

import org.javabasics.model.*;

public class Service {
    
    private static Service service;
    
    private UserSet userSet;
    private MagazineSet magazineSet;
    private SubscriptionSet subscritionSet;
    
    private Service(){ 
        this.userSet = new UserSet();
        this.magazineSet = new MagazineSet();
        this.subscritionSet = new SubscriptionSet();
        initializeSets();
    }

    public static Service getInstance(){
        if(service == null)
            service = new Service();
        return service;
    }

    public String insertUser(User newUser){
        return (new AddToSet<User>(userSet, newUser)).getResult();
    }

    public String showUser(){
        return (new ShowSet<User>(userSet)).getResult();
    }

    public String showSubscription(){
        return (new ShowSet<Subscription>(subscritionSet)).getResult();
    }

    public String showMagazine(){
        return (new ShowSet<Magazine>(magazineSet)).getResult();
    }

    public String addSubscrition(int magazineId, int userId){
        
        if(!magazineSet.existId(magazineId) || !userSet.existId(userId)){
            return("Errore: l'id dell'utente o quello della rivista non esistono");
        }

        Magazine mag = magazineSet.getByID(magazineId);
        if(!magazineSet.getByID(magazineId).isFree()){
            return("Errore: la rivista non è disponibile");
        }
        mag.setBusy();

        int id = subscritionSet.getNextId();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(365);

        Subscription newSubscrition = new Subscription(id, magazineId, userId, startDate, endDate);

        return (new AddToSet<Subscription>(subscritionSet, newSubscrition)).getResult();
    }

    public String removeSubscrition(int subId){
        if(!subscritionSet.existId(subId))
            return "Errore: l'abbonamento inserito non esiste";
        
        Subscription sub = subscritionSet.getByID(subId);
        
        magazineSet.getByID(sub.getIdRivista()).setFree();

        return (new RemoveFromSet<Subscription>(subscritionSet, sub)).getResult();
    }

    public String exportFreeMagazine(){
        try{
            new ExportFreeMagazine(magazineSet);
            return "esportazione completata";
        }
        catch(IOException e){
            return "impossibile esportare "+e.getMessage();
        }
    }

    public void initializeSets(){
        try{
            new ImportMagazineFromCsv(magazineSet);
            new ImportSubscriptionFromCsv(subscritionSet);
            new ImportUserFromCsv(userSet);
        }
        catch(IOException e){
            System.out.print(e.getMessage());
            return;
        }
    }
}
