package warsztat.controller;

import warsztat.Database;
import warsztat.Naprawa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "index";
        }

        request.getSession().setAttribute("bladLogowania", false);
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {

        return "register";
    }

    @GetMapping("/login")
    public String showLogin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if(session == null)
        {
            return "index";
        }
        try{
            Connection conn = Database.getConnection();
            PreparedStatement naprawa = conn.prepareStatement("SELECT n.id, s.marka, s.model, n.data_przyjecia_naprawy FROM public.\"Naprawy\" n INNER JOIN public.\"Samochody\" s ON n.id_samochodu=s.id;");

            List<Naprawa> list = new ArrayList<Naprawa>();
            ResultSet rss = naprawa.executeQuery();
            while(rss.next()){
                Naprawa naprawainfo = new Naprawa();
                naprawainfo.setId_naprawy(rss.getInt(1));
                naprawainfo.setMarka(rss.getString(2));
                naprawainfo.setModel(rss.getString(3));
                naprawainfo.setData_przyjecia_naprawy(rss.getString(4));

                list.add(naprawainfo);
            }
            model.addAttribute("naprawa", list);
            return "login";
        }catch(SQLException e){
            e.printStackTrace();
        }

        return "login";
    }




    @PostMapping("/login")
    public String loginForm(@RequestParam("login") String login, @RequestParam("password") String password, HttpServletRequest request, Model model) {

        try {
            Connection conn = Database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM public.\"Users\" WHERE login=? AND password=?");
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            System.out.println("sadfsdf");
            ResultSet rs = pstmt.executeQuery();
            request.getSession().setAttribute("isLogged", false);

            if (rs.next()) {

                request.getSession().setAttribute("isLogged", true);
                System.out.println("asdfadsf");
                request.getSession().setAttribute("bladLogowania", false);
                request.getSession().setAttribute("login", login);
                PreparedStatement naprawa = conn.prepareStatement("SELECT n.id, s.marka, s.model, n.data_przyjecia_naprawy FROM public.\"Naprawy\" n INNER JOIN public.\"Samochody\" s ON n.id_samochodu=s.id;");

                List<Naprawa> list = new ArrayList<Naprawa>();
                ResultSet rss = naprawa.executeQuery();
                while(rss.next()){
                    Naprawa naprawainfo = new Naprawa();
                    naprawainfo.setId_naprawy(rss.getInt(1));
                    naprawainfo.setMarka(rss.getString(2));
                    naprawainfo.setModel(rss.getString(3));
                    naprawainfo.setData_przyjecia_naprawy(rss.getString(4));

                    list.add(naprawainfo);
                }
                model.addAttribute("naprawa", list);
                return "login";
            }
            else{
                request.getSession().setAttribute("bladLogowania", true);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "index";
    }

}