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
import java.util.Date;
import java.util.List;

@Controller
public class PhieuMuonController {
    @Autowired
    private PhieuMuonService phieuMuonService;
    @Autowired
    private PhieuTraService phieuTraService;
    @Autowired
    private BorowedBookService borowedBookService;
    @Autowired
    private BookService bookService;

    @GetMapping("/admin/chitietPhieuMuon/{id}")
    public String chiTietPhieuMuon(@PathVariable("id") int id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        PhieuMuon p = phieuMuonService.getPhieuMuonById(id);
        model.addAttribute("phieumuon", p);
        List<BorowedBook> tmp = borowedBookService.getByIdPhieuMuon(id);
        List<Book> list = new ArrayList<>();
        for(int i=0;i<tmp.size();i++){
            Book b = bookService.getBookById(tmp.get(i).getIdBook());
            if(b!=null) list.add(b);
        }
        model.addAttribute("list", list);
        return "adminChiTietPhieuMuon";
    }

    @RequestMapping("/admin/duyetPhieuMuon/{id}")
    public String duyetPhieuMuon(@PathVariable("id") int id,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        PhieuTra pt = new PhieuTra(pm.getId(),new Date(),false);
        phieuTraService.Save(pt);
        pm.setTinhTrang(true);
        phieuMuonService.Save(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
    @RequestMapping("/admin/xoaPhieuMuon/{id}")
    public String xoaPhieuMuon(@PathVariable("id") int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        List<BorowedBook> list = borowedBookService.getByIdPhieuMuon(id);
        for(BorowedBook b: list){
            borowedBookService.delete(b);
        }
        phieuMuonService.Delete(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
}
