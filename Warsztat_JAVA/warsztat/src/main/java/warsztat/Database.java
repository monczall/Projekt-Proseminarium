package warsztat;

import java.sql.*;

public class Database
{

    public static Connection getConnection() throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            //  "jdbc:postgresql://nazwahosta:port/
            //   nazwabazy","uzytkownik", "haslo"
            connection = DriverManager.getConnection("jdbc:postgresql://dbservice:5432" +
                    "/Warsztat1","postgres", "admin");
            String selectTableSQL = "SELECT \"login\", \"password\"\n" +
                    "\tFROM public.\"Users\";;";
            statement = connection.createStatement();

            System.out.println(selectTableSQL);

            // wykonywanie SQL
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String d = rs.getString(1);
                String dd = rs.getString(2);

                System.out.println(d + " " + dd);


            }
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.out.append("Nie masz sterownika");
        }
        catch (SQLException e )
        {
            e.printStackTrace();
            System.out.append("Zle dane");
        }

        return connection;
    }
    private static final String bankCode = "87654321";
    private static final String bankCountry = "PL";


    public static String getBankCode() {
        return bankCode;
    }

    public static String getBankCountry() {
        return bankCountry;
    }
}