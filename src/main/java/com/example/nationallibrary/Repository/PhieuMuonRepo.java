package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.PhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuMuonRepo extends JpaRepository<PhieuMuon,Integer> {
    PhieuMuon getPhieuMuonById(int id);
}
