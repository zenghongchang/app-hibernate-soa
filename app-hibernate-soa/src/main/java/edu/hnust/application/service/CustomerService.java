package edu.hnust.application.service;

import edu.hnust.application.orm.Customer;

public interface CustomerService {
	public Customer validateCustomer(String loginName, String password);
	public Customer fetchCustomrtById(Integer id);
}
