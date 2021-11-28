package org.javabasics.model;

import java.util.Set;

public class ShowSet <E>{
    
    String result = "";

    public ShowSet(Set<E> mySet){
        for(Object obj : mySet){
            result += obj.toString();
            result += "\n";
        }
    }

    public String getResult(){
        return result;
    }


}
