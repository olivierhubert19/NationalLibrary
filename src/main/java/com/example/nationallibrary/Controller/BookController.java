package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.Book;
import com.example.nationallibrary.Entity.BorowedBook;
import com.example.nationallibrary.Service.BookService;
import com.example.nationallibrary.Service.BorowedBookService;
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
    public String bookRegister(Model model){
        model.addAttribute("book",new Book());
        return "adminBookRegister";
    }

    @PostMapping("/admin/save")
    public String addBook(@ModelAttribute Book b,Model model){
        if(b.getName().equals("")||b.getAuthor().equals("")||b.getPrice()==0||b.getYear()==0){
            model.addAttribute("error","Không thành công !!!");
        }
        else bookService.save(b);
        List<Book> list = bookService.getAllBook();
        model.addAttribute("list",list);
        return "adminBookList";
    }

    @RequestMapping("/admin/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = bookService.getBookById(id);
        model.addAttribute("book",b);
        return "adminbookEdit";
    }
    @RequestMapping("/admin/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id,Model model){
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

