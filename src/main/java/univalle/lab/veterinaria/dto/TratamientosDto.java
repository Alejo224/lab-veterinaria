package univalle.lab.veterinaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TratamientosDto {

    private Long id;
    private String descripcion;
    private String dosis;
    private Long cita_id;
}
