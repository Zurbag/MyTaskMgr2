package sample.DataBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DBConnector {

    private String DRIVER;
    private String URL;
    private String USER_BD;
    private String PASSWORD_BD;

    public String getDRIVER() {
        return DRIVER;
    }

    public String getURL() {
        return URL;
    }

    public String getUSER_BD() {
        return USER_BD;
    }

    public String getPASSWORD_BD() {
        return PASSWORD_BD;
    }

    //Забираю данные из файла access.txt
    Scanner scanner;

    {
        try {
            scanner = new Scanner(new File("../access.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            DRIVER = scanner.nextLine();
            URL = scanner.nextLine();
            USER_BD = scanner.nextLine();
            PASSWORD_BD = scanner.nextLine();
        }
        scanner.close();
    }

}
