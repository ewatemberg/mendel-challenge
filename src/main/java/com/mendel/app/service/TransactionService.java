package com.mendel.app.service;

import com.mendel.app.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    public Transaction save(Transaction transaction){
        //TODO: persist info
        return transaction;
    }
}
