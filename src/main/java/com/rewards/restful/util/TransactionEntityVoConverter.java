package com.rewards.restful.util;

import com.rewards.restful.entity.TransactionEntity;
import com.rewards.restful.model.Transaction;

public class TransactionEntityVoConverter {
    public static Transaction convertEntityToVo(TransactionEntity entity) {
        if (entity == null) return null;
        Transaction transaction = new Transaction();
        transaction.setAmount(entity.getAmount());
        transaction.setId(entity.getId());
        transaction.setPoints(entity.getPoints());
        transaction.setTimestamp(entity.getTimestamp());
        transaction.setCustomer(CustomerEntityVoConverter.convertEntityToVoWithoutRelation(entity.getCustomerEntity()));
        return transaction;

    }

    public static Transaction convertEntityToVoWithoutRelation(TransactionEntity entity){
        if (entity == null) return null;
        Transaction transaction = new Transaction();
        transaction.setAmount(entity.getAmount());
        transaction.setId(entity.getId());
        transaction.setPoints(entity.getPoints());
        transaction.setTimestamp(entity.getTimestamp());
        return transaction;

    }
    public static TransactionEntity convertVoToEntity(Transaction transaction) {
        if (transaction == null) return null;
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCustomerEntity(CustomerEntityVoConverter.convertVoToEntity(transaction.getCustomer()));
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setTimestamp(transaction.getTimestamp());
        transactionEntity.setPoints(transaction.getPoints());
        return transactionEntity;

    }
}
