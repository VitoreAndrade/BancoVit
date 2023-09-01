package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.model.TipoDeConta;

public record DadosListagemContaDto (
        Long id,
        Long idUsario,
        Long idBanco,
        TipoDeConta tipoDeConta,
        double saldo

) {

}
