package univalle.lab.veterinaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitasDto {

    private Long id;
    private LocalDate fecha;
    private String motivo;
    private Long mascota_id;
    private String veterinario;
}
