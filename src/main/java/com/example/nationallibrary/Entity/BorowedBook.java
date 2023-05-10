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
public class BorowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date ngayTra;
    private int idBook;
//    private int idPhieuTra;
    private int idPhieuMuon;

    public BorowedBook(Date ngayTra, int idBook, int idPhieuMuon) {
        this.ngayTra = ngayTra;
        this.idBook = idBook;
        this.idPhieuMuon = idPhieuMuon;
    }
}
