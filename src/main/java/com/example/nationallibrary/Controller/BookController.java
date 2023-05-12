package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.BorowedBook;
import com.example.nationallibrary.Entity.User;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.BorowedBookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BorowedBookService borowedBookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/admin/book_register")
    public String bookRegister(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("book",new Book());
        return "adminBookRegister";
    }

    @PostMapping("/admin/save")
    public String addBook(@ModelAttribute Book b,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        if(b.getName().equals("")||b.getAuthor().equals("")||b.getPrice()==0||b.getYear()==0){
            model.addAttribute("error","Không thành công !!!");
        }
        else bookService.save(b);
        List<Book> list = bookService.getAllBook();
        model.addAttribute("list",list);
        return "adminBookList";
    }

    @RequestMapping("/admin/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        Book b = bookService.getBookById(id);
        model.addAttribute("book",b);
        return "adminbookEdit";
    }
    @RequestMapping("/admin/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
            BorowedBook bb = borowedBookService.getByIdBook(id);
            if(bb!=null){
            model.addAttribute("error","Sách này đang được mượn");
            }
            else{
            bookService.deleteById(id);
            }
            List<Book> list = bookService.getAllBook();
            model.addAttribute("list",list);
            return "adminBookList";
        }
}

