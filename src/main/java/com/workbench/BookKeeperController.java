package com.workbench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

/**
 * Created by RdDvls on 1/4/17.
 */
@Controller
public class BookKeeperController {
    @Autowired
    ReaderRepository readers;
    @Autowired
    BookRepository books;
    @Autowired
    FriendRepository friends;

    @PostConstruct
    public void init(){
        Reader newReader = new Reader("Clay","Strickland","RdDvls","pass");
        readers.save(newReader);
        Friend newFriend = new Friend(readers.findByUserName("RdDvls"), "FriendName", "StringEmail");
        friends.save(newFriend);
        BookItem newBookItem1 = new BookItem(readers.findByUserName("RdDvls"),"Author1","Title1");
        BookItem newBookItem2 = new BookItem(readers.findByUserName("RdDvls"),"Author2", "Title2");
        books.save(newBookItem1);
        books.save(newBookItem2);
    }
    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        if (session.getAttribute("user")!=null){
            model.addAttribute("user", session.getAttribute("user"));
        }
        Reader retrievedReader = (Reader)session.getAttribute("user");
        return "index";
    }

    @RequestMapping (path = "/register",method = RequestMethod.POST)
    public String register(String firstName, String lastName, String password, String userName, HttpSession session) throws Exception{
        Reader newReader = readers.findByUserName(userName);
        if(newReader == null){
            newReader = new Reader(firstName,lastName,userName,password);
            readers.save(newReader);
        }
        session.setAttribute("user", newReader);
        return "redirect:/";
    }
    @RequestMapping (path = "/login", method = RequestMethod.POST)
    public String login(String userName, String password, HttpSession session)throws Exception{
        Reader reader = readers.findByUserName(userName);
            if(reader == null){
                throw new Exception("Create user first");
            } else if (!password.equals(reader.getPassword())){
                throw new Exception("Incorrect Password");
            }
            session.setAttribute("user",reader);
            return "redirect:/";


    }
    @RequestMapping(path = "/logout",method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
