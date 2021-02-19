package warsztat.controller;

import org.jsoup.Jsoup;
import warsztat.Database;
import warsztat.Part;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EditController {

    @GetMapping("/edit")
    public String showIndex(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }


        request.getSession().setAttribute("bladLogowania", false);
        return "edit";
    }

    @PostMapping("/edit")
    public String editForm(@RequestParam("id_naprawy") int id_naprawy, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }

        System.out.println(id_naprawy);

        try{

            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT * FROM public.\"Naprawy\" n INNER JOIN public.\"Samochody\" s ON n.id_samochodu = s.id INNER JOIN public.\"Czas_naprawy\" c ON n.id = c.id_naprawy  WHERE n.id=?;");
            naprawa.setInt(1, id_naprawy);
            ResultSet rss = naprawa.executeQuery();

            while(rss.next()) {
                model.addAttribute("id_naprawy", rss.getInt(1)  );
                model.addAttribute("opis_usterki", rss.getString(3) );
                model.addAttribute("opis_naprawy", rss.getString(4) );
                model.addAttribute("stan_naprawy", rss.getString(5) );
                model.addAttribute("model", rss.getString(10) );
                model.addAttribute("marka", rss.getString(11) );
                model.addAttribute("rok_produkcji", rss.getString(12) );
                model.addAttribute("planowany_poczatek_naprawy", rss.getString(16) );
                model.addAttribute("planowany_koniec_naprawy", rss.getString(17) );
            }



        }catch(SQLException e){
            e.printStackTrace();
        }

        request.getSession().setAttribute("bladLogowania", false);
        return "edit";
    }

    @GetMapping("update")
    public String showUpdate(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }


        request.getSession().setAttribute("bladLogowania", false);
        return "login";
    }

    @PostMapping("/update")
    public String updateForm(@RequestParam("opis_naprawy") String opis_naprawy, @RequestParam("stan_naprawy") String stan_naprawy,
                             @RequestParam("planowany_poczatek_naprawy") String planowany_poczatek_naprawy, @RequestParam("planowany_koniec_naprawy") String planowany_koniec_naprawy,
                             @RequestParam("id_naprawy") int id_naprawy, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }



        try{

            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE public.\"Naprawy\" SET opis_naprawy = ?, stan_naprawy = ? WHERE id = ?;");
            pstmt.setString(1, opis_naprawy);
            pstmt.setString(2, stan_naprawy);
            pstmt.setInt(3, id_naprawy);

            pstmt.executeQuery();





        }catch(SQLException e){
            e.printStackTrace();
        }

        try{

            Connection conn = Database.getConnection();
            PreparedStatement pstmt2 = conn.prepareStatement("UPDATE public.\"Czas_naprawy\" SET planowany_poczatek_naprawy = ?, planowany_koniec_naprawy = ? WHERE id_naprawy = ?;");
            pstmt2.setString(1, planowany_poczatek_naprawy);
            pstmt2.setString(2, planowany_koniec_naprawy);
            pstmt2.setInt(3, id_naprawy);

            pstmt2.executeQuery();





        }catch(SQLException e){
            e.printStackTrace();
        }




        request.getSession().setAttribute("bladLogowania", false);
        return "save";
    }

    @PostMapping("/parts")
    public String addParts(@RequestParam("id_naprawy") int id_naprawy, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }

        model.addAttribute("id_naprawy", id_naprawy  );

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://10.1.0.212:8082/availability", String.class);
        JSONObject json = new JSONObject(response);
        JSONArray ja = json.getJSONArray("Dostepnosc");

        List<Part> list = new ArrayList<Part>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject ob = ja.getJSONObject(i);



            Part part = new Part();
            part.setHurtownia_id(1);
            part.setId(ob.getInt("id"));
            part.setNazwa(ob.getString("nazwa"));
            part.setIlosc(ob.getInt("ilosc"));
            list.add(part);

        }

        model.addAttribute("part", list);



        RestTemplate rest = new RestTemplate();
        String response1 = rest.getForObject("http://10.1.0.213:8083/availability", String.class);
        JSONObject json1 = new JSONObject(response1);
        JSONArray ja1 = json1.getJSONArray("Dostepnosc");

        List<Part> list1 = new ArrayList<Part>();
        for (int i = 0; i < ja1.length(); i++) {
            JSONObject ob1 = ja1.getJSONObject(i);



            Part part1 = new Part();
            part1.setHurtownia_id(2);
            part1.setId(ob1.getInt("id"));
            part1.setNazwa(ob1.getString("nazwa"));
            part1.setIlosc(ob1.getInt("ilosc"));
            list1.add(part1);

        }

        model.addAttribute("part1", list1);



        try{

            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT nazwa, ilosc FROM public.\"Wymagane_czesci\" WHERE id_naprawy=?;");
            naprawa.setInt(1, id_naprawy);
            List<Part> lista = new ArrayList<Part>();
            ResultSet rss = naprawa.executeQuery();
            while(rss.next()){
                Part naprawainfo = new Part();
                naprawainfo.setNazwa(rss.getString(1));
                naprawainfo.setIlosc(rss.getInt(2));


                lista.add(naprawainfo);
            }
            model.addAttribute("naprawa_baza", lista);





        }catch(SQLException e){
            e.printStackTrace();
        }






        request.getSession().setAttribute("bladLogowania", false);
        return "parts";
    }

    @PostMapping("/add_parts")
    public String addPartsToBase(@RequestParam("id_naprawy") int id_naprawy, @RequestParam("hurtownia_id") int hurtownia_id, @RequestParam("id") int id, @RequestParam("nazwa") String nazwa, @RequestParam("ilosc") int ilosc, HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }



        try{

            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO public.\"Wymagane_czesci\" (ilosc, id_naprawy, id_czesci, nazwa, hurtownia_id) VALUES (?, ?, ?, ?, ?);");

            pstmt.setInt(1, ilosc);
            pstmt.setInt(2, id_naprawy);
            pstmt.setInt(3, id);
            pstmt.setString(4, nazwa);
            pstmt.setInt(5, hurtownia_id);

            pstmt.execute();





        }catch(SQLException e){
            e.printStackTrace();
        }



        model.addAttribute("id_naprawy", id_naprawy);

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://10.1.0.212:8082/availability", String.class);
        JSONObject json = new JSONObject(response);
        JSONArray ja = json.getJSONArray("Dostepnosc");

        List<Part> list = new ArrayList<Part>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject ob = ja.getJSONObject(i);



            Part part = new Part();
            part.setHurtownia_id(1);
            part.setId(ob.getInt("id"));
            part.setNazwa(ob.getString("nazwa"));
            part.setIlosc(ob.getInt("ilosc"));
            list.add(part);

        }

        model.addAttribute("part", list);

        RestTemplate rest = new RestTemplate();
        String response1 = rest.getForObject("http://10.1.0.213:8083/availability", String.class);
        JSONObject json1 = new JSONObject(response1);
        JSONArray ja1 = json1.getJSONArray("Dostepnosc");

        List<Part> list1 = new ArrayList<Part>();
        for (int i = 0; i < ja1.length(); i++) {
            JSONObject ob1 = ja1.getJSONObject(i);



            Part part1 = new Part();
            part1.setHurtownia_id(2);
            part1.setId(ob1.getInt("id"));
            part1.setNazwa(ob1.getString("nazwa"));
            part1.setIlosc(ob1.getInt("ilosc"));
            list1.add(part1);

        }

        model.addAttribute("part1", list1);

        try{

            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT nazwa, ilosc FROM public.\"Wymagane_czesci\" WHERE id_naprawy=?;");
            naprawa.setInt(1, id_naprawy);
            List<Part> lista = new ArrayList<Part>();
            ResultSet rss = naprawa.executeQuery();
            while(rss.next()){
                Part naprawainfo = new Part();
                naprawainfo.setNazwa(rss.getString(1));
                naprawainfo.setIlosc(rss.getInt(2));


                lista.add(naprawainfo);
            }
            model.addAttribute("naprawa_baza", lista);





        }catch(SQLException e){
            e.printStackTrace();
        }




        request.getSession().setAttribute("bladLogowania", false);
        return "parts";
    }


}
