package org.javabasics.model;

import java.util.HashSet;

public class SubscriptionSet extends HashSet<Subscription>{

    public int nextId = 1;

    public boolean existId(int id){
        for(Subscription sub : this){
            if(sub.getId()==id)
                return true;
        }
        return false;
    }

    public Subscription getByID(int id){
        for(Subscription sub : this){
            if(sub.getId()==id)
                return sub;
        }
        return null;
    }

    @Override
    public boolean add(Subscription newSub){
        boolean res = super.add(newSub);
        if(res){
            nextId = newSub.getId() + 1;
            return true;
        }
        return false;
    }
    
    public int getNextId(){
        return nextId;
    }

}
