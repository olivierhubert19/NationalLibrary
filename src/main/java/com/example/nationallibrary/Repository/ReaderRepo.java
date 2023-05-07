package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepo extends JpaRepository<Reader,Integer> {
}
