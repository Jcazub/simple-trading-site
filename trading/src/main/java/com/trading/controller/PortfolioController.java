package com.trading.controller;

import com.trading.external.StockAPI;
import com.trading.model.UserStock;
import com.trading.service.UserService;
import com.trading.service.UserStockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class PortfolioController {

    private UserService userService;
    private UserStockService userStockService;
    private StockAPI stockAPI;

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public String getPortfolioPage(Model model, Principal principal)
    {
        return "portfolio";
    }
}
