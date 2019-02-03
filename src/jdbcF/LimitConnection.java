package jdbcF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LimitConnection {
    //Objet Connection
    private static Connection connect;
    private static String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
    private static String user = "root";
    private static String passwd = "root";

    //Constructeur privé
    private LimitConnection() {
        try {
            connect = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}