package banco.vit.vit.api.repository;

import banco.vit.vit.api.model.Banco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {
    Page<Banco> findAllByAtivoTrue(Pageable paginacao);
}
