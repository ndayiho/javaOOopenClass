package jdbcF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomatisationKpk {

    private static List<Integer> listMedias = new ArrayList<>();
    public static void main(String[] args) {
        try {
//            getMediaIds();
            getResurt(listMedias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getResurt(List<Integer> mediaIds)throws SQLException, ClassNotFoundException,IOException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kpk360_74?useSSL=false";
        String user = "root";
        String passwd = "root";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        StringBuilder builder = new StringBuilder();
        for( int i = 0 ; i < mediaIds.size(); i++ ) {
            builder.append(mediaIds.get(i)+",");
        }
        String query = "select * from article";

        PreparedStatement state = conn.prepareStatement(query);
//        System.out.println(state.toString());
        ResultSet result = state.executeQuery(query);
        ResultSetMetaData resultMeta = result.getMetaData();
        PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\Automatisation _front\\writejdbcTest.csv"));
        for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
            file.print(resultMeta.getColumnName(i) + ";");
        }
        file.print("\n");
        while (result.next()) {
            file.println(result.getString("media_id") + ";" + result.getString("ean") + ";" + result.getString      ("Largeur") + ";" + result.getString("Hauteur"));
        }
        file.close();
        result.close();
        state.close();
    }

}