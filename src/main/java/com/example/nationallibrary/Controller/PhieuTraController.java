package com.example.nationallibrary.Controller;


import com.example.nationallibrary.Service.BorowedBookService;
import com.example.nationallibrary.Service.PhieuTraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class PhieuTraController {
    @Autowired
    private PhieuTraService phieuTraService;
    @Autowired
    private BorowedBookService borowedBookServicevice;


}
