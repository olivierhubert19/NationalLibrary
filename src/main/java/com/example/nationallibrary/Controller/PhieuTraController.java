package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Service.PhieuTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhieuTraController {
    @Autowired
    private PhieuTraService phieuTraService;



}
