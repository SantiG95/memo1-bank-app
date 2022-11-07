package com.aninfo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TransactionController {

    /*private final TransactionRepository transactionRepository;

    TransactionController(TransactionRepository repository){
        this.transactionRepository = repository;
    }

    @GetMapping("/transactions")
    List<EntityModel<Transaction>> employees = transactionRepository.findAll().stream()
            .map(transaction -> EntityModel.of(transaction,
                    linkTo(methodOn(TransactionController.class).one(transaction.getId())).withSelfRel(),
                    linkTo(methodOn(TransactionController.class).all()).withRel("transaction")))
            .collect(Collectors.toList());
    }

    @PostMapping("/transactions")
    Transaction newTransaction(@RequestBody Transaction newTransaction){
        return transactionRepository.save(newTransaction);
    }

    @GetMapping("/transactions/{id}")
    EntityModel<Transaction> getTransactionById(@PathVariable Long id){
        return transactionRepository.findTransactionById(id);
    }

    @GetMapping("/transactions/{id}")
    Transaction getTransactionByAccount(@PathVariable Account account) {
        return transactionRepository.findTransactionByAccount(account);
    }

    @DeleteMapping("/transactions/{id}")
    void deleteTransaction(@PathVariable Long id) {

        transactionRepository.deleteById(id);
    }*/
}
