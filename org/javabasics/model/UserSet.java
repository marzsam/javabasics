package org.javabasics.model;

import java.util.HashSet;

public class UserSet extends HashSet<User> {

    public boolean existId(int id){
        for(User mag : this){
            if(mag.getId()==id)
                return true;
        }
        return false;
    }

}
