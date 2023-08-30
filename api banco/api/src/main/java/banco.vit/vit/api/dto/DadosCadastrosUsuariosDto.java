package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;

public record DadosCadastrosUsuariosDto(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String usuario,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String senha,
        @NotNull
        DadosEnderecoDto endereco


) {
}