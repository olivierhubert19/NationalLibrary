package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.PhieuTra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuTraRepo extends JpaRepository<PhieuTra,Integer> {

    public PhieuTra getPhieuTraByIdPhieuMuon(int id);
}
