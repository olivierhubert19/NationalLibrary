package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.MyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyBookRepo extends JpaRepository<MyBook,Integer>{
}
