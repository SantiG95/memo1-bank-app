package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
//import com.aninfo.exceptions.DepositZeroException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Transaction;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findById(long id){
        return transactionRepository.findById(id);
    }

    public Optional<Transaction> findByCbu(Long accountCbu){
        //TODO buscar por cuenta
        return null;
    }

    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id)
        ;
    }


}