package banco.vit.vit.api.repository;

import banco.vit.vit.api.model.Agencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaRepository extends JpaRepository<Agencia, Long> {

    Page<Agencia> findAllByAtivoTrue(Pageable pagina);
}
