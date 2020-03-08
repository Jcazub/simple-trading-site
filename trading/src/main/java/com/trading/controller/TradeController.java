package com.trading.controller;

import com.trading.exceptions.MalformedObjectException;
import com.trading.exceptions.NotEnoughFundsException;
import com.trading.exceptions.StockNotFoundException;
import com.trading.exceptions.UserNotFoundException;
import com.trading.external.StockAPI;
import com.trading.model.TradeRequest;
import com.trading.model.TransactionType;
import com.trading.model.User;
import com.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class TradeController {

    private UserService userService;
    private StockAPI stockAPI;

    @Autowired
    public TradeController(UserService userService, StockAPI stockAPI) {
        this.userService = userService;
        this.stockAPI = stockAPI;
    }

    @RequestMapping(value = "/buyStock", method = RequestMethod.POST)
    public RedirectView buyStock(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) {
        purchaseStock(request, redirectAttributes, principal);
        return new RedirectView("/portfolio", true);
    }

    private void purchaseStock(HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) {
        try {
            sendTradeRequestToMarket(request, principal);
        } catch (UserNotFoundException | MalformedObjectException | NotEnoughFundsException | StockNotFoundException e) {
            logError(e, redirectAttributes);
        }
    }

    private void sendTradeRequestToMarket(HttpServletRequest request, Principal principal) throws UserNotFoundException, StockNotFoundException, NotEnoughFundsException, MalformedObjectException {
        TradeRequest tradeRequest = constructTradeRequest(request, principal);
        stockAPI.buyStock(tradeRequest);
    }

    private TradeRequest constructTradeRequest(HttpServletRequest request, Principal principal) throws UserNotFoundException {
            User user = retrieveUser(principal);
            String stockSymbol = request.getParameter("symbol");
            int amountToBeTraded = getAmountToBeTraded(request);

            return new TradeRequest(user, amountToBeTraded, TransactionType.BUY, stockSymbol);
    }

    private User retrieveUser(Principal principal) throws UserNotFoundException {
        String email = principal.getName();
        return userService.getUserByEmail(email);
    }

    private int getAmountToBeTraded(HttpServletRequest request) {
        String amountBeforeStrip = request.getParameter("amount");

        // regex to strip trailing .00 if whole number input with decimal, i.e. 10.00
        String amount = amountBeforeStrip.replaceAll("\\.[0-9]*", "");

        return Integer.parseInt(amount);
    }

    private void logError(Exception e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }
}
