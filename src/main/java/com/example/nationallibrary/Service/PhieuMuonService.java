package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Repository.PhieuMuonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuMuonService {
    @Autowired
    private PhieuMuonRepo phieuMuonRepo;

    public List<PhieuMuon> getAll(){
        return phieuMuonRepo.findAll();
    }
}
