package org.javabasics.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import org.javabasics.controller.Controller;

public class SubscriptionSet{

    private int nextId = 1;
    private HashSet<Subscription> subscriptionSet;

    public SubscriptionSet(){
        subscriptionSet = new HashSet<>();
    }

    public int getNextId(){
        return nextId;
    }

    public boolean add(Subscription sub){
        if(subscriptionSet.add(sub)){ 
            if(sub.getId()>=nextId)
                nextId = sub.getId()+1;
            return true;
        }
        return false;
    }

    public boolean existId(int id){
        for(Subscription sub : subscriptionSet)
            if(sub.getId() == id)
                return true;
        return false;
    }

    public Subscription getById(int id){
        for(Subscription sub : subscriptionSet){
            if(sub.getId()==id)
                return sub;
        }
        return null;
    }

    public void remove(Subscription sub){
        subscriptionSet.remove(sub);
    }

    public void importFromCsv(String file){
        Csv csv = new Csv(file);
        ArrayList<String[]> csvList = csv.getCsvLineList();
        for(String[] line : csvList){
            try{
                int id = Integer.parseInt(line[0]);
                int idRivista = Integer.parseInt(line[1]);
                int idUtente = Integer.parseInt(line[2]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataInizio = LocalDate.parse(line[3], formatter);
                LocalDate dataFine = LocalDate.parse(line[4], formatter);
                
                Subscription sub = new Subscription(id, idRivista, idUtente, dataInizio, dataFine);
                if(!this.add(sub))
                    Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE ABBONAMENTO: ID GIA' PRESENTE");;
            }
            catch(NumberFormatException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE ABBONAMENTO: ID NON VALIDO");
            }
            catch(DateTimeParseException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE ABBONAMENTO: DATA NON VALIDA");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(Subscription sub : subscriptionSet){
            str+=sub.toString();
            str+="\n";
        }
        return str;
    }
    
}
