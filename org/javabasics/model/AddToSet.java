package org.javabasics.model;

import java.util.Set;

public class AddToSet <E>{
    
    Boolean result;

    public AddToSet(Set<E> mySet, E newElement){
        this.result = mySet.add(newElement);
    }

    public String getResult(){
        if(result)
            return "Inserimento effettuato";
        else
            return "Impossibile inserire: ID già presente";
    }


}