package banco.vit.vit.api.repository;

import banco.vit.vit.api.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository <Transferencia, Long> {
}
