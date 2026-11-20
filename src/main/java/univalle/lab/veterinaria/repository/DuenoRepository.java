package univalle.lab.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univalle.lab.veterinaria.entity.Dueno;

public interface DuenoRepository extends JpaRepository<Dueno, Long> {
}
