package com.trading.security.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

    public CustomExceptionResolver() {
        // Turn logging on by default
        setWarnLogCategory(getClass().getName());
    }

    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
        return "MVC exception: " + e.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
                                              Object handler, Exception ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(request, response);

        // Log exception
        ex.printStackTrace();
        exceptionDetails.setExceptionType(ex.getClass().getCanonicalName());

        // Get the ModelAndView to use
        ModelAndView mav = super.doResolveException(request, response, handler, ex);

        setExceptionDetails(exceptionDetails);
        updateModelWithExceptionDetails(mav, exceptionDetails);
        return mav;
    }

    private void setExceptionDetails(ExceptionDetails exceptionDetails) {
        String exceptionType = exceptionDetails.getExceptionType();

        if (exceptionDetails.getExceptions404().contains(exceptionType)) {
            set404ExceptionDetails(exceptionDetails);
        } else if (exceptionDetails.getExceptions500().contains(exceptionType)) {
            set500ExceptionDetails(exceptionDetails);
        } else {
            setGeneralExceptionDetails(exceptionDetails);
        }
    }

    private void set404ExceptionDetails(ExceptionDetails exceptionDetails) {
        exceptionDetails.setErrorHuman("We cannot find the page you are looking for");
        exceptionDetails.setErrorTech("Page not found");
        exceptionDetails.setDetails(String.format("The page %s cannot be found",
                exceptionDetails.getRequest().getRequestURL()));
    }

    private void set500ExceptionDetails(ExceptionDetails exceptionDetails) {
        exceptionDetails.setErrorHuman("We cannot currently serve the page you request");
        exceptionDetails.setErrorTech("Internal error");
        exceptionDetails.setDetails("The current page refuses to load due to an internal error");
    }

    private void setGeneralExceptionDetails(ExceptionDetails exceptionDetails) {
        exceptionDetails.setErrorHuman("We cannot serve the current page");
        exceptionDetails.setErrorTech("General error");
        exceptionDetails.setDetails("A generic error prevents from serving the page");
    }

    private void updateModelWithExceptionDetails(ModelAndView mav, ExceptionDetails exceptionDetails) {
        // Make more information available to the view - note that SimpleMappingExceptionResolver adds the exception already
        mav.addObject("url", exceptionDetails.getRequest().getRequestURL());
        mav.addObject("timestamp", new Date());
        mav.setViewName("customError");
        mav.addObject("error_human", exceptionDetails.getErrorHuman());
        mav.addObject("error_tech", exceptionDetails.getErrorTech());
        mav.addObject("exception", exceptionDetails.getDetails());
        mav.addObject("status", exceptionDetails.getResponse().getStatus());
    }

}
