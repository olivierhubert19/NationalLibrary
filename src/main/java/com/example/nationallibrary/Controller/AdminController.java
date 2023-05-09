package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.*;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.PhieuMuonService;
import com.example.nationallibrary.Service.PhieuTraService;
import com.example.nationallibrary.Service.ReaderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PhieuMuonService phieuMuonService;
    @Autowired
    private PhieuTraService phieuTraService;
    @Autowired
    private ReaderService readerService;

    @GetMapping("/admin/returnHome")
    public String returnHome(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user",user);
        return "adminHome";
    }

    @GetMapping("/admin/returnAvailableBook")
    public String availableBook(@ModelAttribute("user") User user, Model model, HttpSession session){
        user = (User) session.getAttribute("user");
        System.out.println(user);
        List<Book> list = bookService.getAllBook();
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        return "adminBookList";
    }

    @GetMapping("/admin/ListPhieuMuon")
    public String ListPhieuMuon(@ModelAttribute("user") User user,Model model){
        List<PhieuMuon> list = phieuMuonService.getAll();
        System.out.println("phieumuon:" + list.size());
        for(PhieuMuon a : list){
            System.out.println(a);
        }
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "adminPhieuMuon";
    }
    @GetMapping("/admin/ListPhieuTra")
    public String ListPhieuTra(@ModelAttribute("user") User user, Model model){
        List<PhieuTra> list = phieuTraService.getAll();
        model.addAttribute("lists",list);
        model.addAttribute("user",user);
        return "adminPhieuTra";
    }


    @GetMapping("/admin/Reader")
    public String ListReader(@ModelAttribute("user") User user, Model model){
        List<Reader> list = readerService.getAll();
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "adminReader";
    }
}
