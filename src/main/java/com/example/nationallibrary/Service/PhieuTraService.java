package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.PhieuTra;
import com.example.nationallibrary.Repository.PhieuTraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PhieuTraService {
    @Autowired
    private PhieuTraRepo phieuTraRepo;

    public PhieuTra getById(int id){return phieuTraRepo.getById(id);}
    public List<PhieuTra> getAll() {
        return phieuTraRepo.findAll();
    }

    public void Save(PhieuTra pt){
        phieuTraRepo.save(pt);
    }

    public PhieuTra getAllByIdPhieuMuon(int id) {
        return phieuTraRepo.getPhieuTraByIdPhieuMuon(id);
    }

}
