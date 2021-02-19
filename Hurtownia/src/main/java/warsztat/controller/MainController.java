package warsztat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import warsztat.Parts;
import warsztat.Database;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex(Model model) {

        refresh(model);
        return "index";
    }

    public void refresh(Model model) {
        Connection connection = Database.getConnection();
        Statement statement;
        try {

            String selectTableSQL = "SELECT \"ID\", \"Nazwa\", \"Ilosc\"\n" +
                    "\tFROM public.\"Czesc\" ORDER BY \"ID\";";
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);
            List<Parts> list = new ArrayList<Parts>();
            while (rs.next()) {
                Parts parts = new Parts();
                parts.setId(rs.getInt(1));
                parts.setNazwa(rs.getString(2));
                parts.setIlosc(rs.getInt(3));
                list.add(parts);
            }
            model.addAttribute("parts", list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/add")
    public String addItems(@RequestParam("name") String name, @RequestParam("amount") int amount, Model model) {

        Connection connection = Database.getConnection();
        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO public.\"Czesc\"(\n" +
                    "\t\"Nazwa\", \"Ilosc\")\n" +
                    "\tVALUES (?, ?);");
            pstmt.setString(1, name);
            pstmt.setInt(2, amount);
            pstmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refresh(model);
        return "index";
    }

    @PostMapping("/addNumber")
    public String addItems(HttpServletRequest request, Model model) {

        String idString = request.getParameter("id");
        String iloscString = request.getParameter("ilosc");
        String ileString = request.getParameter("ile");
        int id = Integer.parseInt(idString);
        int ilosc = Integer.parseInt(iloscString);
        int ile = Integer.parseInt(ileString);
        try {
            Connection connection = Database.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE public.\"Czesc\"\n" +
                    "\tSET \"Ilosc\"=?\n" +
                    "\tWHERE \"ID\" = ?;");

            pstmt.setInt(1, ilosc + ile);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refresh(model);
        return "index";
    }


}