package univalle.lab.veterinaria.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MascotasDto {

    private Long id_mascotas;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    //@Column(name = "dueno_id")
    private Long dueno_id;
}
