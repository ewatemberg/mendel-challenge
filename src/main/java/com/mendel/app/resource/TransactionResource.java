package com.mendel.app.resource;

import com.mendel.app.config.ApiVersion;
import com.mendel.app.domain.Transaction;
import com.mendel.app.exception.ApiError;
import com.mendel.app.service.TransactionService;
import com.mendel.app.util.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Transacciones", description = "operaciones asociadas a transacciones.")
@Slf4j
@RestController
@RequestMapping("/api")
public class TransactionResource {

    public static final String PATH = "/transactions";
    public static final String PATH_ID = PATH + "/{id}";

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Crea una nueva transaccion.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = Messages.RESOURCE_CREATED,
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))}),
            @ApiResponse(responseCode = "400", description = Messages.REQUIRED_FIELD,
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})
    })
    @PutMapping(ApiVersion.V1 + PATH_ID)
    public ResponseEntity<Transaction> updateOrCreateTx(
            @PathVariable(value = "id", required = true) final Long id, @Valid @RequestBody Transaction transaction) {
        log.debug("REST request to create a transaction with id: {}", id);
        return ResponseEntity.ok().body(transactionService.save(transaction));
    }

}
