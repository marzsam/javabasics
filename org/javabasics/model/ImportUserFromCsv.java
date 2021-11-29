package org.javabasics.model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ImportUserFromCsv extends ReadCsv{
    public ImportUserFromCsv(UserSet userSet) throws IOException{
        super("org/javabasics/csv/utenti.csv", ";");
        for(String[] elem : super.getParsedLineList()){
            
            int id = Integer.parseInt(elem[0]);
            String nome = elem[1];
            String cognome = elem[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataDiNascita = LocalDate.parse(elem[3], formatter);
            String indirizzo = elem[4];
            String documentoId = elem[5];

            new AddToSet<User>(userSet, new User(id, nome, cognome, dataDiNascita, indirizzo, documentoId));
        }
    }    
}
