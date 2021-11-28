package org.javabasics.model;

public class Magazine {
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String tipologia;
    private String disponibile;

    public Magazine(int id, String nome, String descrizione, double prezzo, String tipologia, String disponibile){
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.tipologia = tipologia;
        this.disponibile = disponibile;
    }

    public int getId(){
        return id;
    }

    public boolean isFree(){
        if( disponibile.equals("SI") )
            return true;
        return false;
    }

    public void setBusy(){
        disponibile = "NO";
    }

    public void setFree(){
        disponibile = "SI";
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

    public String toCsvString(String separator){

        return this.id+separator+
               this.nome+separator+
               this.descrizione+separator+
               this.prezzo+separator+
               this.tipologia+separator+
               this.disponibile+separator+
               "\n";
    }

}
