package jdbcF;

import java.sql.*;

public class UpdateData {


    public static void main(String[] args) {
        try {

            Connection conn = connectionDatabase();
            conn.setAutoCommit(false);
            //On autorise la mise à jour des données
            //Et la mise à jour de l'affichage
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //On va chercher une ligne dans la base de données
            String query = "SELECT * FROM article " + "WHERE article_id= 4";
            ResultSet res = state.executeQuery(query);
           String updateQuery="UPDATE article set name = ? "+"WHERE article_id = 4";
           PreparedStatement prep=conn.prepareStatement(updateQuery);
            prep.setString(1, "Mabuso");
           prep.executeUpdate();

            res.close();
            state.close();
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Connection connectionDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
        String user = "root";
        String passwd = "root";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        return conn;
    }
}
