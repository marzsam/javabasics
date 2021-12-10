package org.javabasics.model;

public class Magazine {
    
    private Integer id;
    private String nome;
    private String descrizione;
    private String prezzo;
    private String tipologia;
    private Disponibile disponibile;

    public Magazine(Integer id, String nome, String descrizione, String prezzo, String tipologia, Disponibile disponibile){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.tipologia = tipologia;
        this.disponibile = disponibile;
    }

    public Integer getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public String getPrezzo(){
        return prezzo;
    }

    public String getTipologia(){
        return tipologia;
    }

    public String getDisponibileStr(){
        if(disponibile == Disponibile.SI)
            return "SI";
        else
            return "NO";
    }

    public boolean isFree(){
        if(disponibile == Disponibile.SI){
            return true;
        }
        return false;
    }

    public void setDisponibile(Disponibile disponibile){
        this.disponibile = disponibile;
    }

    @Override
    public boolean equals(Object mag){
        if(mag instanceof Magazine && this.id == ((Magazine)mag).getId())
            return true;
        return false;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString(){
        return "RIVISTA ID: "+this.id+
               "\n|-> Nome: "+this.nome+
               "\n|-> Descrizione: "+this.descrizione+
               "\n|-> Prezzo: "+this.prezzo+
               "\n|-> Tipologia: "+this.tipologia+
               "\n|-> Disponibile: "+this.disponibile+
               "\n";
    }

}
