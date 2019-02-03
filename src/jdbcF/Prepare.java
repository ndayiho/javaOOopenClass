package jdbcF;

import java.sql.*;

//CTRL + SHIFT + O pour générer les imports
public class Prepare {
  public static void main(String[] args) {
    try {
      Connection conn=connectionDatabase();
      Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
         
      //On crée la requête
      String query = "SELECT article_id, name FROM article";
      //Premier trou pour le nom du professeur
      query += " WHERE article_id = ?";
      //Deuxième trou pour l'identifiant du professeur
      query += " OR name = ?";
         
      //On crée l'objet avec la requête en paramètre
      PreparedStatement prepare = conn.prepareStatement(query);
      //On remplace le premier trou par le nom du professeur
      prepare.setInt(1, 4);
      //On remplace le deuxième trou par l'identifiant du professeur
      prepare.setString(2, "honoré");
      //On affiche la requête exécutée
      System.out.println(prepare.toString());
      //On modifie le premier trou
      prepare.setInt(1, 5);
      //On affiche à nouveau la requête exécutée
      System.out.println(prepare.toString());
      //On modifie le deuxième trou
      prepare.setString(2, "Test");
      //On affiche une nouvelle fois la requête exécutée
      System.out.println(prepare.toString());
         
      prepare.close();
      state.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static Connection  connectionDatabase() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
    String user = "root";
    String passwd = "root";
    Connection conn = DriverManager.getConnection(url, user, passwd);
    return conn;
  }
}