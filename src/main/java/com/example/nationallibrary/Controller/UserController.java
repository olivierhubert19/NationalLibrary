package com.example.nationallibrary.Controller;

import com.example.nationallibrary.Entity.*;
import com.example.nationallibrary.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PhieuMuonService phieuMuonService;

    @Autowired
    private PhieuTraService phieuTraService;

    @Autowired
    private BorowedBookService borowedBookService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private UserService userService;

    @GetMapping(path = {"/user/Home", "/user/returnHome"})
    public String userHome() {
        return "userhome";
    }

    @GetMapping(path = {"/user/returnAvailableBook"})
    public String goToUserAvailableBook(@ModelAttribute("user") User user, Model model, HttpSession session) {
        user = (User) session.getAttribute("user");
        List<Book> list = bookService.getAllBook();
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        return "userBookList";
    }

    @GetMapping(path = {"/user/ListPhieuMuon"})
    public String goToUserPhieuMuon(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> list = phieuMuonService.getAllByIdReader(user.getId());
            model.addAttribute("list", list);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userPhieuMuon";
    }

    @GetMapping(path = {"/user/ListPhieuTra"})
    public String goToUserPhieuTra(@ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> listPhieuMuon = phieuMuonService.getAllByIdReader(user.getId());
            List<PhieuTra> list = new ArrayList<>();
            for (int i = 0; i < listPhieuMuon.size(); i++) {
                try {
                    PhieuTra l = phieuTraService.getAllByIdPhieuMuon(listPhieuMuon.get(i).getId());
                    if (l != null) list.add(l);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addAttribute("list", list);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userPhieuTra";
    }

    @RequestMapping(path = {"/user/addToPhieuMuon/{id}"})
    public String addToPhieuMuon(@PathVariable("id") int id, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            List<PhieuMuon> list = phieuMuonService.getAllByIdReaderAndTinhTrang(user.getId(), false);
            Book book = bookService.getBookById(id);
            model.addAttribute("list", list);
            model.addAttribute("user", user);
            model.addAttribute("book", book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userAddPhieuMuon";
    }

    @RequestMapping(path = {"/user/actionAddPhieuMuon/{id}/{idBook}"})
    public String addToClosePhieuMuon(@PathVariable("id") int idPhieuMuon, @PathVariable("idBook") int idBook, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            System.out.println("id phieu muon: " + idPhieuMuon);
            System.out.println("id book: " + idBook);
            PhieuMuon p = phieuMuonService.getById(idPhieuMuon);
            borowedBookService.save(new BorowedBook(p.getNgayMuon(), idBook, idPhieuMuon));
            user = (User) httpSession.getAttribute("user");
            List<Book> list = bookService.getAllBook();
            model.addAttribute("user", user);
            model.addAttribute("list", list);
            model.addAttribute("successMessage", "Thêm sách vào phiếu mượn thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userBookList";
    }

    @RequestMapping(path = {"/userAddNewTicker/{idBook}"})
    public String addNewTicket(@PathVariable("idBook") int id, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            user = (User) httpSession.getAttribute("user");
            PhieuMuon p = new PhieuMuon(user.getId(), new Date(), false);
            phieuMuonService.savePhieuMuon(p);
            List<PhieuMuon> list = phieuMuonService.getAllByIdReaderAndTinhTrang(user.getId(), false);
            Book book = bookService.getBookById(id);
            model.addAttribute("list", list);
            model.addAttribute("user", user);
            model.addAttribute("book", book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "userAddPhieuMuon";
    }

    @RequestMapping(path = {"/user/detailPhieuMuon/{id}"})
    public String detailPhieuMuon(@PathVariable("id") int id, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            PhieuMuon p = phieuMuonService.getById(id);
            model.addAttribute("phieumuon", p);
            List<BorowedBook> tmp = borowedBookService.getByIdPhieuMuon(id);
            List<Book> list = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Book b = bookService.getBookById(tmp.get(i).getIdBook());
                if (b != null) list.add(b);
            }
            model.addAttribute("list", list);
            System.out.println("Log " + p);
            System.out.println("Log " + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "detailPhieuMuon";
    }

    @RequestMapping(path = {"/user/detailPhieuTra/{id}"})
    public String detailPhieuTra(@PathVariable("id") int id, @ModelAttribute("user") User user, Model model, HttpSession httpSession) {
        try {
            PhieuTra phieuTra = phieuTraService.getAllByIdPhieuMuon(id);
            PhieuMuon p = phieuMuonService.getById(phieuTra.getId());
            model.addAttribute("phieutra", phieuTra);
            List<BorowedBook> tmp = borowedBookService.getByIdPhieuMuon(p.getId());
            List<Book> list = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Book b = bookService.getBookById(tmp.get(i).getIdBook());
                if (b != null) list.add(b);
            }
            model.addAttribute("list", list);
            System.out.println("Log " + p);
            System.out.println("Log " + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "detailPhieuTra";
    }

    @GetMapping(path = {"/adminAddNewReader"})
    public String addNewReader(@ModelAttribute("user") User user, Model model, HttpSession session) {
         user = (User) session.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        model.addAttribute("reader", new Reader());
        return "addNewReader";
    }

    @RequestMapping(path = {"/admin/saveNewReader"})
    public String saveNewReader(@ModelAttribute Reader reader, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        try {
            if (user == null) {
                return "redirect:/login";
            } else if (!user.getRole().equals("ADMIN")) return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if (reader.getName().equals("") || reader.getAddress().equals("") || reader.getTel().equals("") || reader.getEmail().equals("")) {
                model.addAttribute("error", "Không được để trống các trường!!!");
                model.addAttribute("reader", new Reader());
                return "addNewReader";
            }
            readerService.save(reader);
            String username = "";
            String name = removeAccent(reader.getName());
            String[] tmp = name.split(" ");
            if (tmp.length > 1) {
                for (int i = 0; i < tmp.length - 1; i++) {
                    username += tmp[i].substring(0, 1).toLowerCase();
                }
            }
            username += tmp[tmp.length - 1].toLowerCase() + reader.getTel().substring(reader.getTel().length()-3);
            userService.save(new User(reader.getName(), username, "123456", "USER"));
            user = (User) httpSession.getAttribute("user");
            List<Reader> list = readerService.getAll();
            model.addAttribute("success", "Thêm người dùng thành công!!!");
            model.addAttribute("list", list);
            model.addAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "adminReader";
    }

    public static String removeAccent(String str) {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }

}
