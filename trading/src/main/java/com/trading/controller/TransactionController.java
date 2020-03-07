package com.trading.controller;

import com.trading.exceptions.TransactionNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.model.Transaction;
import com.trading.model.User;
import com.trading.service.TransactionService;
import com.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class TransactionController {

    private UserService userService;
    private TransactionService transactionService;

    @Autowired
    public TransactionController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String getTransactionsPage(Model model, Principal principal)
    {
        String email = principal.getName();

        try {
            User user = userService.getUserByEmail(email);
            List<Transaction> userTransactions = transactionService.getTransactionsByUserDescendingDateTime(user.getUserId());
            model.addAttribute("transactions", userTransactions);
        } catch (UserNotFoundException | TransactionNotFoundException e) {
            //TODO: implement exception handling
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "transactions";
    }

}
