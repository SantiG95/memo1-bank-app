package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.DepositZeroException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Transaction;
import com.aninfo.model.Account;
import com.aninfo.repository.TransactionRepository;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private AccountService accountService;

    public Transaction createDeposit(Transaction transaction){
        if(transaction.getValue() < 0){
            throw new DepositNegativeSumException("Value cannot be negative or zero");
        }
        return transactionRepository.save(transaction);
    }

    public Transaction createWithdraw(Transaction transaction){
        if(transaction.getValue() < 0){
            throw new DepositNegativeSumException("Value cannot be negative or zero");
        }
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionsById(Long id){
        return this.transactionRepository.findById(id);
    }

    public Optional<Transaction> getTransactionsByCbu(Long accountCbu){
        return this.accountService.getTransactionsByCbu(accountCbu);
    }

    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }


}