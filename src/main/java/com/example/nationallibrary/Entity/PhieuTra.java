package com.example.nationallibrary.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuTra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idPhieuMuon;
    private Date ngayTra;
    private boolean tinhTrang;

    public PhieuTra(int idPhieuMuon, Date ngayTra, boolean tinhTrang) {
        this.idPhieuMuon = idPhieuMuon;
        this.ngayTra = ngayTra;
        this.tinhTrang = tinhTrang;
    }
}
