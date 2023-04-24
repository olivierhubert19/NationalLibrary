package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.MyBook;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookService myBookService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("/available_book")
    public ModelAndView availableBook(){
        List<Book> list = bookService.getAllBook();
        return new ModelAndView("bookList","books",list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        bookService.save(b);
        return "redirect:/available_book";
    }
    @GetMapping("/my_books")
    public ModelAndView getMyBook(){
        List<MyBook> list = myBookService.getAllBook();
        return new ModelAndView("myBooks","books",list);
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b = bookService.getBookById(id);
        MyBook mb = new MyBook(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.save(mb);
        return "redirect:/my_books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = bookService.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteById(id);
        return "redirect:/available_book";
    }
}
