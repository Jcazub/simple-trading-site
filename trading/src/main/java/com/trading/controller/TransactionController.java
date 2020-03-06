package com.trading.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

public class TransactionController {

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String getPortfolioPage(Model model, Principal principal)
    {
        return "transactions";
    }

}
