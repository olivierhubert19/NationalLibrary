package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/admin/book_register")
    public String bookRegister(Model model){
        model.addAttribute("book",new Book());
        return "adminBookRegister";
    }

    @PostMapping("/admin/save")
    public String addBook(@ModelAttribute Book b){
        bookService.save(b);
        return "redirect:/admin/returnAvailableBook";
    }

    @RequestMapping("/admin/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = bookService.getBookById(id);
        model.addAttribute("book",b);
        return "adminbookEdit";
    }
    @RequestMapping("/admin/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id,Model model){
        try {
            bookService.deleteById(id);
            return "redirect:/admin/returnAvailableBook";
        }catch (DataIntegrityViolationException e){
            model.addAttribute("error","Sách này đang được mượn");
            bookService.deleteById(id);
            return "redirect:/admin/returnAvailableBook";
        }

    }
}
