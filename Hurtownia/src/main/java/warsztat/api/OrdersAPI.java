package warsztat.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import warsztat.Database;

import java.sql.*;
import java.time.LocalDate;

@Controller
public class OrdersAPI {

    @GetMapping("/ordersAPI")
    public void getOrders() throws JSONException {

        Connection connection = Database.getConnection();
        PreparedStatement pstmt;
        int generatedId = 0;
        LocalDate date = java.time.LocalDate.now();

        try {
            pstmt = connection.prepareStatement("INSERT INTO public.\"Sprzedaz\"(\n" +
                            "\t\"ID_Warsztatu\", \"Data_sprzedazy\")\n" +
                            "\tVALUES (1, ?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setDate(1, java.sql.Date.valueOf(date));
            pstmt.execute();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                generatedId = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://10.1.0.210:8080/order_parts", String.class);
        JSONObject json = new JSONObject(response);
        JSONArray ja = json.getJSONArray("Zamowienie");
        for (int i = 0; i < ja.length(); i++) {
            JSONObject ob = ja.getJSONObject(i);

            int idCzesci = ob.getInt("id_czesci");
            int ilosc = ob.getInt("ilosc");

            try {
                pstmt = connection.prepareStatement("INSERT INTO public.\"Szczegoly_sprzedazy\"(\n" +
                        "\t\"ID_sprzedazy\", \"ID_czesci\", \"Ilosc\")\n" +
                        "\tVALUES (?, ?, ?);");
                pstmt.setInt(1, generatedId);
                pstmt.setInt(2, idCzesci);
                pstmt.setInt(3, ilosc);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
