package univalle.lab.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univalle.lab.veterinaria.entity.Citas;

public interface CitasRepository extends JpaRepository<Citas, Long> {
}
