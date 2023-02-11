package com.rewards.restful.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class Customer {

    private Long id;
    @NotBlank
    private String name;

    @Email
    private String email;

    @Positive
    private Integer rewards;

    @Positive
    private Integer age;

    private List<Transaction> transactionList;
    public Customer() {
    }

    public Customer(Long id, String name, String email, Integer rewards, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rewards = rewards;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
