package org.javabasics.model;

import java.time.LocalDate;

public class Subscription {

    private int id;
    private int idRivista;
    private int idUtente;
    private LocalDate dataInizio;
    private LocalDate dataFine;

    public Subscription(int id, int idRivista, int idUtente, LocalDate dataInizio, LocalDate dataFine){
        this.id = id;
        this.idRivista = idRivista;
        this.idUtente = idUtente;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public int getId(){
        return id;
    }

    @Override
    public int hashCode(){
        return id;
    }

    public int getIdRivista(){
        return idRivista;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof User){
            User usr = (User)obj;
            if(this.getId() == usr.getId())
                return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "ABBONAMENTO ID: "+this.id+
               "\n|-> ID rivista: "+this.idRivista+
               "\n|-> ID utente: "+this.idUtente+
               "\n|-> Data inizio: "+this.dataInizio+
               "\n|-> Data fine: "+this.dataFine+
               "\n";
    }
}
