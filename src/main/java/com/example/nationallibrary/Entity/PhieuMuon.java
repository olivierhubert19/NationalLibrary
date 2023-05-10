package com.example.nationallibrary.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private Integer idReader;

    private Date ngayMuon;

    private Boolean tinhTrang;

    public PhieuMuon(Integer idReader, Date ngayMuon, Boolean tinhTrang) {
        this.idReader = idReader;
        this.ngayMuon = ngayMuon;
        this.tinhTrang = tinhTrang;
    }
}
