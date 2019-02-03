package jdbcF;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        try {
            //Nous appelons quatre fois la méthode getInstance()
            PreparedStatement prepare = LimitConnection.getInstance().prepareStatement("SELECT * FROM classe WHERE cls_nom = ?");

            Statement state = LimitConnection.getInstance().createStatement();

            LimitConnection.getInstance().setAutoCommit(false);

            DatabaseMetaData meta = LimitConnection.getInstance().getMetaData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
