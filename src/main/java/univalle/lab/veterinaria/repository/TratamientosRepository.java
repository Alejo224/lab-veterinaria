package univalle.lab.veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univalle.lab.veterinaria.entity.Tratamientos;

public interface TratamientosRepository extends JpaRepository<Tratamientos, Long> {
}
