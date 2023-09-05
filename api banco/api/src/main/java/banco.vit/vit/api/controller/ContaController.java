package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosAtualizacaoContaDto;
import banco.vit.vit.api.dto.DadosCadastroContaDto;
import banco.vit.vit.api.dto.DadosListagemContaDto;
import banco.vit.vit.api.service.ContaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroContaDto dados) {
        var result = contaService.cadastrarConta(dados);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public Page<DadosListagemContaDto> listar(@PageableDefault(size = 10, sort = {"tipoConta"}) Pageable pagina) {
        return contaService.listarConta(pagina);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoContaDto dados) {
        contaService.atualizarConta(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        contaService.excluirConta(id);
    }

}