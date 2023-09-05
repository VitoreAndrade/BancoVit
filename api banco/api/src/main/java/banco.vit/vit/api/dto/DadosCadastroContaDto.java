package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Agencia;
import banco.vit.vit.api.model.TipoDeConta;
import banco.vit.vit.api.model.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public record DadosCadastroContaDto(
        Long idBanco,
        Long idAgencia,
        Long IdUsuario,
        boolean ativo,
        TipoDeConta tipoDeConta,
        double saldo


) {
}
