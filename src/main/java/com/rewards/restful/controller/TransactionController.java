package com.rewards.restful.controller;

import com.rewards.restful.exception.AgeNotValidException;
import com.rewards.restful.exception.TransactionNotFoundException;
import com.rewards.restful.model.Customer;
import com.rewards.restful.model.ErrorResponse;
import com.rewards.restful.model.ResponseMessage;
import com.rewards.restful.model.Transaction;
import com.rewards.restful.service.CustomerService;
import com.rewards.restful.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@ResponseBody
@RequestMapping("/api")
public class TransactionController {

    private TransactionService transactionService;

    private CustomerService customerService;
    @Autowired
    public TransactionController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction doesn't exist");
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }


    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactionsByAgeBetween() {

        List<Transaction> transactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.valueOf(200));
    }

    @PostMapping("/transactions")
    public ResponseEntity<ResponseMessage> createTransaction(@Validated @RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        int amount = transaction.getAmount();
        int points;
        if(amount > 50 && amount <= 100) {
            points = amount - 50;
        }
        else if(amount > 100){
            points = 2 * (amount -100) + 50;
        }
        else {
            points = 0;
        }

        savedTransaction.setAmount(amount);
        savedTransaction.setPoints(points);
        savedTransaction.setTimestamp(System.currentTimeMillis());
        savedTransaction.setCustomer_id(transaction.getCustomer_id());

        return new ResponseEntity<>(new ResponseMessage("transaction has been created", savedTransaction), HttpStatus.CREATED);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @Validated @RequestBody Transaction transaction) {
        Transaction currentTransaction = transactionService.findById(id);
        if (currentTransaction == null) {
            throw new TransactionNotFoundException("Transaction doesn't exist");
        }
        long customer_id = transaction.getCustomer_id();

        if(customer_id != currentTransaction.getCustomer_id()){
            throw new TransactionNotFoundException("Transaction should not change customer");
        }

        Customer customer = customerService.findById(customer_id);

        int lastPoints = currentTransaction.getPoints();

        int amount = transaction.getAmount();
        int points;
        if(amount > 50 && amount <= 100) {
            points = amount - 50;
        }
        else if(amount > 100){
            points = 2 * (amount -100) + 50;
        }
        else {
            points = 0;
        }

        currentTransaction.setPoints(points);
        currentTransaction.setAmount(transaction.getAmount());
        customer.setRewards(customer.getRewards() + lastPoints - points);


        customerService.updateCustomer(customer);
        transactionService.updateTransaction(currentTransaction);
        return new ResponseEntity<>(currentTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<ResponseMessage> deleteTransaction(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction doesn't exist");
        }
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(new ResponseMessage("transaction has been deleted", transaction), HttpStatus.OK);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerTransactionNotFound(Exception ex) {
        logger.error("Can't find transaction");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
