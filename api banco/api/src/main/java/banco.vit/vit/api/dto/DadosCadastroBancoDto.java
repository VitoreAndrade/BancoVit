package banco.vit.vit.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroBancoDto (
        @NotBlank
        String nome,
        @NotNull
        DadosEnderecoDto endereco
){
}
