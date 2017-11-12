package edu.hnust.application.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hnust.application.system.ServiceResponse;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("/index")
    @ResponseBody
    public ServiceResponse<Boolean> index(Model model) {
        System.out.println("index");
        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
        serviceResponse.setResult(true);
        return serviceResponse;
    }
}