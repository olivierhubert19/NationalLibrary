package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Entity.PhieuTra;
import com.example.nationallibrary.Entity.User;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.PhieuMuonService;
import com.example.nationallibrary.Service.PhieuTraService;
import com.example.nationallibrary.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private PhieuTraService phieuTraService;

    @GetMapping(path = {"/user/Home", "/user/returnHome"})
    public String userHome() {
        return "userhome";
    }

    @GetMapping(path = {"/user/returnAvailableBook"})
    public String goToUserAvailableBook(@ModelAttribute("user") User user, Model model, HttpSession session) {
        user = (User) session.getAttribute("user");
        System.out.println(user);
        List<Book> list = bookService.getAllBook();
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "userBookList";
    }

    @GetMapping(path = {"/user/ListPhieuMuon"})
    public String goToUserPhieuMuon(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> list = phieuMuonService.getAllByIdReader(user.getId());
            model.addAttribute("list", list);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userPhieuMuon";
    }

    @GetMapping(path = {"/user/ListPhieuTra"})
    public String goToUserPhieuTra(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> listPhieuMuon = phieuMuonService.getAllByIdReader(user.getId());
            List<PhieuTra> list = new ArrayList<>();
            for (int i = 0; i < listPhieuMuon.size(); i++) {
                try {
                    PhieuTra l = phieuTraService.getAllByIdPhieuMuon(listPhieuMuon.get(i).getId());
                    if(l!=null) list.add(l);
                    System.out.println("Log goToUserPhieuTra1 " + l);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Log goToUserPhieuTra2 " + list.size());
            model.addAttribute("list", list);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userPhieuTra";
    }

    @RequestMapping(path = {"/user/addToPhieuMuon/{id}"})
    public String addToPhieuMuon(@PathVariable("id") int id, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> list = phieuMuonService.getAllByIdReaderAndTinhTrang(user.getId(),false);
            Book book = bookService.getBookById(id);
            model.addAttribute("list", list);
            model.addAttribute("user", user);
            model.addAttribute("book", book);
            System.out.println("log addToPhieuMuon " + book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userAddPhieuMuon";
    }

}
