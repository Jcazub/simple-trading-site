package com.trading.security.handlers;

import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.InternalServerErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ExceptionDetails {

    private String details, errorHuman, errorTech, exceptionType;
    private List<String> exceptions404, exceptions500;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public ExceptionDetails(HttpServletRequest request, HttpServletResponse response) {
        this.exceptions404 = new ArrayList<>(
                Arrays.asList(
                        NoHandlerFoundException.class.getName()
                )
        );

        this.exceptions500 = new ArrayList<>(
                Arrays.asList(
                        InternalServerErrorException.class.getName(),
                        NullPointerException.class.getName()
                )
        );

        this.request = request;

        this.response = response;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getErrorHuman() {
        return errorHuman;
    }

    public void setErrorHuman(String errorHuman) {
        this.errorHuman = errorHuman;
    }

    public String getErrorTech() {
        return errorTech;
    }

    public void setErrorTech(String errorTech) {
        this.errorTech = errorTech;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public List<String> getExceptions404() {
        return exceptions404;
    }

    public void setExceptions404(List<String> exceptions404) {
        this.exceptions404 = exceptions404;
    }

    public List<String> getExceptions500() {
        return exceptions500;
    }

    public void setExceptions500(List<String> exceptions500) {
        this.exceptions500 = exceptions500;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionDetails that = (ExceptionDetails) o;
        return Objects.equals(details, that.details) &&
                Objects.equals(errorHuman, that.errorHuman) &&
                Objects.equals(errorTech, that.errorTech) &&
                Objects.equals(exceptionType, that.exceptionType) &&
                Objects.equals(exceptions404, that.exceptions404) &&
                Objects.equals(exceptions500, that.exceptions500) &&
                Objects.equals(request, that.request) &&
                Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(details, errorHuman, errorTech, exceptionType, exceptions404, exceptions500, request, response);
    }
}
