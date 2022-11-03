package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long accountCbu;

    private Double value;

    public Transaction(){

    }

    public Transaction(Long accountCbu, Double value){
        this.accountCbu = accountCbu;
        this.value = value;
    }

    public Long getId(){
        return id;
    }

    public Long getAccountCbu(){
        return accountCbu;
    }

    public Double getValue(){
        return value;
    }
}
