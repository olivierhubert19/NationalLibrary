package com.example.nationallibrary.Service;

import com.example.nationallibrary.Entity.User;
import com.example.nationallibrary.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void save(User u){ userRepo.save(u);}

    public User checkLogin(String username,String pass){
        User user = userRepo.findByUsername(username);
        if(user.getPassword().equals(pass)) return user;
        return null;
    }

}
