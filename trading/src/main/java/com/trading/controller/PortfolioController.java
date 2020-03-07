package com.trading.controller;

import com.trading.exceptions.StockNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.external.StockAPI;
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
    private StockAPI stockAPI;

    @Autowired
    public PortfolioController(UserService userService, UserStockService userStockService, StockAPI stockAPI) {
        this.userService = userService;
        this.userStockService = userStockService;
        this.stockAPI = stockAPI;
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public String getPortfolioPage(Model model, Principal principal)
    {
        String email = principal.getName();

        try {
            User user = userService.getUserByEmail(email);

            List<UserStock> userStocks = userStockService.getStocksByUserDescendingInPrice(user.getUserId());
            user.setStocksOwned(userStocks);

            model.addAttribute(user);
            model.addAttribute("totalPortfolioValue", user.getTotalPorfolioValue());
            model.addAttribute("userStocks", user.getStocksOwned());

        } catch (UserNotFoundException | StockNotFoundException e) {
            e.printStackTrace();
        }
        return "portfolio";
    }
}
