package org.javabasics.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv {

    private List<String[]> parsedLineList;
    
    public ReadCsv(String strPath, String separator) throws IOException{
        Path path = Paths.get(strPath);
        List<String> linesList = Files.readAllLines(path);
        parsedLineList = new ArrayList<>();
        for(int i=1; i<linesList.size(); i++)
            parsedLineList.add( linesList.get(i).split(separator) );
    }

    protected List<String[]> getParsedLineList(){
        return parsedLineList;
    }

}
