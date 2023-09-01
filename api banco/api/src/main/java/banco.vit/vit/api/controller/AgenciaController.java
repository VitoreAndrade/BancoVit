package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosAtualizacaoAgenciaDto;
import banco.vit.vit.api.dto.DadosCadastroAgenciaDto;
import banco.vit.vit.api.dto.DadosListagemAgenciaDto;
import banco.vit.vit.api.dto.DadosListagemBancoDto;
import banco.vit.vit.api.service.AgenciaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DadosListagemAgenciaDto> listar (@PageableDefault(size = 10, sort = {"nomeAgencia"})Pageable pagina){
        return agenciaService.listarAgencia(pagina);
    }


    @PutMapping
    @Transactional
    public void atualizar (@RequestBody @Valid DadosAtualizacaoAgenciaDto dados){
        agenciaService.atualizarAgencia(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir (@PathVariable Long id){
        agenciaService.excluirAgencia(id);
    }
}
