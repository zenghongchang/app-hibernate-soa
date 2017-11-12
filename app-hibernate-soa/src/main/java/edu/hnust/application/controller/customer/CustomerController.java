package edu.hnust.application.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.hnust.application.orm.Customer;
import edu.hnust.application.service.CustomerService;
import edu.hnust.application.system.ServiceResponse;
import edu.hnust.application.system.ServiceResponseCode;
import edu.hnust.application.system.ServiceResponseDescription;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping("/validateCustomer")
    @ResponseBody
    public ServiceResponse<Customer> validateCustomer(String loginName, String password) {
        ServiceResponse<Customer> response = new ServiceResponse<Customer>();
        
        if (null == loginName || null == password) {
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription(ServiceResponseDescription.SERVER_ERROR);
            response.setResult(null);
        }
        try {
            Customer c = customerService.validateCustomer(loginName, password);
            response.setCode(ServiceResponseCode.SUCCESS);
            response.setDescription(ServiceResponseDescription.SUCCESS);
            response.setResult(c);
        } catch (Exception e) {
            response.setCode(ServiceResponseCode.SERVER_ERROR);
            response.setDescription(ServiceResponseDescription.SERVER_ERROR);
            response.setResult(null);
            e.printStackTrace();
        }
        return response;
    }
    
    /**
     * 根据ID检索客户信息
     *
     * @param id
     * @return
     * @author Henry(fba02)
     * @version [版本号, 2017年9月22日]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryCustomerById", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponse<Customer> queryCustomerById(String id) {
        ServiceResponse<Customer> response = new ServiceResponse<Customer>();
        try {
            Customer customer = customerService.fetchCustomrtById(Integer.valueOf(id));
            response.setResult(customer);
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult(null);
        }
        return response;
    }
}