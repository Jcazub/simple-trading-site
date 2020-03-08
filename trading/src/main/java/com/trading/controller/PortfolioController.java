package com.trading.controller;

import com.trading.exceptions.StockNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.model.User;
import com.trading.model.UserStock;
import com.trading.service.UserService;
import com.trading.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class PortfolioController {

    private UserService userService;
    private UserStockService userStockService;

    @Autowired
    public PortfolioController(UserService userService, UserStockService userStockService) {
        this.userService = userService;
        this.userStockService = userStockService;
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public String getPortfolioPage(Model model, Principal principal)
    {
        getPortfolioPageInfo(model, principal);
        return "portfolio";
    }

    private void getPortfolioPageInfo(Model model, Principal principal) {
        try {
            getPortfolio(model, principal);
        } catch (UserNotFoundException | StockNotFoundException e) {
            logError(e, model);
        }
    }

    private void getPortfolio(Model model, Principal principal) throws UserNotFoundException, StockNotFoundException {
        String email = principal.getName();
        User user = userService.getUserByEmail(email);

        List<UserStock> userStocks = userStockService.getStocksByUserDescendingInPrice(user.getUserId());
        user.setStocksOwned(userStocks);

        model.addAttribute("user", user);
        model.addAttribute("totalPortfolioValue", user.getTotalPorfolioValue());
        model.addAttribute("userStocks", user.getStocksOwned());
    }

    private void logError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
    }
}
