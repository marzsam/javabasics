package org.javabasics.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import org.javabasics.controller.Controller;

public class MagazineSet{

    private HashSet<Magazine> magazineSet;

    public MagazineSet(){
        magazineSet = new HashSet<>();
    }
    
    public boolean add(Magazine mag){
        return magazineSet.add(mag);
    }

    public boolean existId(int id){
        for(Magazine mag : magazineSet)
            if(mag.getId() == id)
                return true;
        return false;
    }

    public Magazine getById(int id){
        for(Magazine mag : magazineSet){
            if(mag.getId()==id)
                return mag;
        }
        return null;
    }

    public boolean exportFreeMagToCsv(){
        String[] name = {"ID","Nome","Descrizione","Tipologia","Prezzo","Disponibile"};
        ArrayList<String[]> value = new ArrayList<>();
        for(Magazine mag : magazineSet){
            if(mag.isFree()){
                String[] valueArray = {
                                        mag.getId().toString(),
                                        mag.getNome(),
                                        mag.getDescrizione(),
                                        mag.getTipologia(),
                                        mag.getPrezzo(),
                                        mag.getDisponibileStr(),
                                      };
                value.add(valueArray);
            }
        }
        LocalDate ld = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");

        String fileName = "riviste_"+ld.format(formatter)+".csv";
        Csv csv = new Csv(fileName);
        try{
            csv.write(name, value, ';');
            return true;
        }
        catch(IOException e){
            return false;
        } 
    }

    public void importFromCsv(String file){
        Csv csv = new Csv(file);
        ArrayList<String[]> csvList = csv.getCsvLineList();
        for(String[] line : csvList){
            try{
                int id = Integer.parseInt(line[0]);
                String nome = line[1];
                String descrizione = line[2];
                String tipologia = line[3];
                String prezzo = line[4];
                Disponibile disponibile;
                if(line[5].equals("SI"))
                    disponibile = Disponibile.SI;
                else
                    disponibile = Disponibile.NO;
                Magazine mag = new Magazine(id, nome, descrizione, tipologia, prezzo, disponibile);
                if(!this.add(mag))
                    Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE RIVISTA: ID GIA' PRESENTE");;
            }
            catch(NumberFormatException e){
                Controller.getInstance().printMessage("IMPOSSIBILE IMPORTARE RIVISTA: ID NON VALIDO");
            }
        }
    }

    @Override
    public String toString(){
        String str = "";
        for(Magazine mag : magazineSet){
            str+=mag.toString();
            str+="\n";
        }
        return str;
    }
    
}
