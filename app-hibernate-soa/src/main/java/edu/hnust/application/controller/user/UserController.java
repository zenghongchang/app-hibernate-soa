package edu.hnust.application.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hnust.application.orm.user.User;
import edu.hnust.application.service.user.UserService;
import edu.hnust.application.system.ReturnPageData;
import edu.hnust.application.system.ServiceResponse;
import edu.hnust.application.system.ServiceResponseCode;
import edu.hnust.application.system.ServiceResponseDescription;

@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/index")
    @ResponseBody
    public ServiceResponse<Boolean> index(Model model) {
        System.out.println("index");
        ServiceResponse<Boolean> serviceResponse = new ServiceResponse<Boolean>();
        serviceResponse.setResult(true);
        return serviceResponse;
    }
    
    /**
     * 用户登陆校验
     * 
     * @param requstArgs
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2017年11月18日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "validateUser", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<User> validateUser(@RequestBody Map<String, String> requstArgs) {
        ServiceResponse<User> response = new ServiceResponse<User>();
        try {
            String loginName = requstArgs.get("loginName");
            String password = requstArgs.get("password");
            User user = userService.validateUser(loginName, password);
            response.setResult(user);
            response.setCode(ServiceResponseCode.SUCCESS);
            response.setDescription(ServiceResponseDescription.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(null);
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription(ServiceResponseDescription.ERROR);
        }
        return response;
    }
    
    @RequestMapping(value = "pageQueryUser", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<ReturnPageData<User>> pageQueryUser(@RequestBody Map<String, Object> requstArgs) {
        ServiceResponse<ReturnPageData<User>> response = new ServiceResponse<ReturnPageData<User>>();
        try {
            
            ReturnPageData<User> pageData = userService.pageQueryUser(requstArgs);
            response.setResult(pageData);
            response.setCode(ServiceResponseCode.SUCCESS);
            response.setDescription(ServiceResponseDescription.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(null);
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription(ServiceResponseDescription.ERROR);
        }
        return response;
    }
}