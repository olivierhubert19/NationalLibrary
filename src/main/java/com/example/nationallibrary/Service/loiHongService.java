package com.example.nationallibrary.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nationallibrary.Repository.loiHongRepo;
import com.example.nationallibrary.Entity.loiHong;

@Service
public class loiHongService {
    @Autowired
    private  loiHongRepo loiHongRepo;

    private void save(loiHong loiHong){
        loiHongRepo.save(loiHong);

    }
}
