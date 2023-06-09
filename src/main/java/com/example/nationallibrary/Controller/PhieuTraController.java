package com.example.nationallibrary.Controller;


import com.example.nationallibrary.Entity.*;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.BorowedBookService;
import com.example.nationallibrary.Service.PhieuMuonService;
import com.example.nationallibrary.Service.PhieuTraService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PhieuTraController {
    @Autowired
    private PhieuTraService phieuTraService;
    @Autowired
    private BorowedBookService borowedBookServicevice;
    @Autowired
    private PhieuMuonService phieuMuonService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/admin/chitietPhieuTra/{id}")
    public String chiTietPhieuTra(@PathVariable("id") int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        PhieuTra pt = phieuTraService.getById(id);
        System.out.println(pt);
        PhieuMuon pm = phieuMuonService.getById(pt.getIdPhieuMuon());
        List<BorowedBook> tmp = borowedBookServicevice.getByIdPhieuMuon(id);
        List<Book> list = new ArrayList<>();
        for(int i=0;i<tmp.size();i++){
            Book b = bookService.getBookById(tmp.get(i).getIdBook());
            System.out.println(b);
            if(b!=null) list.add(b);
        }
        model.addAttribute("phieumuon", pt);
        model.addAttribute("list",list);
        return "adminChiTietPhieuTra";
    }

}
