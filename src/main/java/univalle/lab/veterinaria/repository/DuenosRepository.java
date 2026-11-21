package univalle.lab.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univalle.lab.veterinaria.entity.Duenos;

public interface DuenosRepository extends JpaRepository<Duenos, Long> {
}
