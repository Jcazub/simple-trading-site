package com.trading.controller;

import com.trading.exceptions.MalformedObjectException;
import com.trading.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.trading.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
public class LoginController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginFor() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");

        String clearPassword = request.getParameter("password");
        String hashPassword = passwordEncoder.encode(clearPassword);

        BigDecimal currentBalance = new BigDecimal(5000);

        User user = new User(firstName, lastName, email, currentBalance, hashPassword);

        //TODO: when roleService is implemented, add code to edit user roles

        try {
            userService.addUser(user);
        } catch (MalformedObjectException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }
}
