package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosAtualizacaoUsuariosDto;
import banco.vit.vit.api.dto.DadosCadastrosUsuariosDto;
import banco.vit.vit.api.dto.DadosListagemUsuarioDto;
import banco.vit.vit.api.service.UsuarioService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastrosUsuariosDto dados){
       usuarioService.cadastrarUsuario(dados);
    }


    @GetMapping
    public Page<DadosListagemUsuarioDto> listar(@PageableDefault(size = 10, sort = {"nome"})Pageable pagina){
        return usuarioService.listarUsuarios(pagina);
    }

    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid DadosAtualizacaoUsuariosDto dados){
        usuarioService.atualizarUsuario(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
    }
}
