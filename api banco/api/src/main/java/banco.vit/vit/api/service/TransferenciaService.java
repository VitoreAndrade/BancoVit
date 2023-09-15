package banco.vit.vit.api.service;

import banco.vit.vit.api.controller.DadosErro;
import banco.vit.vit.api.dto.DadosTransferenciaDto;
import banco.vit.vit.api.model.*;
import banco.vit.vit.api.repository.ContaRepository;
import banco.vit.vit.api.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
            transferenciaRepository.save(transfer);

        } else if (conta.getTipoConta() == TipoDeConta.ContaNormal) {
            if (conta.getSaldo() < valor) {
                transfer.setStatus(StatusTransferencia.SEMLIMITE);
                transfer.setTipoTransferencia(TipoTransferencia.SALDO);

                throw new DadosErro("Saldo insuficiente");
            }
            transferenciaRepository.save(transfer);
        }
        if (conta.getId() == contaRecebe.getId()) {
            transferenciaRepository.save(transfer);
            throw new DadosErro("Você não pode transferir para a mesma conta");
        }

        if (conta.getTipoConta() == TipoDeConta.ContaEspecial) {
            if (valor > conta.getSaldo()) {
                conta.transferirSaldoCredito(valor - conta.getSaldo());
                conta.zerarSaldo();
                contaRecebe.adicionar(valor);
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITO);
                transfer.setStatus(StatusTransferencia.CONCLUIDO);
                transferenciaRepository.save(transfer);
            }

            else if (valor > (conta.getLimiteCredito() + conta.getSaldo())) {
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITO);
                transfer.setStatus(StatusTransferencia.SEMLIMITE);
                transferenciaRepository.save(transfer);
                throw new DadosErro("saldo insuficiente");
            }
        }

        if (conta.getTipoConta() == TipoDeConta.ContaPremium) {
            if (valor > (conta.getLimiteCredito() + conta.getSaldo() + conta.getLimiteLis())) {
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITOELIS);
                transfer.setStatus(StatusTransferencia.SEMLIMITE);
                transferenciaRepository.save(transfer);
                throw new DadosErro("saldo insuficiente");
            }
            if (valor > (conta.getSaldo() + conta.getLimiteCredito())) {
                conta.transferirSaldoLis(valor - (conta.getSaldo() + conta.getLimiteCredito()));
                conta.zerarSaldo();
                conta.zerarCredtio();
                contaRecebe.adicionar(valor);
                transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITOELIS);
                transfer.setStatus(StatusTransferencia.CONCLUIDO);
            }
                else if (valor > conta.getSaldo()) {
                  conta.transferirSaldoCredito(valor - conta.getSaldo());
                  conta.zerarSaldo();
                  contaRecebe.adicionar(valor);
                  transfer.setTipoTransferencia(TipoTransferencia.SALDOECREDITO);
                  transfer.setStatus(StatusTransferencia.CONCLUIDO);
            }
        }
        return "Transferencia realizada";
    }
}


