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
    
    public ObservableList<Task> taskListForTable = FXCollections.observableArrayList(); //Тут храняться задачи
    private int idTask; //Переменная для храннения ID выбраной задачи используется при удалении
    String dateOfNewTask;  //Тут будет храниться дата крайнего срока задачи
    TypeOfDisplayedTasks typeOfDisplayedTasks; //Это перечисления для хранения типа покзываемых задач, нужно для отображения задач после удаления

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
        typeOfDisplayedTasks = TypeOfDisplayedTasks.TODAY;
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
    //Метод который перерисовывает таблицу после удаления или добавления задач
    private void refreshTable(TypeOfDisplayedTasks typeOfDisplayedTasks){
        switch (typeOfDisplayedTasks){
            case TODAY -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
            }case WEEK -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString())));
            }case LATER -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
            }case ALL -> {
                setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
            }
        }
    };

    //Установить дану новой задачи из дата пикера
    @FXML
    void setDateOfNewTask(ActionEvent event) {
        dateOfNewTask = setNewTaskDataFinishId.getValue().toString();
    }

    //После нажатия этой кнопки добавляется новая задача
    @FXML
    void addNewTaskBtn(ActionEvent event) {
        new TaskCreator().createTask(TextFieldForNewTask.getText(),dateOfNewTask);
    }

    //Показать сегодняшние задачи
    @FXML
    void showTodayTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.TODAY;
    }

    //Показать задачи на неделю
    @FXML
    void showWeekTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString())));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.WEEK;
    }

    //Показать все дадачи без завершенных LATER
    @FXML
    void showLaterTaskBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.LATER;
    }
    //Показать все задачи в том числе завершенные
    @FXML
    void showAllTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGeter().getData(new DBQuery().getAllTask()));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.ALL;

    }

    //Удаление задачи по id
    @FXML
    void deleteTaskBtn(ActionEvent event) {
        new TaskRemover().removeTask(idTask);
        refreshTable(typeOfDisplayedTasks);
    }

    //Редактирование задачи получить по id задачу затем передать ее в окно, отредактировать и сохранить
    @FXML
    void editTaskBtn(ActionEvent event) {

    }
}
