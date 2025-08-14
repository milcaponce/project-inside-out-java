package dev.milca;

import java.time.LocalDate;

public class Moment {
    private int id;
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate date;
    private LocalDate creationDate;
    private LocalDate modificationDate;

    public Moment (int id, String title, String description, Emotion emotion, LocalDate date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.date = date;
        this.creationDate = LocalDate.now();
        this.modificationDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.modificationDate = LocalDate.now();
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion (Emotion emotion) {
        this.emotion = emotion;
        this.modificationDate = LocalDate.now();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        this.modificationDate = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", emotion='" + emotion + '\'' +
                ", date=" + date +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}