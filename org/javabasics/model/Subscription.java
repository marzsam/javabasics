package org.javabasics.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public int getIdRivista(){
        return idRivista;
    }

    public int getIdUtente(){
        return idUtente;
    }

    @Override
    public boolean equals(Object sub){
        if(sub instanceof Subscription && this.id == ((Subscription)sub).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "ABBONAMENTO ID: "+this.id+
               "\n|-> ID rivista: "+this.idRivista+
               "\n|-> ID utente: "+this.idUtente+
               "\n|-> Data inizio: "+this.dataInizio.format(formatter)+
               "\n|-> Data fine: "+this.dataFine.format(formatter)+
               "\n";
    }
}
