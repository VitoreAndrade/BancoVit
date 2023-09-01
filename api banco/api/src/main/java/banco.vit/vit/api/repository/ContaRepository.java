package banco.vit.vit.api.repository;

import banco.vit.vit.api.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
