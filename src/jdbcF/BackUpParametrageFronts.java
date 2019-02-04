package jdbcF;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BackUpParametrageFronts {

    private static String article_tableColumns = "";
    private static String translation_table_columns = "";
    private static String translation_value_table_columns = "";
    private static String ui_tableColumns = "";
    private static Connection connect;
    private static String user = "root";
    private static String passwd = "root";
    private static String url;


    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/kpk360?useSSL=false";
            final List<Integer> instanceIds = new ArrayList<>();
            try (final Connection con = DriverManager.getConnection(url, user, passwd);
                 final Statement statement = con.createStatement()) {
                final String queryString = "select instance_id from instance";
                final ResultSet resultSet = statement.executeQuery(queryString);
                while (resultSet.next()) {
                    instanceIds.add(resultSet.getInt(1));
                }
            }
            final String instanceId = instanceIds.isEmpty() ? "1" : String.valueOf(instanceIds.get(0));
            final String usedDataBase = "kpk360_" + instanceId;
            getResurt(usedDataBase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(String url) {
        if (connect == null) {
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connect;
    }


    public static void getResurt(String instance) throws SQLException, ClassNotFoundException, IOException {
        url = "jdbc:mysql://localhost:3306/" + instance + "?useSSL=false";
        Connection conn = getConnection(url);
        String query = "select * from article";

        PreparedStatement article_state = conn.prepareStatement(query);
        ResultSet result_article = article_state.executeQuery(query);
        ResultSetMetaData resultMeta = result_article.getMetaData();
        PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Gasana.NHonore\\Desktop\\automatisation_front\\globalTest_" + instance + ".sql"));



        file.println("\n\n" + "-------- ****TRANSLATION*******----------" + "\n");

        String query_translation = "select a.translation_id from article a left join translation tr  on(tr.translation_id = a.translation_id);";

        PreparedStatement state_translation = conn.prepareStatement(query_translation);
        ResultSet result_translation = state_translation.executeQuery(query_translation);
        ResultSetMetaData resultMeta_translation = result_translation.getMetaData();
        translation_table_columns = resultMeta_translation.getColumnName(1);
        for (int i = 2; i <= resultMeta_translation.getColumnCount(); i++) {
            translation_table_columns = translation_table_columns.concat("," + resultMeta_translation.getColumnName(i));
        }
        while (result_translation.next()) {
            String translation_Columnvalues = result_translation.getString(resultMeta_translation.getColumnLabel(1));
            for (int i = 2; i <= resultMeta_translation.getColumnCount(); i++) {
                if (result_translation.getString(resultMeta_translation.getColumnLabel(i)) != null) {
//                    if column type is an integer
                    if (resultMeta_translation.getColumnType(i) == 4) {
                        translation_Columnvalues = translation_Columnvalues.concat("," + result_translation.getString(resultMeta_translation.getColumnLabel(i)));
                    } else {
//                        result_translation.getString(resultMeta_translation.getColumnLabel(i).replace("'",hello));
                        translation_Columnvalues = translation_Columnvalues.concat(",'" + result_translation.getString(resultMeta_translation.getColumnLabel(i)) + "'");
                    }

                } else {
                    translation_Columnvalues = translation_Columnvalues.concat(",'" + null);
                }
            }
            file.println("insert ignore into translation(" + translation_table_columns + ")values(" + translation_Columnvalues + ");");
        }


        file.println("\n\n" + "-------- ****TRANSLATION_VALUE*******----------" + "\n");

        String query_translation_value = "select tr.TRANSLATION_ID,ISO2,value from article a , translation_value tr  where (a.TRANSLATION_ID=tr.TRANSLATION_ID);";
        PreparedStatement state_translation_value = conn.prepareStatement(query_translation_value);
        ResultSet result_translation_value = state_translation_value.executeQuery(query_translation_value);
        ResultSetMetaData resultMeta_translation_value = result_translation_value.getMetaData();
        translation_value_table_columns = resultMeta_translation_value.getColumnName(1);
        for (int i = 2; i <= resultMeta_translation_value.getColumnCount(); i++) {
            translation_value_table_columns = translation_value_table_columns.concat("," + resultMeta_translation_value.getColumnName(i));
        }
        while (result_translation_value.next()) {
            String translation_value_Columnvalues = result_translation_value.getString(resultMeta_translation_value.getColumnLabel(1));
            for (int i = 2; i <= resultMeta_translation_value.getColumnCount(); i++) {
                if (result_translation_value.getString(resultMeta_translation_value.getColumnLabel(i)) != null) {
//                    if column type is an integer
                    if (resultMeta_translation_value.getColumnType(i) == 4 || resultMeta_translation_value.getColumnType(i) == -7) {
                        translation_value_Columnvalues = translation_value_Columnvalues.concat("," + result_translation_value.getString(resultMeta_translation_value.getColumnLabel(i)));
                    } else {
                        if(result_translation_value.getString(resultMeta_translation_value.getColumnLabel(i)).contains("'")){

                            result_translation_value.getString(resultMeta_translation_value.getColumnLabel(i)).replace("utilisation","hello");
                        }
                        translation_value_Columnvalues = translation_value_Columnvalues.concat(",'" + result_translation_value.getString(resultMeta_translation_value.getColumnLabel(i)) + "'");
                    }

                } else {
                    translation_value_Columnvalues = translation_value_Columnvalues.concat(",'" + null);
                }
            }
            file.println("insert ignore into translation_value(" + translation_value_table_columns + ")values(" + translation_value_Columnvalues + ");");
        }




        String query_ui= "select *from ui where ui_id >0;";

        PreparedStatement state_ui= conn.prepareStatement(query_ui);
        ResultSet ui_result= state_ui.executeQuery(query_ui);
        ResultSetMetaData ui_resultMeta= ui_result.getMetaData();

        file.println("\n\n" + "-------- ****FRONTS PROFILE(ui)*******----------" + "\n");
        ui_tableColumns = ui_resultMeta.getColumnName(1);
        for (int i = 2; i <= ui_resultMeta.getColumnCount(); i++) {
            ui_tableColumns = ui_tableColumns.concat("," + ui_resultMeta.getColumnName(i));
        }
        while (ui_result.next()) {

            String ui_values = ui_result.getString(ui_resultMeta.getColumnLabel(1));
            for (int i = 2; i <= ui_resultMeta.getColumnCount(); i++) {
                if (ui_result.getString(ui_resultMeta.getColumnLabel(i)) != null) {
//                    if column type is an integer
//                    System.out.println( "le type du colonne "+ ui_resultMeta.getColumnName(i)+"est"+ui_resultMeta.getColumnType(i));
                    if (ui_resultMeta.getColumnType(i) == 4 || ui_resultMeta.getColumnType(i) == -7) {
                        ui_values = ui_values.concat("," + ui_result.getString(ui_resultMeta.getColumnLabel(i)));
                    } else {
                        if (ui_result.getString(ui_resultMeta.getColumnLabel(i)).contains("'"))
                            ui_result.getString(ui_resultMeta.getColumnLabel(i)).replace("'","\'");

                        ui_values = ui_values.concat(",'" + ui_result.getString(ui_resultMeta.getColumnLabel(i)) + "'");
                    }
                } else {
                    ui_values = ui_values.concat("," + null);
                    continue;
                }
            }
            file.println("insert ignore into ui(" + ui_tableColumns + ")values(" + ui_values + ");");
        }



        file.println("\n\n" + "-------- ****ARTICLE*******----------" + "\n");
        article_tableColumns = resultMeta.getColumnName(1);
        for (int i = 2; i <= resultMeta.getColumnCount(); i++) {
            article_tableColumns = article_tableColumns.concat("," + resultMeta.getColumnName(i));
        }
        while (result_article.next()) {

            String article_values = result_article.getString(resultMeta.getColumnLabel(1));
            for (int i = 2; i <= resultMeta.getColumnCount(); i++) {
                if (result_article.getString(resultMeta.getColumnLabel(i)) != null) {
//                    if column type is an integer
//                    System.out.println( "le type du colonne "+ resultMeta.getColumnName(i)+"est"+resultMeta.getColumnType(i));
                    if (resultMeta.getColumnType(i) == 4 || resultMeta.getColumnType(i) == -7) {
                        article_values = article_values.concat("," + result_article.getString(resultMeta.getColumnLabel(i)));
                    } else {
                        article_values = article_values.concat(",'" + result_article.getString(resultMeta.getColumnLabel(i)) + "'");
                    }
                } else {
                    article_values = article_values.concat("," + null);
                    continue;
                }
            }

            file.println("insert ignore into article(" + article_tableColumns + ")values(" + article_values + ");");
        }


        file.close();
        result_translation_value.close();
        state_translation_value.close();
        result_translation.close();
        state_translation.close();
        ui_result.close();
        state_ui.close();
        result_article.close();
        article_state.close();
    }

}
