package com.rewards.restful.service;

import com.rewards.restful.dao.CustomerRepository;
import com.rewards.restful.entity.CustomerEntity;
import com.rewards.restful.model.Customer;
import com.rewards.restful.util.CustomerEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static com.rewards.restful.util.CustomerEntityVoConverter.convertEntityToVo;
import static com.rewards.restful.util.CustomerEntityVoConverter.convertVoToEntity;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepo;
    @Override
    public Customer findById(long id) {
        CustomerEntity customerEntity = customerRepo.findById(id).orElse(null);
        return convertEntityToVo(customerEntity);
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<CustomerEntity> customers = customerRepo.findAll();
        return customers.stream().map(CustomerEntityVoConverter::convertEntityToVoWithoutRelation).collect(Collectors.toList());
    }


    @Override
    public List<Customer> findCustomersByAgeBetween(int from, int to) {
        List<CustomerEntity> customers = customerRepo.findByAgeBetween(from, to);
        return customers.stream().map(CustomerEntityVoConverter::convertEntityToVoWithoutRelation).collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepo.saveAndFlush(convertVoToEntity(customer));
        return convertEntityToVo(customerEntity);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepo.saveAndFlush(convertVoToEntity(customer));
        return convertEntityToVo(customerEntity);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepo.deleteById(id);
    }


}
