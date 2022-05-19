package com.mendel.app.service;

import com.mendel.app.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TransactionService {

    public Transaction save(Transaction transaction){
        //TODO: persist info
        return transaction;
    }

    public List<Long> findByType(String type){
        //TODO: get all ids
        return Arrays.asList(10L,11L,12L);
    }

    public Map<String,Double> sumarizeByParentId(Long parentId){
        //TODO: sumarize all transacction by parent
        Map sum = new HashMap<String,Double>();
        sum.put("sum", new Random().nextDouble());
        return sum;
    }
}
