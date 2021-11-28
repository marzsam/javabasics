package org.javabasics.model;

import java.util.Set;

public class RemoveFromSet <E> {
    Boolean result;

    public RemoveFromSet(Set<E> mySet, E removeElement){
        this.result = mySet.remove(removeElement);
    }

    public String getResult(){
        if(result)
            return "Elemento eliminato";
        else
            return "Impossibile rimuovere: ID non esistente";
    }
}
