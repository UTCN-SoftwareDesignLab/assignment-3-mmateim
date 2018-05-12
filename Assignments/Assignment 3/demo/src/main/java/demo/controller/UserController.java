package demo.controller;

import demo.Validator.UserValidator;
import demo.entity.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("users", service.getAll());
        model.addAttribute("user", new User());
        System.out.println("UserController : return users-admin.html");
        return "users-admin";
    }

    @RequestMapping(params = "create=", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("user") User user) {
        System.out.println("UserController : create");
        String message;
        try {
            UserValidator validator = new UserValidator(user);
            List<String> errors = validator.validate();
            if (errors.size() != 0) {
                message = errors.toString();
            } else {
                message = "";
            }
        } catch (Exception e) {
            message = "error : some fields are empty";
        }
        if (message.equals("")) {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            String pass = encoder.encodePassword(user.getPassword(), "");
            user.setPassword(pass);
            if (service.create(user) == null) {
                message = "Error sql table user";
            } else {
                message = "";
            }
        }
        if (message.equals("")) {
            model.addAttribute("user", new User());
        } else {
            model.addAttribute("user", user);
        }
        model.addAttribute("users", service.getAll());
        model.addAttribute("message", message);
        return "users-admin";
    }

    @RequestMapping(params = "update=", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("user") User user) {
        System.out.println("UserController : update");
        String message;
        try {
            UserValidator validator = new UserValidator(user);
            List<String> errors = validator.validate();
            if (errors.size() != 0) {
                message = errors.toString();
            } else {
                message = "";
            }
        } catch (Exception e) {
            message = "error : some fields are empty";
        }
        if (message.equals("")) {
            ShaPasswordEncoder encoder = new ShaPasswordEncoder();
            String pass = encoder.encodePassword(user.getPassword(), "");
            user.setPassword(pass);
            if (service.update(user) == null) {
                message = "Error sql table user";
            } else {
                message = "";
            }
        }
        if(message.equals("")) {
            model.addAttribute("user", new User());
            return "login";
        }
        else {
            model.addAttribute("users", service.getAll());
            model.addAttribute("message", message);
            model.addAttribute("user", user);
            return "users-admin";
        }
    }

    @RequestMapping(params = "delete=", method = RequestMethod.GET)
    public String deleteUser(Model model, @RequestParam("deleteId") Integer deleteId) {
        System.out.println("UserController : delete");
        String message = "";
        if (deleteId != null) {
            service.delete(deleteId);
        } else {
            message = "Id field is empty";
        }
        model.addAttribute("user", new User());
        if(message.equals("")){
            return "login";
        }
        else {
            model.addAttribute("users", service.getAll());
            model.addAttribute("message", message);
            return "books-admin";
        }
    }
}
