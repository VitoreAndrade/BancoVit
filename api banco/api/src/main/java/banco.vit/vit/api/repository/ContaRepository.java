package banco.vit.vit.api.repository;

import banco.vit.vit.api.dto.DadosListagemContaDto;
import banco.vit.vit.api.model.Conta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Page<Conta> findAllByAtivoTrue(Pageable paginacao);
}
