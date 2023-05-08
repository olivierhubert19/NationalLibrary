package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.Reader;
import com.example.nationallibrary.Repository.ReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepo readerRepo;

    public List<Reader> getAll(){
        return readerRepo.findAll();
    }

}
