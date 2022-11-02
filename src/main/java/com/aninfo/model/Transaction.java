package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Account account;

    private Double value;

    public Transaction(){

    }

    public Transaction(Account account, Double value){
        this.account = account;
        this.value = value;
    }

    public Account getAccount(){ return account; }

    public Double getValue(){ return value; }

    
}
