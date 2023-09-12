package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.StatusTransferencia;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record DadosTransferenciaDto(
        @NotNull
        Long idContaEnvia,
        @NotNull
        Long idContaRecebe,

        StatusTransferencia status,

        @DecimalMin(value = "1.00")
        @DecimalMax(value = "999999999.99")
        Long  valor
//
//        @DecimalMin(value = "1.00")
//        @DecimalMax(value = "999999999.99")
//        Long  valorCredito,
//        @DecimalMin(value = "1.00")
//        @DecimalMax(value = "999999999.99")
//        Long  valorLis
) {
}
