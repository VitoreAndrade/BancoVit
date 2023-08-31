package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosCadastroAgenciaDto;
import banco.vit.vit.api.service.AgenciaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agencia")
public class AgenciaController {
    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroAgenciaDto dados){
        agenciaService.cadastrarAgencia(dados);
    }
}
