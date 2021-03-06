package com.mendel.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Transaction {

    @NotNull
    private Long id;
    @NotNull
    private Double amount;
    @NotEmpty
    private String type;
    private Long parentId;
}
