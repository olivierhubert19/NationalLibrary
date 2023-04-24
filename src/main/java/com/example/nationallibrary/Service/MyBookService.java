package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.MyBook;
import com.example.nationallibrary.Repository.BookRepo;
import com.example.nationallibrary.Repository.MyBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    @Autowired
    private MyBookRepo myBookRepo;

    public void save(MyBook b){
        myBookRepo.save(b);
    }
    public List<MyBook> getAllBook(){
        return myBookRepo.findAll();
    }
    public void deleteById(int id){
        myBookRepo.deleteById(id);
    }
}
