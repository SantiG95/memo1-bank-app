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
        if(transaction.getValue() == 0){
            throw new DepositZeroException("Value cannot be zero");
        }
        if(transaction.getValue() < 0){
            throw new DepositNegativeSumException("Value cannot be negative");
        }

        Double newValue = this.applyPromo(transaction.getValue());
        transaction.setValue(newValue);
        transaction.setValueAsDeposit();
        return save(transaction);
    }

    public double applyPromo(double value){
        if(value >= 2000){
            Double promotionalBonus = value * 0.1;
            if (promotionalBonus > 500) promotionalBonus = 500.00;
            value += promotionalBonus;
        }
        return value;
    }

    public Transaction createWithdraw(Transaction transaction){
        if(transaction.getValue() == 0){
            throw new DepositZeroException("Value cannot be zero");
        }
        if(transaction.getValue() < 0){
            throw new DepositNegativeSumException("Value cannot be negative");
        }

        transaction.setValueAsWithdraw();
        return save(transaction);
    }

    public Optional<Transaction> getTransactionsById(Long id){
        return this.transactionRepository.findById(id);
    }

    public Collection<Transaction> getTransactionsByCbu(Long accountCbu){
        return this.transactionRepository.findTransactionsByAccountCbu(accountCbu);
    }

    public Transaction save(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }


}