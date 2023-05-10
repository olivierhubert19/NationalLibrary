package com.example.nationallibrary.Controller;


import com.example.nationallibrary.Entity.User;
import com.example.nationallibrary.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping(path = {"/", "/logout", "/login"})
    public ModelAndView login() {
        return new ModelAndView("login", "user", new User());
    }


    @PostMapping("/loginCheck")
    public String loginCheck(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
        try {
            user = userService.checkLogin(user.getUsername(), user.getPassword());
            if (user != null) {
                if (user.getRole().equals("ADMIN")) {
                    model.addAttribute("user", user);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    return "redirect:/admin/Home";
                }
                else {
                    model.addAttribute("user", user);
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    return "redirect:/user/Home";
                }
            }
        } catch (Exception e) {
            model.addAttribute("error", "Tên đăng nhập hoặc mặt khẩu chưa đúng, vui lòng nhập lại!!");
            return "login";
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mặt khẩu chưa đúng, vui lòng nhập lại!!");
        return "login";
    }


    @GetMapping("/admin/Home")
    public String home(@ModelAttribute("user") User user, Model model, HttpSession session) {
        user = (User) session.getAttribute("user");
        System.out.println(user);
        model.addAttribute("user", user);
        return "adminHome";
    }


}
