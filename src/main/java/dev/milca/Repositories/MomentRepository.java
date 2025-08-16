package dev.milca.Repositories;

import dev.milca.mvc.model.EmotionEnum;
import dev.milca.mvc.model.Moment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MomentRepository {
    private List<Moment> moments;
    private int nextId;

    public MomentRepository() {
        this.moments = new ArrayList<>();
        this.nextId = 1;
    }

    //Agregar un nuevo objeto Moment a la lista
    public void addMoment(Moment moment) {
        if (moment != null) {
            moment.setId(nextId++);
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

    //Buscar el moment por su ID
    public Moment getMomentById(int id) {
        for (Moment moment : this.moments) {
            if (moment.getId() == id) {
                return moment;
            }
        }
        return null; 
    }

    //Elimina un moment por su Id
    public boolean deleteMomentById(int id) {
        Moment momentToDelete = getMomentById(id);
        if (momentToDelete != null) {
            return this.moments.remove(momentToDelete);
        }
        return false;
    }

    //Filtrar la lista
    public List<Moment> filterMomentsByEmotion(String emotion) {
        List<Moment> filteredList = new ArrayList<>();

        EmotionEnum searchEmotion = EmotionEnum.valueOf(emotion.toUpperCase());

        for (Moment moment : this.moments) {
            if (moment.getEmotion().equals(searchEmotion)) {
                filteredList.add(moment);
            }
        }
        return filteredList;
    }
}
