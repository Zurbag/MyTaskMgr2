package sample;

import javafx.scene.control.Button;

public class Task {
    private int id;
    private Boolean status;
    private String name;
    private String dateCreate;
    private String dateFinish;
    private String project;
    private Button changeStatusBtn;
    private Button editTaskBtn;
    private Button deleteTaskBtn;

    public Task() {
    }

    public Task(int id, Boolean status, String name, String dateCreate, String dateFinish) {
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
}
