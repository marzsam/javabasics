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
        List<String> lineList = Files.readAllLines(path);
        
        parsedLineList = new ArrayList<>();

        int iLine = 1;
        while(iLine<lineList.size()){
            String line = lineList.get(iLine);
            String lineToParse = ""+line;
            int iChar = 0;
            while(iChar<line.length()){
                if(line.charAt(iChar)=='"'){
                    iChar++;
                    while(line.charAt(iChar)!='"'){
                        if(iChar == line.length()-1){
                            iChar = 0;
                            iLine++;
                            line = lineList.get(iLine);
                            lineToParse += line;
                        }
                        else{
                            iChar++;
                        }
                    }
                }
                iChar++;
            }

            String[] parsedLine = lineToParse.split(";");
            
            if(parsedLine.length > 0)
                parsedLineList.add(parsedLine);
            iLine++;
        }
        
    }

    protected List<String[]> getParsedLineList(){
        return parsedLineList;
    }


}
