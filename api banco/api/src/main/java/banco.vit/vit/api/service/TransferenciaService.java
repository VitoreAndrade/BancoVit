package banco.vit.vit.api.service;

import banco.vit.vit.api.dto.DadosTransferenciaDto;
import banco.vit.vit.api.model.Conta;
import banco.vit.vit.api.repository.ContaRepository;
import banco.vit.vit.api.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    private ContaRepository contaRepository;

    public String cadastrarTransferencia(DadosTransferenciaDto dados) {
        Conta conta = contaRepository.getReferenceById(dados.contaEnvia());
        Conta contaR = contaRepository.getReferenceById(dados.contaRecebe());
        Long valorSaldo = conta.getSaldo() + contaR.

    }

}
