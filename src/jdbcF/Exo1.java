package jdbcF;

import java.sql.*;

//CTRL + SHIFT + O pour générer les imports
public class Exo1 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
            String user = "root";
            String passwd = "root";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            Statement state = conn.createStatement();

            ResultSet result = state.executeQuery("SELECT * FROM article");
            ResultSetMetaData resultMeta = result.getMetaData();

            System.out.println("- Il y a " + resultMeta.getColumnCount() + " colonnes dans cette table");
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.println("\t *" + resultMeta.getColumnName(i));

            System.out.println("Voici les article_id et translation_id: ");
            System.out.println("\n---------------------------------");

            while(result.next()){
                System.out.print("\t" + result.getString("article_id") + "\t |");
                System.out.print("\t" + result.getString("translation_id") + "\t |");
                System.out.println("\n---------------------------------");
            }

            result.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}