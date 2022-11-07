package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.exceptions.NoTransactionFoundException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {

        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Double sum) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < sum) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        account.setBalance(account.getBalance() - sum);
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Double sum) {

        if (sum <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        Double sumWithPromo = transactionService.applyPromo(sum);;
        account.setBalance(sumWithPromo + account.getBalance());
        accountRepository.save(account);

        return account;
    }

    @Transactional
    public Transaction createDeposit(Transaction transaction){
        Optional<Account> optionalAccount = accountRepository.findById(transaction.getAccountCbu());

        if(optionalAccount.isEmpty()){
            throw new InvalidTransactionTypeException("Invalid Transaction");
        }

        Transaction deposit = transactionService.createDeposit(transaction);
        Double value = transaction.getValue();
        Account account = deposit(optionalAccount.get().getCbu(), value);

        return deposit;
    }

    @Transactional
    public Transaction createWithdraw(Transaction transaction){
        Optional<Account> optionalAccount = accountRepository.findById(transaction.getAccountCbu());

        if(optionalAccount.isEmpty()){
            throw new InvalidTransactionTypeException("Invalid Transaction");
        }

        Double value = transaction.getValue();
        Transaction deposit = transactionService.createWithdraw(transaction);
        Account account = withdraw(optionalAccount.get().getCbu(), value);

        return deposit;
    }

    public Optional<Transaction> getTransactionById(Long id){
        Optional<Transaction> transaction = transactionService.getTransactionsById(id);
        return transaction;

    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu){
        return transactionService.getTransactionsByCbu(cbu);
    }

    public void deleteTransaction(Long id){
        Transaction transactionToDelete = transactionService.getTransactionsById(id).get();
        undoTransaction(transactionToDelete);
        transactionService.deleteTransaction(id);
    }

    private void undoTransaction(Transaction transaction){
        double transactionValue = transaction.getValue();
        Long accountCbu = transaction.getAccountCbu();

        Account account = accountRepository.findAccountByCbu(accountCbu);
        Double balance = account.getBalance() - transactionValue;

        if(balance < 0) throw new InvalidTransactionTypeException("Cant delete transaction");

        account.setBalance(balance);
        accountRepository.save(account);
    }

}
