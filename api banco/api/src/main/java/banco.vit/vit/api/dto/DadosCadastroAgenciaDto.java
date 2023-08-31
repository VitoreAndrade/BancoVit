package banco.vit.vit.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAgenciaDto (
        @NotBlank
        String nomeAgencia,
        @NotNull
        Long id_banco

){
}
