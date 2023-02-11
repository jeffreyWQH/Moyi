package com.rewards.restful.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Transaction {
    private Long id;


    private Long timestamp;

    @Min(1)
    private Integer amount;

    private Integer points;

    @NotNull
    private Long customer_id;

    private Customer customer;

    public Transaction(){}
    public Transaction(Long id, Long timestamp, Integer amount, Integer points, long customer_id) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.points = points;
        this.customer_id = customer_id;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }


    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
