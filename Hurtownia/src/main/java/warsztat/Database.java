package warsztat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://dbservice:5432" +
                    "/Hurtownia2","postgres", "admin");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
