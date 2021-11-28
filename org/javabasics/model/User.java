package org.javabasics.model;

import java.time.LocalDate;

public class User{
    private int id;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String indirizzo;
    private String documentoId;

    public User(int id, String nome, String cognome, LocalDate dataDiNascita, String indirizzo, String documentoId){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
        this.documentoId = documentoId;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof User){
            User usr = (User)obj;
            if(id == usr.getId())
                return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "UTENTE ID: "+this.id+
               "\n|-> Nome: "+this.nome+
               "\n|-> Cognome: "+this.cognome+
               "\n|-> Data di nascita: "+this.dataDiNascita+
               "\n|-> Indirizzo: "+this.indirizzo+
               "\n|-> Documento ID: "+this.documentoId+
               "\n";
    }
}
