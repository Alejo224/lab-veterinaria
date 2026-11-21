package univalle.lab.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univalle.lab.veterinaria.entity.Mascotas;

public interface MascotasRepository extends JpaRepository<Mascotas, Long> {
}
