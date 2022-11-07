package com.aninfo.model;

import com.aninfo.repository.AccountRepository;
import com.aninfo.service.AccountService;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    public void setValue(Double amount){this.value = amount;}

    public void setValueAsDeposit() {
    }

    public void setValueAsWithdraw() {
        this.value *= -1;
    }
}
