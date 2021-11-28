package org.javabasics.model;

import java.io.IOException;

public class ImportMagazineFromCsv extends ReadCsv{

    public ImportMagazineFromCsv(MagazineSet magazineSet) throws IOException{
        super("org/javabasics/csv/riviste.csv", ";");

        for(String[] elem : super.getParsedLineList()){
            int id = Integer.parseInt(elem[0]);
            String nome = elem[1];
            String descrizione = elem[2];
            double prezzo = Double.parseDouble(elem[4]);
            String tipologia = elem[3];
            String disponibile = elem[5];

            new AddToSet<Magazine>(magazineSet, new Magazine(id, nome, descrizione, prezzo, tipologia, disponibile));

        }

    }
}
