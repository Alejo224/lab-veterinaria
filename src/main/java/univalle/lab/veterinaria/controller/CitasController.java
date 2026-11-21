package univalle.lab.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.dto.CitasDto;
import univalle.lab.veterinaria.entity.Citas;
import univalle.lab.veterinaria.entity.Mascotas;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
class CitasController {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private MascotasRepository mascotasRepository;

    public CitasDto convertirDto(Citas citas) {
        return new CitasDto(
                citas.getId(),
                citas.getFecha(),
                citas.getMotivo(),
                citas.getMascota() != null ? citas.getMascota().getId() : null,
                citas.getVeterinario()
        );
    }

    @GetMapping
    public List<CitasDto> getAll() {
        return citasRepository.findAll()
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CitasDto getById(@PathVariable Long id) {
        return citasRepository.findById(id)
                .map(this::convertirDto)
                .orElse(null);
    }

    @PostMapping
    public CitasDto create(@RequestBody CitasDto dto) {
        Mascotas mascota = mascotasRepository.findById(dto.getMascota_id())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + dto.getMascota_id()));

        Citas cita = new Citas();
        cita.setFecha(dto.getFecha());
        cita.setMotivo(dto.getMotivo());
        cita.setVeterinario(dto.getVeterinario());
        cita.setMascota(mascota);

        Citas citaGuardada = citasRepository.save(cita);
        return convertirDto(citaGuardada);
    }

    @PutMapping("/{id}")
    public CitasDto update(@PathVariable Long id, @RequestBody CitasDto dto) {
        Citas cita = citasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        Mascotas mascota = mascotasRepository.findById(dto.getMascota_id())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        cita.setFecha(dto.getFecha());
        cita.setMotivo(dto.getMotivo());
        cita.setVeterinario(dto.getVeterinario());
        cita.setMascota(mascota);

        Citas citaUpdate = citasRepository.save(cita);
        return convertirDto(citaUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        citasRepository.deleteById(id);
    }

}
