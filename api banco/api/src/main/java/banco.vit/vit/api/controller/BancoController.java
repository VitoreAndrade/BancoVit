package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosAtualizacaoBancoDto;
import banco.vit.vit.api.dto.DadosCadastroBancoDto;
import banco.vit.vit.api.dto.DadosListagemBancoDto;
import banco.vit.vit.api.service.BancoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;
    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid DadosCadastroBancoDto dados){
        bancoService.cadastrarBanco(dados);
    }

    @GetMapping
    public Page<DadosListagemBancoDto> listar (@PageableDefault(size = 10, sort = {"nome"}) Pageable pagina){
      return bancoService.listarBanco(pagina);
    }

    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid DadosAtualizacaoBancoDto dados){
        bancoService.atualizarBanco(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        bancoService.excluirBanco(id);
    }
}
