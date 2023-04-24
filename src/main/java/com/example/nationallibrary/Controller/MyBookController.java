package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookController {
    @Autowired
    private MyBookService myBookService;
    @RequestMapping("/deleteMyBook/{id}")
    public String deleteById(@PathVariable("id") int id){
        myBookService.deleteById(id);
        return "redirect:/my_books";
    }
}
