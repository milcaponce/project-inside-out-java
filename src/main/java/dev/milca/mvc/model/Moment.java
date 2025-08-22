package dev.milca.mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Moment {
    private int id;
    private String title;
    private String description;
    private EmotionEnum emotion;
    private MomentTypeEnum momentType;
    private LocalDate momentDate;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

    public Moment (String title, String description, EmotionEnum emotion, MomentTypeEnum momentType, LocalDate momentDate) {
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentType = momentType;
        this.momentDate = momentDate;
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }

    public Moment(int id, String title, String description, EmotionEnum emotion, MomentTypeEnum momentType, LocalDate momentDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentType = momentType;
        this.momentDate = momentDate;
        this.creationDate = LocalDateTime.now();
        this.modificationDate = LocalDateTime.now();
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EmotionEnum getEmotion() {
        return emotion;
    }
        
    public MomentTypeEnum getMomentType() {
        return momentType;
    }
    
    public LocalDate getMomentDate() {
        return momentDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
        this.modificationDate = LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.modificationDate = LocalDateTime.now();
    }

    public void setEmotion(EmotionEnum emotion) {
        this.emotion = emotion;
        this.modificationDate = LocalDateTime.now();
    }

    public void setMomentType(MomentTypeEnum momentType) {
        this.momentType = momentType;
        this.modificationDate = LocalDateTime.now();
    }
    
    public void setMomentDate(LocalDate momentDate) {
        this.momentDate = momentDate;
        this.modificationDate = LocalDateTime.now();
    }



    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", emotion='" + emotion + '\'' +
                ",momentType=" + momentType +
                ", momentDate=" + momentDate +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}