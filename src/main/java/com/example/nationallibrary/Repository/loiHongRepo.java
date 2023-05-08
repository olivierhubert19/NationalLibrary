package com.example.nationallibrary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nationallibrary.Entity.loiHong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface loiHongRepo extends JpaRepository<loiHong,Integer> {

}
