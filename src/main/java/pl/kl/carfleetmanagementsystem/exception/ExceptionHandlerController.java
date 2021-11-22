package pl.kl.carfleetmanagementsystem.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String handleFleetCardExpirationDateException() {
        return "error/400";
    }
}
