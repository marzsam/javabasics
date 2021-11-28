package org.javabasics.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImportSubscriptionFromCsv extends ReadCsv{
    public ImportSubscriptionFromCsv(SubscriptionSet subSet) throws IOException{
        super("org/javabasics/csv/abbonamenti.csv", ";");
        for(String[] elem : super.getParsedLineList()){
            int id = Integer.parseInt(elem[0]);
            int idRivista = Integer.parseInt(elem[1]);
            int idUtente = Integer.parseInt(elem[2]);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataInizio = LocalDate.parse(elem[3], formatter);
            LocalDate dataFine = LocalDate.parse(elem[4], formatter);;

            new AddToSet<Subscription>(subSet, new Subscription(id, idRivista, idUtente, dataInizio, dataFine));

        }

    }
    
}

