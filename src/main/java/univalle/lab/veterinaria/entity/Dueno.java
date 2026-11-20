package univalle.lab.veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dueno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dueno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El telefono es obligatorio")
    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "La direccion es obligatorio")
    private String direccion;

    @OneToMany(mappedBy = "dueno", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Mascota> mascotas = new ArrayList<>();
}
