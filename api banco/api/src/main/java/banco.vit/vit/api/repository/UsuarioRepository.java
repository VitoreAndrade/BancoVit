package banco.vit.vit.api.repository;

import banco.vit.vit.api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Page<Usuario> findAllByAtivoTrue (Pageable pagina);
}
