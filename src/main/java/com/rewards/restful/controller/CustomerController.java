package com.rewards.restful.controller;

import com.rewards.restful.exception.AgeNotValidException;
import com.rewards.restful.exception.CustomerNotFoundException;
import com.rewards.restful.model.Customer;
import com.rewards.restful.model.ErrorResponse;
import com.rewards.restful.model.ResponseMessage;
import com.rewards.restful.service.CustomerService;
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
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

//    @GetMapping("/customers")
//    public ResponseEntity<?> getAllCustomers() {
//        List<Customer> customers = customerService.findAllCustomers();
//        return new ResponseEntity<>(customers, HttpStatus.valueOf(200));
//    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomersByAgeBetween(@RequestParam(required = false, defaultValue = "0") Integer from,
                                                     @RequestParam(required = false, defaultValue = "200") Integer to) {
        if (from < 0) {
            throw new AgeNotValidException("Age should be at least 0");
        }
        logger.info("get customers by age between " + from + " " + to);
        List<Customer> customers = customerService.findCustomersByAgeBetween(from, to);
        return new ResponseEntity<>(customers, HttpStatus.valueOf(200));
    }

    @PostMapping("/customers")
    public ResponseEntity<ResponseMessage> createCustomer(@Validated @RequestBody Customer customer) {
//        logger.debug("create studnet", customer);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(new ResponseMessage("customer has been created", savedCustomer), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @Validated @RequestBody Customer customer) {
        Customer currentCustomer = customerService.findById(id);
        if (currentCustomer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        currentCustomer.setName(customer.getName());
        currentCustomer.setRewards(customer.getRewards());
        currentCustomer.setEmail(customer.getEmail());

        customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(new ResponseMessage("customer has been deleted", customer), HttpStatus.OK);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerCustomerNotFound(Exception ex) {
        logger.error("Can't find customer");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
