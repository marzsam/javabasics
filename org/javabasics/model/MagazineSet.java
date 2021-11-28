package org.javabasics.model;

import java.util.HashSet;

public class MagazineSet extends HashSet<Magazine>{

    public boolean existId(int id){
        for(Magazine mag : this){
            if(mag.getId()==id)
                return true;
        }
        return false;
    }

    public Magazine getByID(int id){
        for(Magazine mag : this){
            if(mag.getId()==id)
                return mag;
        }
        return null;
    }

}
