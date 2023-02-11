package com.rewards.restful.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "rewards")
    private Integer rewards;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "customerEntity")
    private List<TransactionEntity> transactionEntityList;

    public CustomerEntity() {
    }
    public CustomerEntity(Long id, String name, Integer rewards, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.rewards = rewards;
        this.email = email;
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

    public List<TransactionEntity> getTransactionEntityList() {
        return transactionEntityList;
    }

    public void setTransactionEntityList(List<TransactionEntity> transactionEntityList) {
        this.transactionEntityList = transactionEntityList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
