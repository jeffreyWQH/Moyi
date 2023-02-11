package com.rewards.restful.service;

import com.rewards.restful.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer findById(long id);
    List<Customer> findAllCustomers();
    List<Customer> findCustomersByAgeBetween(int from, int to);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(long id);

}
