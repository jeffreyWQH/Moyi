package com.rewards.restful.service;

import com.rewards.restful.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction findById(long id);
    List<Transaction> findAllTransactions();

    Transaction saveTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(long id);

}
