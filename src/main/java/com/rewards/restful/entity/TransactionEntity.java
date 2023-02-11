package com.rewards.restful.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "points")
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    public TransactionEntity(){

    }
    public TransactionEntity(Long id, Long timestamp, Integer amount, Integer points, CustomerEntity customerEntity) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.points = points;
        this.customerEntity = customerEntity;
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

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }


}
