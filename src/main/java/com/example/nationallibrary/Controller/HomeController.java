package com.example.nationallibrary.Controller;


import com.example.nationallibrary.Entity.User;
import com.example.nationallibrary.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping(path = {"/","/logout"})
    public ModelAndView login(){
        return new ModelAndView("login","user",new User());
    }



    @PostMapping("/loginCheck")
    public ModelAndView loginCheck(@ModelAttribute("user") User user) {
        user = userService.checkLogin(user.getUsername(), user.getPassword());
        if (user != null) {
            if (user.getRole().equals("ADMIN")) return new ModelAndView("adminHome", "user", user);
            else return new ModelAndView("userhome", "user", user);
        } else {
            return new ModelAndView("login", "user", new User());
        }

    }

}
