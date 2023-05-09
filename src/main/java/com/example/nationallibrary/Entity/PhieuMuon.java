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
@Table(name="PhieuMuon")
public class PhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_Reader")
    private int idReader;
    @Column(name = "ngay_Muon")
    private Date ngayMuon;
    @Column(name = "tinh_Trang")
    private boolean tinhTrang;
}
