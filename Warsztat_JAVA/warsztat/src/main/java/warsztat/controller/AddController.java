package warsztat.controller;

import org.springframework.web.bind.annotation.*;
import warsztat.Database;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.sql.*;

@RestController
public class AddController {

    @CrossOrigin(origins = "http://localhost")
    @GetMapping("/add")
    public void  showIndex() {


        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://10.1.0.200/naprawson.php", String.class);
        JSONObject json = new JSONObject(response);
        JSONArray ja = json.getJSONArray("Naprawa");
        JSONObject ob = ja.getJSONObject(0);



        String marka = ob.getString("marka");
        String model = ob.getString("model");
        int rok_produkcji = ob.getInt("rok_produkcji");
        String id_uzytkownika = ob.getString("id_uzytkownika");
        String data_przyjecia_naprawy = ob.getString("data_przyjecia_naprawy");

        String planowany_poczatek_naprawy = ob.getString("planowany_poczatek_naprawy");
        String planowany_koniec_naprawy = ob.getString("planowany_koniec_naprawy");

        String opis_usterki = ob.getString("opis_usterki");

        int generatedID = 0;
        int generatedKey = 0;

        try{

            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO public.\"Samochody\" (marka, model, rok_produkcji, id_uzytkownika) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, marka);
            pstmt.setString(2, model);
            pstmt.setInt(3, rok_produkcji);
            pstmt.setString(4, id_uzytkownika);

            pstmt.execute();

            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                generatedKey = rs.getInt(1);

            }

            int wpisane_id = generatedKey;
            System.out.println(wpisane_id + " wpisane id");



            PreparedStatement pstmtt = conn.prepareStatement("INSERT INTO public.\"Naprawy\" (id_uzytkownika, id_samochodu, data_przyjecia_naprawy, opis_usterki, opis_naprawy, stan_naprawy, czy_potrzebne_czesci) VALUES (?,?,?,?, '', 'Przyjęto zlecenie', FALSE);", Statement.RETURN_GENERATED_KEYS);
            pstmtt.setString(1, id_uzytkownika);
            pstmtt.setInt(2, wpisane_id);
            pstmtt.setString(3, data_przyjecia_naprawy);
            pstmtt.setString(4, opis_usterki);

            pstmtt.execute();

            ResultSet rst = pstmtt.getGeneratedKeys();

            while (rst.next()) {
                generatedID = rst.getInt(1);

            }

            int wygenerowane_id = generatedID;
            System.out.println(wygenerowane_id + " wygenerowane id");

            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO public.\"Czas_naprawy\"(id_naprawy, planowany_poczatek_naprawy, planowany_koniec_naprawy) VALUES (?, ?, ?);");
            pstmt2.setInt(1, wygenerowane_id);
            pstmt2.setString(2, planowany_poczatek_naprawy);
            pstmt2.setString(3, planowany_koniec_naprawy);


            pstmt2.execute();


        }catch(SQLException e){
            e.printStackTrace();
        }







    }
    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/terminy", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String showTerminy() {
        String json = "";

        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT planowany_poczatek_naprawy, planowany_koniec_naprawy FROM public.\"Czas_naprawy\";");

            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject account = new JSONObject();
                account.put("start", rs.getString(1));
                account.put("end", rs.getString(2));
                array.put(account);
            }
            jsonObject.put("Terminy_napraw", array);
            json = jsonObject.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return json;
    }

    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/naprawy", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String showNaprawy() {
        String json = "";

        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Naprawy\" n INNER JOIN public.\"Samochody\" s ON n.id_samochodu = s.id INNER JOIN public.\"Czas_naprawy\" c ON n.id = c.id_naprawy;");

            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject account = new JSONObject();
                account.put("id_uzytkownika", rs.getString(13));
                account.put("opis_usterki", rs.getString(3));
                account.put("opis_naprawy", rs.getString(4));
                account.put("stan_naprawy", rs.getString(5));
                account.put("data_przyjecia", rs.getString(7));
                account.put("marka", rs.getString(10));
                account.put("model", rs.getString(11));
                account.put("rok_produkcji", rs.getString(12));
                account.put("poczatek", rs.getString(16));
                account.put("koniec", rs.getString(17));

                array.put(account);
            }
            jsonObject.put("Naprawy", array);
            json = jsonObject.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return json;
    }
    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/order_parts", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String showOrder() {
        String json = "";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT ilosc, id_czesci, nazwa FROM public.\"Wymagane_czesci\" WHERE hurtownia_id = 1;");
            //naprawa.setInt(1, id_naprawy);
            ResultSet rs = naprawa.executeQuery();
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject account = new JSONObject();
                account.put("ilosc", rs.getInt(1));
                account.put("id_czesci", rs.getInt(2));
                account.put("nazwa", rs.getString(3));
                array.put(account);
            }
            jsonObject.put("Zamowienie", array);
            json = jsonObject.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return json;



    }


    @CrossOrigin(origins = "http://localhost")
    @GetMapping(value = "/order_parts1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public String showOrder1() {
        String json = "";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT ilosc, id_czesci, nazwa FROM public.\"Wymagane_czesci\" WHERE hurtownia_id = 2;");
            //naprawa.setInt(1, id_naprawy);
            ResultSet rs = naprawa.executeQuery();
            JSONObject jsonObject = new JSONObject();
            JSONArray array = new JSONArray();
            while (rs.next()) {
                JSONObject account = new JSONObject();
                account.put("ilosc", rs.getInt(1));
                account.put("id_czesci", rs.getInt(2));
                account.put("nazwa", rs.getString(3));
                array.put(account);
            }
            jsonObject.put("Zamowienie", array);
            json = jsonObject.toString();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return json;



    }

}
