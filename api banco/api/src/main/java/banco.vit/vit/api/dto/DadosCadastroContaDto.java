package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Agencia;
import banco.vit.vit.api.model.TipoDeConta;
import banco.vit.vit.api.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroContaDto(
        @NotNull
        Long idBanco,
        @NotNull
        Long idAgencia,
        @NotNull
        Long IdUsuario,
        @NotNull
        boolean ativo,
        @NotNull
        TipoDeConta tipoDeConta,
        @NotNull
        double saldo,
        double limiteCredito,
        double limiteLis


) {
}
