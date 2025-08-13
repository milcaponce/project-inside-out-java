package dev.milca;

import java.util.ArrayList;
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
}
