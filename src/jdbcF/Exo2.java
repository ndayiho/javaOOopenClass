package jdbcF;

import java.sql.*;
import java.util.List;

public class Exo2 {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
            String user = "root";
            String passwd = "root";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            Statement state = conn.createStatement();
            Statement state2 = conn.createStatement();

            String query = "select  tr.TRANSLATION_ID,INTERNAL_NAME,VALUE from meta_field m , translation_value tr,metamodel_field mt ";
            query += " where tr.TRANSLATION_ID= m.TRANSLATION_ID and m.META_FIELD_ID=mt.META_FIELD_ID ";

            String query2 = "select media_id from media_in_folder mf, folder fo where mf.FOLDER_ID=fo.FOLDER_ID and fo.FOLDER_ID in(select folder_id from folder where ARB_SID like 'FNnM7%')";


            ResultSet result = state.executeQuery(query);
                ResultSet mediasISet = state2.executeQuery(query2);
//            List<Object[]> results = mediasISet.;

            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            for (int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

            System.out.println("\n**********************************");

            while (result.next()) {
                for (int i = 1; i <= resultMeta.getColumnCount(); i++)
                    System.out.print("\t" + result.getObject(i).toString() + "\t |");

                System.out.println("\n---------------------------------");

            }

            result.close();
            state.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}