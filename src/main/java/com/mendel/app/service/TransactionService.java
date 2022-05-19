package com.mendel.app.service;

import com.mendel.app.domain.Transaction;
import com.mendel.app.exception.ApiError;
import com.mendel.app.exception.TypeNotFoundException;
import com.mendel.app.service.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    Set<Transaction> storeTx;

    public TransactionDTO save(Long id, TransactionDTO dto) {
        log.debug("Storing a transaction: {} with id:{}", dto, id);
        storeTx.add(new Transaction().builder()
                .id(id)
                .amount(dto.getAmount())
                .parentId(dto.getParentId())
                .type(dto.getType())
                .build());
        return dto;
    }

    public List<Long> findByType(String type) {
        log.debug("Get all transactions by type:{}", type);
        List<Long> result = storeTx.stream()
                .filter(tx -> Objects.nonNull(tx.getType()))
                .filter(tx -> tx.getType().equals(type))
                .map(it -> it.getId()).collect(Collectors.toList());
        return result;
    }

    public Map<String, Double> sumarizeByParentId(Long parentId) {
        log.debug("Sumarize all transactions by parent id:{}", parentId);
        List<Transaction> filterByParentId = storeTx.stream()
                .filter(tx -> Objects.nonNull(tx.getParentId()))
                .filter(tx -> tx.getParentId().equals(parentId))
                .collect(Collectors.toList());
        if (filterByParentId.size() == 0)
            throw new TypeNotFoundException(new ApiError("No existen transacciones almacenadas con el padre id: ".concat(parentId.toString())));
        Double total = filterByParentId.stream().mapToDouble(tx -> tx.getAmount()).sum();
        Map sum = new HashMap<String, Double>();
        sum.put("sum", total);
        return sum;
    }
}
