package dev.milca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MomentManager {
    private List<Moment> moments;

    public MomentManager() {
        this.moments = new ArrayList<>();
    }

    //Agregar un nuevo objeto Moment a la lista
    public void addMoment(Moment moment) {
        if (moment != null) {
            this.moments.add(moment);
            
        }
    }

    //Añadir método getMoments 
    public List<Moment> getMoments() {
        return new ArrayList<>(this.moments);
    }

    //Retorna la lista
    public List<Moment> getOrderedMomentsByDate() {
        List<Moment> orderedList = new ArrayList<>(this.moments);

        Collections.sort(orderedList, Comparator.comparing(Moment::getDate));
        
        return orderedList; 
    }
}
