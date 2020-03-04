package controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;

@Controller
public class ErrorController {

    @RequestMapping(value = "/error")
    public String customError(HttpServletRequest request,
                              Model model) {
        // retrieve some useful information from the request
        Integer statusCode
                = (Integer) request.getAttribute("javax.servlet.error.status_code");
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        String exceptionMessage = httpStatus.getReasonPhrase();

        String requestUri
                = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        // format the message for the view
        String message = MessageFormat.format("{0} returned for {1}: {2}",
                statusCode, requestUri, exceptionMessage);

        model.addAttribute("errorMessage", message);
        return "customError";
    }
}
