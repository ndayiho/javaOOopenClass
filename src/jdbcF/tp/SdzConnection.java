package jdbcF.tp;
import javax.swing.*;
import java.sql.*;
public class SdzConnection {
    //Objet Connection
    private static Connection connect;

    private static String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
    private static String user = "root";
    private static String passwd = "root";

    /**
     * Méthode qui va retourner notre instance
     * et la créer si elle n'existe pas...
     * @return
     */
    public static Connection getInstance(){
        if(connect == null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connect;
    }
}
