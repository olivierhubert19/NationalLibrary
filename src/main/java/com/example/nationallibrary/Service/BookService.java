package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public void save(Book b){
        bookRepo.save(b);
    }
    public List<Book> getAllBook(){
        return bookRepo.findAll();
    }
    public Book getBookById(int id){
        return bookRepo.findById(id).get();
    }
    public void deleteById(int id){
        bookRepo.deleteById(id);
    }
}
