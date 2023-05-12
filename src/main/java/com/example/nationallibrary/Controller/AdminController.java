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
    public String returnHome(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("user",user);
        return "adminHome";
    }

    @GetMapping("/admin/returnAvailableBook")
    public String availableBook(@ModelAttribute("user") User user, Model model, HttpSession session){
        user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(user);
        List<Book> list = bookService.getAllBook();
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        return "adminBookList";
    }

    @GetMapping("/admin/ListPhieuMuon")
    public String ListPhieuMuon(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        List<PhieuMuon> list = phieuMuonService.getAll();
        System.out.println("phieumuon:" + list.size());
        for(PhieuMuon a : list){
            System.out.println(a);
        }
        for (PhieuMuon p : list) {
            Reader reader = readerService.getById(p.getIdReader());
            p.setNameReader(reader.getName());
        }
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "adminPhieuMuon";
    }
    @GetMapping("/admin/ListPhieuTra")
    public String ListPhieuTra( Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        List<PhieuTra> list = phieuTraService.getAll();
        model.addAttribute("lists",list);
        model.addAttribute("user",user);
        return "adminPhieuTra";
    }


    @GetMapping("/admin/Reader")
    public String ListReader(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Reader> list = readerService.getAll();
        model.addAttribute("list",list);
        model.addAttribute("user",user);
        return "adminReader";
    }

}
