package com.trading.security.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void handleNotFound(HttpSession session, HttpServletRequest req, HttpServletResponse httpServletResponse, Exception ex) throws IOException {
        httpServletResponse.sendRedirect("/error");
    }

}
