package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhieuMuonRepo extends JpaRepository<PhieuMuon,Integer> {

    public List<PhieuMuon> getAllByIdReader(int id);

    public  List<PhieuMuon> getAllByIdReaderAndAndTinhTrang(int id,boolean tinhTrang);

    public PhieuMuon getById(int id);
}
