package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosCadastroContaDto;
import banco.vit.vit.api.service.ContaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroContaDto dados){
        contaService.cadastarConta(dados);
    }


}