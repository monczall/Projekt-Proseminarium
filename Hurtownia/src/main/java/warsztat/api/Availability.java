package warsztat.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import warsztat.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@RestController
public class Availability {

    @GetMapping(value = "/availability", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String showExternalTransfers() {
        String json = "";
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT \"ID\", \"Nazwa\", \"Ilosc\"\n" +
                    "\tFROM public.\"Czesc\" ORDER BY \"ID\";");
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject part = new JSONObject();
                part.put("id", rs.getInt(1));
                part.put("nazwa", rs.getString(2));
                part.put("ilosc", rs.getInt(3));

                array.put(part);
            }
            jsonObject.put("Dostepnosc", array);
            json = jsonObject.toString();

        } catch (SQLException | JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}

