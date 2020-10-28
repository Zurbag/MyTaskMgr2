package sample;

import javafx.scene.control.Button;

public class Project {
    private int id;
    private Boolean status;
    private String name;
    private String dateCreate;
    private String dateFinish;
    private int tack_id;
    private String description;

    public Project() {
    }

    public Project(int id, Boolean status, String name, String dateCreate, String dateFinish) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.dateCreate = dateCreate;
        this.dateFinish = dateFinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getTack_id() {
        return tack_id;
    }

    public void setTack_id(int tack_id) {
        this.tack_id = tack_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
