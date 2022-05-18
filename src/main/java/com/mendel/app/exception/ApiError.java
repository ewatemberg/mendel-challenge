package com.mendel.app.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "informacion del error retornado")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    @JsonProperty("detalle")
    @Schema(description = "Descripcion general del problema ocurrido")
    String detail;
}
