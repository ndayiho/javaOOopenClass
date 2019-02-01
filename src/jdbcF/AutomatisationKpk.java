package jdbcF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AutomatisationKpk {

    private static String tableColumns = "";
    private static String tableColumns_translation = "";

    public static void main(String[] args) {
        try {
            getResurt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void getResurt() throws SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/kpk360_5975?useSSL=false";
        String user = "root";
        String passwd = "root";
        Connection conn = DriverManager.getConnection(url, user, passwd);
        String query = "select * from article";

        PreparedStatement state = conn.prepareStatement(query);
        ResultSet result = state.executeQuery(query);
        ResultSetMetaData resultMeta = result.getMetaData();
        PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\automatisation_front\\global.sql"));
        tableColumns = resultMeta.getColumnName(1);
        for (int i = 2; i <= resultMeta.getColumnCount(); i++) {
            tableColumns = tableColumns.concat("," + resultMeta.getColumnName(i));
        }
        while (result.next()) {
            String test = result.getString(resultMeta.getColumnLabel(1));
            for (int i = 2; i <= resultMeta.getColumnCount(); i++) {
                if (result.getString(resultMeta.getColumnLabel(i)) != null) {
//                    if column type is an integer
                    if (resultMeta.getColumnType(i) == 4) {
                        test = test.concat("," + result.getString(resultMeta.getColumnLabel(i)));
                    } else {
                        test = test.concat(",'" + result.getString(resultMeta.getColumnLabel(i)) + "'");
                    }
                } else {
                    continue;
                }
            }
            file.println("insert ignore into article(" + tableColumns + ")values(" + test + ");");
        }

        file.println("\n\n" + "-------- ****TRANSLATION*******----------" + "\n");

        String query_translation = "select a.translation_id from article a left join translation tr  on(tr.translation_id = a.translation_id);";
        PreparedStatement state2 = conn.prepareStatement(query_translation);
        ResultSet result_translation = state2.executeQuery(query_translation);
        ResultSetMetaData resultMeta_translation = result_translation.getMetaData();
        PrintWriter file_translation = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\automatisation_front\\translationTest.sql"));
        tableColumns_translation = resultMeta_translation.getColumnName(1);
        for (int i = 2; i <= resultMeta_translation.getColumnCount(); i++) {
            tableColumns_translation = tableColumns_translation.concat("," + resultMeta_translation.getColumnName(i));
        }
        String hello="test'";
        while (result_translation.next()) {
            String test2 = result_translation.getString(resultMeta_translation.getColumnLabel(1));
            for (int i = 2; i <= resultMeta_translation.getColumnCount(); i++) {
                if (result_translation.getString(resultMeta_translation.getColumnLabel(i)) != null) {
//                    if column type is an integer
                    if (resultMeta_translation.getColumnType(i) == 4) {
                        test2 = test2.concat("," + result_translation.getString(resultMeta_translation.getColumnLabel(i)));
                    } else {
//                        result_translation.getString(resultMeta_translation.getColumnLabel(i).replace("'",hello));
                        test2 = test2.concat(",'" + result_translation.getString(resultMeta_translation.getColumnLabel(i)) + "'");
                    }

                } else {
                    continue;
                }
            }
            file.println("insert ignore into translation(" + tableColumns_translation + ")values(" + test2 + ");");
        }
        file.close();
        result.close();
        state.close();
        file_translation.close();
        result_translation.close();
        state2.close();
    }
}