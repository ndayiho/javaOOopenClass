package jdbcF;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exo3 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
            String user = "root";
            String passwd = "root";
            Connection conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            Statement state = conn.createStatement();
            Statement state2 = conn.createStatement();

            String query2 = "select media_id from media_in_folder mf, folder fo where mf.FOLDER_ID=fo.FOLDER_ID and fo.FOLDER_ID in(select " +
                    "folder_id from folder where ARB_SID like 'FNnM7%')";


            String query = "select m.media_id, tv1.value as ean, m.WIDTH as Largeur, m.HEIGHT as Hauteur " +
                    " from media m left join media_field f1 on (f1.MEDIA_ID = m.MEDIA_ID and f1.META_FIELD_ID = 1) " +
                    " left join translation_value tv1 on (tv1.TRANSLATION_ID = f1.TRANSLATION_ID and tv1.ISO2 = 'FR') where m.media_id in (select media_id from media_in_folder mf, folder fo where mf.FOLDER_ID=fo.FOLDER_ID " +
 "and fo.FOLDER_ID in(select folder_id from folder where ARB_SID like 'FNnM7%'))";

            ResultSet mediasISet = state2.executeQuery(query2);
            ResultSet result = state.executeQuery(query);
            List<Integer> listMedias = new ArrayList<>();
            ResultSetMetaData resultMeta = result.getMetaData();
            PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\writejdbc.txt"));
//header
            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                file.print(resultMeta.getColumnName(i) + ";");
            }
            file.print("\n");
            while (result.next()) {
                file.println(result.getString("media_id") + ";" + result.getString("ean") + ";" + result.getString      ("Largeur") + ";" + result.getString("Hauteur"));
            }
            while(mediasISet.next()) {
                listMedias.add(mediasISet.getInt("media_id"));
            }
            file.close();
            result.close();
            mediasISet.close();
            state2.close();
            state.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getResurt(List<Integer> myList){


    }
}