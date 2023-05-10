package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Repository.PhieuMuonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PhieuMuonService {
    @Autowired
    private PhieuMuonRepo phieuMuonRepo;

    public List<PhieuMuon> getAll(){
        return phieuMuonRepo.findAll();
    }

    public List<PhieuMuon> getAllByIdReader(int id){
        return phieuMuonRepo.getAllByIdReader(id);
    }

    public List<PhieuMuon> getAllByIdReaderAndTinhTrang(int id,boolean tinhTrang){
        return phieuMuonRepo.getAllByIdReaderAndAndTinhTrang(id,tinhTrang);
    }

    public PhieuMuon getById(int id){
        return phieuMuonRepo.getById(id);
    }

}
