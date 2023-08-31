package banco.vit.vit.api.dto;

public record DadosAtualizacaoUsuariosDto(
        Long id,
        String nome,
        String email,
        String senha
) {
}
