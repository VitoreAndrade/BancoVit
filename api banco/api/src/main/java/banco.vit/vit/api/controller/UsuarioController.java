package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosCadastrosUsuariosDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public void cadastrarUsuario(@RequestBody @Valid DadosCadastrosUsuariosDto dados){
       usuarioService.cadastrarUsuario(dados);
    }
}
