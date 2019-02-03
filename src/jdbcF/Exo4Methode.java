package jdbcF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Exo4Methode {

    private static List<Integer> listMedias = new ArrayList<>();
    public static void main(String[] args) {
        try {
            getMediaIds();
            getResurt(listMedias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  public static void getMediaIds() throws SQLException,ClassNotFoundException{
      Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
            String user = "root";
            String passwd = "root";
            Connection conn = DriverManager.getConnection(url, user, passwd);
            conn.setAutoCommit(false);
            Statement state2 = conn.createStatement();
            String query2 = "select media_id from media_in_folder mf, folder fo where mf.FOLDER_ID=fo.FOLDER_ID and fo.FOLDER_ID in(select " +
                    "folder_id from folder where ARB_SID like 'FNnM7%')";

            ResultSet mediasISet = state2.executeQuery(query2);
            while(mediasISet.next()) {
                listMedias.add(mediasISet.getInt("media_id"));
            }

            mediasISet.close();
            state2.close();

  }

    public static void getResurt(List<Integer> mediaIds)throws SQLException, ClassNotFoundException,IOException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
        String user = "root";
        String passwd = "root";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        conn.setAutoCommit(false);
        StringBuilder builder = new StringBuilder();
        for( int i = 0 ; i < mediaIds.size(); i++ ) {
            builder.append(mediaIds.get(i)+",");
        }
        System.out.println(builder);
        String query = "select m.media_id, tv1.value as ean, m.WIDTH as Largeur, m.HEIGHT as Hauteur " +
                " from media m left join media_field f1 on (f1.MEDIA_ID = m.MEDIA_ID and f1.META_FIELD_ID = 1) " +
                " left join translation_value tv1 on (tv1.TRANSLATION_ID = f1.TRANSLATION_ID and tv1.ISO2 = 'FR') where m.media_id in ("
                + builder.deleteCharAt( builder.length() -1 ).toString() + ")";

        PreparedStatement state = conn.prepareStatement(query);
//        System.out.println(state.toString());
        ResultSet result = state.executeQuery(query);
        ResultSetMetaData resultMeta = result.getMetaData();
        PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\writejdbcTest.csv"));
//header
        for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
            file.print(resultMeta.getColumnName(1) + ";");
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