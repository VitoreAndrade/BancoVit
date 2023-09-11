package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.StatusTransferencia;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record DadosTransferenciaDto(
        Long contaEnvia,
        Long contaRecebe,
        StatusTransferencia status,
        @DecimalMin(value = "1.00")
        @DecimalMax(value = "999999999.99")
        Long  valor
) {
}
