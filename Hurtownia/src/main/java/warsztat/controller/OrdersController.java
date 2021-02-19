package warsztat.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import warsztat.Database;
import warsztat.Ordered;
import warsztat.Orders;
import warsztat.Parts;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Controller
public class OrdersController {

    @PostMapping("orders")
    public String showOrders(Model model, HttpServletRequest request) throws JSONException {


        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://10.1.0.210:8080/order_parts1", String.class);
        JSONObject json = new JSONObject(response);
        JSONArray ja = json.getJSONArray("Zamowienie");

        List<Ordered> list = new ArrayList<Ordered>();
        for (int i = 0; i < ja.length(); i++) {
            JSONObject ob = ja.getJSONObject(i);



            Ordered orders = new Ordered();
            //orders.setWarsztat_id(1);
            orders.setId_czesci(ob.getInt("id_czesci"));
            orders.setNazwa(ob.getString("nazwa"));
            orders.setIlosc(ob.getInt("ilosc"));
            list.add(orders);

        }

        model.addAttribute("orders", list);

        RestTemplate rest = new RestTemplate();
        String response1 = rest.getForObject("http://10.1.0.211:8081/order_parts1", String.class);
        JSONObject json1 = new JSONObject(response1);
        JSONArray ja1 = json1.getJSONArray("Zamowienie");

        List<Ordered> list1 = new ArrayList<Ordered>();
        for (int i = 0; i < ja1.length(); i++) {
            JSONObject ob1 = ja1.getJSONObject(i);



            Ordered orders1 = new Ordered();
            //orders1.setWarsztat_id(2);
            orders1.setId_czesci(ob1.getInt("id_czesci"));
            orders1.setNazwa(ob1.getString("nazwa"));
            orders1.setIlosc(ob1.getInt("ilosc"));
            list1.add(orders1);

        }

        model.addAttribute("orders1", list1);

        return "orders";
    }
}
