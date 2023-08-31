package banco.vit.vit.api.dto;

import banco.vit.vit.api.model.Agencia;
import banco.vit.vit.api.model.Usuario;

public record DadosListagemUsuarioDto(
        Long id,
        String nome,
        String usario


) {
    public DadosListagemUsuarioDto(Usuario usuario){
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getUsuario()
                );
    }


}
