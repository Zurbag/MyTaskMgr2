package sample;

import sample.dataBase.DBQuery;
import sample.dataBase.DBGetter;
import sample.dataBase.DBTaskSetter;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //ВРЕМЕННАЯ ПРОГРАММА ТОЛЬКО ДЛЯ ПОСТРОЕНИЯ АЛГОРИТМОВ
        Scanner scanner = new Scanner(System.in);
        int go = 0;
        while (true){
            System.out.println("--------------");
            System.out.println("0: Создать задачу");
            System.out.println("1: Вывести список задач");
            System.out.println("2: Показать сегодняшние задачи");
            System.out.println("3: Показать задачи на неделю");
            System.out.println("4: Показать задачу по id");
            System.out.println("5: Изменить задачу по id");
            System.out.println("6: Удалить задачу по id");
            System.out.println("--------------");
            go = scanner.nextInt();

            //Создать задачу
            if(go == 0){
                Scanner scanner1 = new Scanner(System.in);
                String name = scanner1.nextLine();
                String date = scanner1.nextLine();
                new DBTaskSetter().updateDate(new DBQuery().createTask(name,date));
            }//Показать все задачи
            else if (go == 1){
                for (Task x: new DBGetter().getTasks(new DBQuery().getAllTask())
                ) {
                    System.out.print(x.getId()+" ");
                    System.out.print(x.getStatus()+" ");
                    System.out.print(x.getName()+" ");
                    System.out.print(x.getDateCreate()+" ");
                    System.out.print(x.getDateFinish());
                    System.out.println();
                }
                //Показать сегодняшние задачи
            }else if (go == 2){
                for (Task x: new DBGetter().getTasks(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString()))
                ) {
                    System.out.print(x.getId()+" ");
                    System.out.print(x.getStatus()+" ");
                    System.out.print(x.getName()+" ");
                    System.out.print(x.getDateCreate()+" ");
                    System.out.print(x.getDateFinish());
                    System.out.println();
                }
                //Показать задачи на неделю
            }else if (go == 3){
                for (Task x: new DBGetter().getTasks(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString()))//
                ) {
                    System.out.print(x.getId()+" ");
                    System.out.print(x.getName()+" ");
                    System.out.print(x.getDateCreate()+" ");
                    System.out.print(x.getDateFinish());
                    System.out.println();
                }

                //Показать задачу по id
            }else if (go == 4){
                DBGetter recipient = new DBGetter();
                System.out.println("Введите id задачи");
                for (Task x: recipient.getTasks(new DBQuery().getOneTaskForIndex(scanner.nextInt()))//
                ) {
                    System.out.print(x.getId()+" ");
                    System.out.print(x.getName()+" ");
                    System.out.print(x.getDateCreate()+" ");
                    System.out.print(x.getDateFinish());
                    System.out.println();
                }
                //Получить задачу по id и изменить ее
            }else if (go == 5){
                new DBTaskSetter().updateDate(new DBQuery().editTask(55,true,"Измененная задача","2055-09-09"));

                //Удалить данные по id
            }else if (go == 6){
                new DBTaskSetter().updateDate(new DBQuery().deleteTask(91));
            }
        }
    }
}
