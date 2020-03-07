package com.trading.controller;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.NotEnoughFundsException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.external.StockAPI;
import com.trading.model.Stock;
import com.trading.model.TradeRequest;
import com.trading.model.TransactionType;
import com.trading.model.User;
import com.trading.service.UserService;
import com.trading.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class TradeController {

    private UserService userService;
    private UserStockService userStockService;
    private StockAPI stockAPI;

    @Autowired
    public TradeController(UserService userService, UserStockService userStockService, StockAPI stockAPI) {
        this.userService = userService;
        this.userStockService = userStockService;
        this.stockAPI = stockAPI;
    }

    @RequestMapping(value = "/buyStock", method = RequestMethod.POST)
    public RedirectView buyStock(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) {

        String email = principal.getName();
        RedirectView redirectView = new RedirectView("/portfolio", true);

        try {
            User user = userService.getUserByEmail(email);
            String stockSymbol = request.getParameter("symbol");
            int amountToBeTraded = Integer.parseInt(request.getParameter("amount"));

            TradeRequest tradeRequest = new TradeRequest(user, amountToBeTraded, TransactionType.BUY, stockSymbol);

            stockAPI.buyStock(tradeRequest);
        } catch (UserNotFoundException | MalformedObjectException | NotEnoughFundsException | StockNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }



        return redirectView;
    }
}
