package com.rewards.restful.service;

import com.rewards.restful.dao.TransactionRepository;
import com.rewards.restful.entity.TransactionEntity;
import com.rewards.restful.model.Transaction;
import com.rewards.restful.util.TransactionEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rewards.restful.util.TransactionEntityVoConverter.convertEntityToVo;
import static com.rewards.restful.util.TransactionEntityVoConverter.convertVoToEntity;



@Service("transactionService")
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepo;
    @Override
    public Transaction findById(long id) {
        TransactionEntity transactionEntity = transactionRepo.findById(id).orElse(null);
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        List<TransactionEntity> transactions = transactionRepo.findAll();
        return transactions.stream().map(TransactionEntityVoConverter::convertEntityToVoWithoutRelation).collect(Collectors.toList());
    }




    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = transactionRepo.saveAndFlush(convertVoToEntity(transaction));
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        TransactionEntity transactionEntity = transactionRepo.saveAndFlush(convertVoToEntity(transaction));
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public void deleteTransactionById(long id) {
        transactionRepo.deleteById(id);
    }


}
