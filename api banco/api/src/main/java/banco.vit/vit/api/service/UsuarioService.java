package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosAtualizacaoUsuariosDto;
import banco.vit.vit.api.dto.DadosCadastrosUsuariosDto;
import banco.vit.vit.api.dto.DadosListagemBancoDto;
import banco.vit.vit.api.dto.DadosListagemUsuarioDto;
import banco.vit.vit.api.model.Usuario;
import banco.vit.vit.api.repository.EnderecoRepository;
import banco.vit.vit.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrarUsuario(DadosCadastrosUsuariosDto dados){
        usuarioRepository.save(new Usuario(dados));
    }
    public Page<DadosListagemUsuarioDto> listarUsuarios (Pageable pagina){
        return usuarioRepository.findAllByAtivoTrue(pagina).map(DadosListagemUsuarioDto::new);
    }

    public void atualizarUsuario(DadosAtualizacaoUsuariosDto dadosAtualizar){
        Usuario usuario = usuarioRepository.findById(dadosAtualizar.id()).get();

        if(usuario.getNome() != null){
            usuario.setUsuario(dadosAtualizar.nome());
        }
        if(usuario.getEmail() != null){
            usuario.setEmail(dadosAtualizar.email());
        }
        if(usuario.getSenha() != null){
            usuario.setSenha(dadosAtualizar.senha());
        }
    }

    public void excluirUsuario (Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
    }
}
