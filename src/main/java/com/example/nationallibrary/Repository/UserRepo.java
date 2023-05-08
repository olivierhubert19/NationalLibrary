package com.example.nationallibrary.Repository;

import com.example.nationallibrary.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    Boolean existsByUsername(String username);
    User findByUsername(String username);
}

