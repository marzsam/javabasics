package org.javabasics.model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class ExportFreeMagazine{
    
    public ExportFreeMagazine(MagazineSet magSet) throws IOException{
        
        LocalDate ld = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");

        String fileName = "riviste_"+ld.format(formatter)+".csv";

        Path path = Paths.get(fileName);
        String toWrite = "ID;Nome;Descrizione;Prezzo;Tipologia;Disponibile\n";
        for(Magazine mag : magSet){
            if(mag.isFree())
                toWrite += mag.toCsvString(";");
        }
        byte[] strToBytes = toWrite.getBytes();
        Files.write(path, strToBytes);
    }
}
