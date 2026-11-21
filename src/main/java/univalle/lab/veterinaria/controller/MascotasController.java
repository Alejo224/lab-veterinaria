package univalle.lab.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.dto.MascotasDto;
import univalle.lab.veterinaria.entity.Duenos;
import univalle.lab.veterinaria.entity.Mascotas;
import univalle.lab.veterinaria.repository.DuenosRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
class MascotasController {

    @Autowired
    private MascotasRepository mascotasRepository;

    @Autowired
    private DuenosRepository duenosRepository;

    @GetMapping
    public List<Mascotas> getAll() {
        return mascotasRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mascotas getById(@PathVariable Long id) {
        return mascotasRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Mascota no encontrado"));

    }

    @PostMapping
    public Mascotas create(@RequestBody MascotasDto dto) {

        Duenos dueno = duenosRepository.findById(dto.getDueno_id())
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));

        Mascotas mascota = new Mascotas();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setDueno(dueno);

        return mascotasRepository.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascotas update(@PathVariable Long id, @RequestBody MascotasDto dto) {

        Mascotas mascota = mascotasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        Duenos dueno = duenosRepository.findById(dto.getDueno_id())
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));

        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        mascota.setRaza(dto.getRaza());
        mascota.setEdad(dto.getEdad());
        mascota.setDueno(dueno);

        return mascotasRepository.save(mascota);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        mascotasRepository.deleteById(id);
    }
}
