package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Service.PhieuMuonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhieuMuonController {
    @Autowired
    public PhieuMuonService phieuMuonService;

    @GetMapping("/admin/chitietPhieuMuon/{id}")
    public String chiTietPhieuMuon(@PathVariable("id") int id, Model model){
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        model.addAttribute("pm",pm);
        return "adminChiTietPhieuMuon";


    }

    @RequestMapping("/admin/duyetPhieuMuon/{id}")
    public String duyetPhieuMuon(@PathVariable("id") int id){
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        pm.setTinhTrang(true);
        phieuMuonService.Save(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
    @RequestMapping("/admin/xoaPhieuMuon/{id}")
    public String xoaPhieuMuon(@PathVariable("id") int id){
        PhieuMuon pm = phieuMuonService.getPhieuMuonById(id);
        phieuMuonService.Delete(pm);
        return "redirect:/admin/ListPhieuMuon";
    }
}