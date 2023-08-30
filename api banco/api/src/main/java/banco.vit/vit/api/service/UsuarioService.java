package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosCadastrosUsuariosDto;
import banco.vit.vit.api.model.Usuario;
import banco.vit.vit.api.repository.EnderecoRepository;
import banco.vit.vit.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
