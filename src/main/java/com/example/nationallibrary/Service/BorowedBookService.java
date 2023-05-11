package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.BorowedBook;
import com.example.nationallibrary.Entity.PhieuMuon;
import com.example.nationallibrary.Repository.BookRepo;
import com.example.nationallibrary.Repository.BorowedBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorowedBookService {
    @Autowired
    private BorowedBookRepo borowedBookRepo;

    public BorowedBook getByIdBook(int id){
        return borowedBookRepo.getBorowedBookByIdBook(id);
    }
    public void save(BorowedBook bb){ borowedBookRepo.save(bb); }
    public void delete(BorowedBook borowedBook){
        borowedBookRepo.delete(borowedBook);
    }
    public List<BorowedBook> getByIdPhieuMuon(int idPhieuMuon){
        return borowedBookRepo.getBorowedBookByIdPhieuMuon(idPhieuMuon);
    }

}
