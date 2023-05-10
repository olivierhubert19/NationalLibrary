package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.BorowedBook;
import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Entity.PhieuTra;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.BorowedBookService;
import com.example.nationallibrary.Service.PhieuMuonService;
import com.example.nationallibrary.Service.PhieuTraService;
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
    public PhieuMuonService phieuMuonService;
    @Autowired
    public PhieuTraService phieuTraService;
    @Autowired
    public BorowedBookService borowedBookService;
    @Autowired
    public BookService bookService;

    @GetMapping("/admin/chitietPhieuMuon/{id}")
    public String chiTietPhieuMuon(@PathVariable("id") int id, Model model){
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
    public String duyetPhieuMuon(@PathVariable("id") int id){
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        PhieuTra pt = new PhieuTra(pm.getId(),new Date(),false);
        phieuTraService.Save(pt);
        pm.setTinhTrang(true);
        phieuMuonService.Save(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
    @RequestMapping("/admin/xoaPhieuMuon/{id}")
    public String xoaPhieuMuon(@PathVariable("id") int id){
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        List<BorowedBook> list = borowedBookService.getByIdPhieuMuon(id);
        for(BorowedBook b: list){
            borowedBookService.delete(b);
        }
        phieuMuonService.Delete(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
}
