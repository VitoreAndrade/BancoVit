package banco.vit.vit.api.service;

import banco.vit.vit.api.controller.DadosErro;
import banco.vit.vit.api.dto.DadosTransferenciaDto;
import banco.vit.vit.api.model.*;
import banco.vit.vit.api.repository.ContaRepository;
import banco.vit.vit.api.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private ContaRepository contaRepository;

    public String cadastrarTransferencia(DadosTransferenciaDto dados) {
        Conta conta = contaRepository.getReferenceById(dados.idContaEnvia());
        Conta contaRecebe = contaRepository.getReferenceById(dados.idContaRecebe());
        Long valor = dados.valor();
        Transferencia transfer = new Transferencia();
        transfer.setValor(dados.valor());
        transfer.setContaEnvia(conta);
        transfer.setContaRecebe(contaRecebe);
        transfer.setDataTransacao(LocalDateTime.now());

        if (conta.getSaldo() >= valor) {
            conta.transferir(valor);
            contaRecebe.adicionar(valor);
            transfer.setStatus(StatusTransferencia.CONCLUIDO);
            transfer.setTipoTransferencia(TipoTransferencia.SALDO);
            if (conta.getId() == contaRecebe.getId()) {
                transferenciaRepository.save(transfer);
                throw new DadosErro("Você não pode transferir para a mesma conta");
            }
            transferenciaRepository.save(transfer);

        }


        if (conta.getTipoConta() == TipoDeConta.ContaEspecial || conta.getTipoConta() == TipoDeConta.ContaPremium && conta.getSaldo() < valor) {


            if (valor > (conta.getLimiteCredito() + conta.getSaldo())) {
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITO);
                transfer.setStatus(StatusTransferencia.SEMLIMITE);
                transferenciaRepository.save(transfer);
                throw new DadosErro("saldo insuficiente");
            } else if (valor <= (conta.getLimiteCredito() + conta.getSaldo())) {
                conta.transferirSaldoCredito(valor - conta.getSaldo());
                conta.zerarSaldo();
                contaRecebe.adicionar(valor);
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITO);
                transfer.setStatus(StatusTransferencia.CONCLUIDO);
                transferenciaRepository.save(transfer);
            }
        }
        if (conta.getTipoConta() == TipoDeConta.ContaPremium && conta.getLimiteCredito() >= valor) {
            if (valor > (conta.getLimiteCredito() + conta.getSaldo() + conta.getLimiteLis())) {
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITOELIS);
                transfer.setStatus(StatusTransferencia.SEMLIMITE);
                transferenciaRepository.save(transfer);
                throw new DadosErro("saldo insuficiente");
            }
            else if (valor <= (conta.getLimiteCredito() + conta.getSaldo() + conta.getLimiteLis())) {
                conta.transferirSaldoCredito(valor - conta.getLimiteCredito());
                conta.transferirSaldoLis(valor - (conta.getLimiteCredito() + conta.getSaldo()));
                contaRecebe.adicionar(valor);
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITOELIS);
                transfer.setStatus(StatusTransferencia.CONCLUIDO);
            }
        }


//                transferenciaRepository.save(transfer);
//            } else {
//                transfer.setStatus(StatusTransferencia.INDEFERIDO);
//                transferenciaRepository.save(transfer);
//                throw new DadosErro("Limite de crédito insuficiente");
//            }
//                    if{
//            transfer.setStatus(StatusTransferencia.INDEFERIDO);
//            transfer.setTipoTransferencia(TipoTransferencia.CANCELADO);
//            transferenciaRepository.save(transfer);
//            throw new DadosErro("saldo insuficiente");
//
//
//        }


            return "Transferencia realizada";
        }

}


