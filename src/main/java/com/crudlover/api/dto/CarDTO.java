package com.crudlover.api.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CarDTO(
                @NotBlank String modelo,

                @NotBlank String fabricante,

                @NotNull @Past Date dataFabricacao,

                @NotNull int valor,

                @NotNull int anoModelo) {

}
