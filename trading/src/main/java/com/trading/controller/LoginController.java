package com.trading.controller;

import com.trading.exceptions.EmailAlreadyInUseException;
import com.trading.exceptions.MalformedObjectException;
import com.trading.model.User;
import com.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RedirectView addUser(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User user = createUser(request);
        return addUserToDatabase(user, redirectAttributes);
        //TODO: when roleService is implemented, add code to edit user roles
    }

    private User createUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");

        String clearPassword = request.getParameter("password");
        String hashPassword = passwordEncoder.encode(clearPassword);

        BigDecimal currentBalance = new BigDecimal(5000);

        User user = new User(firstName, lastName, email, currentBalance, hashPassword);

        return user;
    }

    private RedirectView addUserToDatabase(User user, RedirectAttributes redirectAttributes) {
        try {
            return persistUser(user);
        } catch (MalformedObjectException | EmailAlreadyInUseException e) {
            return logError(e, redirectAttributes);
        }
    }

    private RedirectView persistUser(User user) throws EmailAlreadyInUseException, MalformedObjectException {
        userService.addUser(user);
        return new RedirectView("/login", true);
    }

    private RedirectView logError(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return new RedirectView("/register", true);
    }
}
