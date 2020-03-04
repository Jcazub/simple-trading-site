package controller;

import exceptions.MalformedObjectException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

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
    public String showLoginForm(Model model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        String clearPassword = request.getParameter("password");
        String hashPassword = passwordEncoder.encode(clearPassword);

        BigDecimal currentBalance = new BigDecimal(5000);

        User user = new User(firstName, lastName, email, currentBalance, hashPassword);

        //TODO: when roleService is implemented, add code to edit user roles

        try {
            userService.addUser(user);
        } catch (MalformedObjectException e) {
            return "error";
        }

        return "redirect:/login";
    }
}
