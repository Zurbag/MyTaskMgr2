package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField TextFieldForNewTask;

    @FXML
    private Button addNewTaskId;

    @FXML
    private TableView<Task> tableShowTasks;

    @FXML
    private TableColumn<Task, Boolean> setStatus;

    @FXML
    private TableColumn<Task, Integer> id;

    @FXML
    private TableColumn<Task, String> name;

    @FXML
    private TableColumn<Task, String> dateCreate;

    @FXML
    private TableColumn<Task, String> dateFinish;

    @FXML
    private Button todayTasksId;

    @FXML
    private Button weekTasksId;

    @FXML
    private Button laterTasksId;

    @FXML
    private DatePicker setNewTaskDataFinishId;

    @FXML
    private Button deleteTaskId;

    @FXML
    private Button showAllTasksId;

    @FXML
    private Button editTaskId;

    //Тут храняться задачи
    public ObservableList<Task> taskListForTable = FXCollections.observableArrayList();
    //Переменная для храннения ID выбраной задачи используется при удалении
    private int idTask;
    //Пременная для хранния выбраной даты
    //Тут будет храниться дата крайнего срока задачи
    String dateOfNewTask;

    @FXML
    private void initialize() {
        //Инициализация полей таблицы
        setStatus.setCellValueFactory(new PropertyValueFactory<Task, Boolean>("status"));
        id.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        dateCreate.setCellValueFactory(new PropertyValueFactory<Task, String>("dateCreate"));
        dateFinish.setCellValueFactory(new PropertyValueFactory<Task, String>("dateFinish"));

        // заполняем данными
        // по умолчанию это задачи на сегодняшний день
        //Создаем обьект который иницализирует запрос, вызываем у него метод на получение данных, с помошью BDQurry выбираем нужный запрос
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
        // добавляем данные в таблицу
        tableShowTasks.setItems(taskListForTable);
        // Получение данных из таблицы в переменную idTask;
        TableView.TableViewSelectionModel<Task> selectionModel = tableShowTasks.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Task>() {

            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if (t1 != null) idTask = t1.getId();
            }
        });



    }

    //Метод позвоялющий заполнить таблицу данными из базы данны
    private void setTaskListForTable(ArrayList<Task> tasksFromBD){
        for (Task x:tasksFromBD
        ) {
            taskListForTable.add(x);
        }
    }

    @FXML
    void addNewTaskBtn(ActionEvent event) {
        new TaskCreator().createTask(TextFieldForNewTask.getText(),dateOfNewTask);
    }

    //Удаление задачи по id
    @FXML
    void deleteTaskBtn(ActionEvent event) {
        new TaskRemover().removeTask(idTask);
    }

    @FXML
    void editTaskBtn(ActionEvent event) {

    }

    @FXML
    void setDateOfNewTask(ActionEvent event) {
        dateOfNewTask = setNewTaskDataFinishId.getValue().toString();
    }


    //Показать все задачи в том числе завершенные
    @FXML
    void showAllTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTask()));
    }

    //Показать все дадачи без завершенных
    @FXML
    void showLaterTaskBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
    }

    //Показать сегодняшние задачи
    @FXML
    void showTodayTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
    }

    //Показать задачи на неделю
    @FXML
    void showWeekTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString())));
    }

}
