package com.rewards.restful.util;

import com.rewards.restful.entity.CustomerEntity;
import com.rewards.restful.model.Customer;

import java.util.stream.Collectors;

public class CustomerEntityVoConverter {

    public static Customer convertEntityToVo(CustomerEntity entity) {
        if (entity == null) return null;

        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setRewards(entity.getRewards());
        customer.setAge(entity.getAge());
        customer.setTransactionList(entity.getTransactionEntityList().stream().map(TransactionEntityVoConverter::convertEntityToVoWithoutRelation).collect(Collectors.toList()));
        return customer;
    }
    public static Customer convertEntityToVoWithoutRelation(CustomerEntity entity){
        if (entity == null) return null;
        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setRewards(entity.getRewards());
        customer.setAge(entity.getAge());
        return customer;

    }

    public static CustomerEntity convertVoToEntity(Customer customer) {
        if (customer == null) return null;
        CustomerEntity customerEntity =  new CustomerEntity();
        customerEntity.setAge(customer.getAge());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setName(customer.getName());
        customerEntity.setRewards(customer.getRewards());

        return customerEntity;
    }


}
