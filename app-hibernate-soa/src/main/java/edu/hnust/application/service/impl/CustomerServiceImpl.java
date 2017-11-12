package edu.hnust.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hnust.application.dao.CommonDao;
import edu.hnust.application.orm.Customer;
import edu.hnust.application.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CommonDao commonDao;
    
    @Override
    public Customer validateCustomer(String loginName, String password) {
        return null;
    }
    
    @Override
    public Customer fetchCustomrtById(Integer id) {
        return (Customer)commonDao.load(Customer.class, id);
    }
}