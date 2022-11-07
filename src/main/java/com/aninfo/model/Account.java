package com.aninfo.model;

import com.aninfo.exceptions.NoTransactionFoundException;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    //------------------------------------------------------------------------------------------------------------------
    /*public List<Transaction> transactionList;

    public void addTransaction(Transaction newTransaction){
        transactionList.add(newTransaction);
    }

    public List<Transaction> getTransactions(){
        return this.transactionList;
    }

    public void deleteTransaction(Long id){
        Transaction transaction = null;
        for (Transaction anTransaction: transactionList){
            if(anTransaction.getId() == id){
                transaction = anTransaction;
                break;
            }

        }

        if(transaction == null) throw new NoTransactionFoundException("No transaction found");
        
        transactionList.remove(
                transactionList.indexOf(transaction)
        );
    }
*/
    //------------------------------------------------------------------------------------------------------------------

}
