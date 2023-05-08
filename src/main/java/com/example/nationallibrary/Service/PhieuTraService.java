package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.PhieuTra;
import com.example.nationallibrary.Repository.PhieuMuonRepo;
import com.example.nationallibrary.Repository.PhieuTraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuTraService {
    @Autowired
    private PhieuTraRepo phieuTraRepo;

    public List<PhieuTra> getAll(){
        return phieuTraRepo.findAll();
    }
}
