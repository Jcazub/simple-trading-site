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
        getTransactionsPageInfo(model, principal);
        return "transactions";
    }

    private void getTransactionsPageInfo(Model model, Principal principal) {
        try {
            getTransactions(model, principal);
        } catch (UserNotFoundException | TransactionNotFoundException e) {
            logError(e, model);
        }
    }

    private void getTransactions(Model model, Principal principal) throws UserNotFoundException, TransactionNotFoundException {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);
        List<Transaction> userTransactions = transactionService.getTransactionsByUserDescendingDateTime(user.getUserId());
        model.addAttribute("transactions", userTransactions);
    }

    private void logError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
    }

}
