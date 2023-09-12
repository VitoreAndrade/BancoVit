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
//            if (conta.getId() == contaRecebe.getId()) {
//                transferenciaRepository.save(transfer);
//                throw new DadosErro("Você não pode transferir para a mesma conta");
//            }
        }
            if(conta.getTipoConta() == TipoDeConta.ContaEspecial && ((conta.getSaldo() + conta.getLimiteCredito()) > valor)){


                conta.transferir(valor);
                conta.zerarSaldo();
                conta.transferirSaldoCredito(valor);
                contaRecebe.adicionar(valor);
                transfer.setStatus(StatusTransferencia.CONCLUIDO);
                transfer.setTipoTransferencia(TipoTransferencia.CARTAOCREDITO);


                if (conta.getId() == contaRecebe.getId()) {
                    transfer.setStatus(StatusTransferencia.CANCELADO);
                    throw new DadosErro("Você não pode transferir para a mesma conta");
                }
                System.out.println("aaaaaaaaaaaaaaaaaaaaa");
                transferenciaRepository.save(transfer);
            }

//
//            } else if ((conta.getTipoConta() == TipoDeConta.ContaEspecial && conta.getLimiteCredito() >= valor)) {
//
//                conta.transferirSaldoCredito(valor);
//                contaRecebe.receberSaldoCredito(valor);
//                transfer.setStatus(StatusTransferencia.CONCLUIDO);
//                transfer.setTipoTransferencia(TipoTransferencia.CARTAOCREDITO);
//                if (conta.getId() == contaRecebe.getId()) {
//                    throw new DadosErro("Você não pode transferir para a mesma conta");
//                }
//                transferenciaRepository.save(transfer);
//            } else {
//                transfer.setStatus(StatusTransferencia.INDEFERIDO);
//                transferenciaRepository.save(transfer);
//                throw new DadosErro("Limite de crédito insuficiente");
//
//
//            }
//            if (conta.getTipoConta() == TipoDeConta.ContaPremium && conta.getLimiteCredito() >= valor) {
//                conta.transferirSaldoCredito(valor);
//                contaRecebe.receberSaldoCredito(valor);
//                transfer.setStatus(StatusTransferencia.CONCLUIDO);
//                transfer.setTipoTransferencia(TipoTransferencia.CARTAOCREDITO);
//                if (conta.getId() == contaRecebe.getId()) {
//                    throw new DadosErro("Você não pode transferir para a mesma conta");
//                }
//                transferenciaRepository.save(transfer);
//            } else {
//                transfer.setStatus(StatusTransferencia.INDEFERIDO);
//                transferenciaRepository.save(transfer);
//                throw new DadosErro("Limite de crédito insuficiente");
//            }
//
//            if (conta.getTipoConta() == TipoDeConta.ContaPremium && conta.getLimiteLis() >= valor) {
//                conta.transferirSaldoLis(valor);
//                contaRecebe.receberSaldoLis(valor);
//                transfer.setStatus(StatusTransferencia.CONCLUIDO);
//                transfer.setTipoTransferencia(TipoTransferencia.LIS);
//                if (conta.getId() == contaRecebe.getId()) {
//                    throw new DadosErro("Você não pode transferir para a mesma conta");
//                }
//                transferenciaRepository.save(transfer);
//            } else {
//                transfer.setStatus(StatusTransferencia.INDEFERIDO);
//                transferenciaRepository.save(transfer);
//                throw new DadosErro("Limite LIS insuficiente");
//            }
//
//
//        } else {
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


