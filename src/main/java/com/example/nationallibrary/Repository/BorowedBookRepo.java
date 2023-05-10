package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.BorowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorowedBookRepo extends JpaRepository<BorowedBook,Integer> {
    List<BorowedBook> getBorowedBookByIdPhieuMuon(int idPhieuMuon);
    Void deleteBorowedBookByIdPhieuMuon(int idPhieuMuon);

}
