package banco.vit.vit.api.controller;

import banco.vit.vit.api.dto.DadosTransferenciaDto;
import banco.vit.vit.api.service.TransferenciaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosTransferenciaDto dados){
    var result = transferenciaService.cadastrarTransferencia(dados);
    return ResponseEntity.ok(result);
    }


}
