package com.example.nationallibrary.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nationallibrary.Repository.loiHongRepo;

@Service
public class loiHongService {
    @Autowired
    private  loiHongRepo loiHongRepo;

}
