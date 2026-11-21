package univalle.lab.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.dto.TratamientosDto;
import univalle.lab.veterinaria.entity.Citas;
import univalle.lab.veterinaria.entity.Tratamientos;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.TratamientosRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tratamientos")
class TratamientosController {

    @Autowired
    private TratamientosRepository tratamientosRepository;

    @Autowired
    private CitasRepository citasRepository;

    public TratamientosDto convertirDto(Tratamientos tratamientos) {
        return new TratamientosDto(
                tratamientos.getId(),
                tratamientos.getDescripcion(),
                tratamientos.getDosis(),
                tratamientos.getCita() != null ? tratamientos.getCita().getId() : null
        );
    }
    
    @GetMapping
    public List<TratamientosDto> getAll() {
        return tratamientosRepository.findAll()
                .stream()
                .map(this::convertirDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TratamientosDto getById(@PathVariable Long id) {
        return tratamientosRepository.findById(id)
                .map(this::convertirDto)
                .orElse(null);
    }

    @PostMapping
    public TratamientosDto create(@RequestBody TratamientosDto dto) {

        Citas cita = citasRepository.findById(dto.getCita_id())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el id: " + dto.getCita_id()));

        Tratamientos tratamiento = new Tratamientos();
        tratamiento.setDescripcion(dto.getDescripcion());
        tratamiento.setDosis(dto.getDosis());
        tratamiento.setCita(cita);

        Tratamientos tratamientosGuardado = tratamientosRepository.save(tratamiento);
        return convertirDto(tratamientosGuardado);
    }

    @PutMapping("/{id}")
    public TratamientosDto update(@PathVariable Long id, @RequestBody TratamientosDto dto) {

        Tratamientos tratamiento = tratamientosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamiento no encontrado "));

        Citas cita = citasRepository.findById(dto.getCita_id())
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con el id: " + dto.getCita_id()));

        tratamiento.setCita(cita);
        tratamiento.setDescripcion(dto.getDescripcion());
        tratamiento.setDosis(dto.getDosis());

        Tratamientos tratamientosUpdate = tratamientosRepository.save(tratamiento);
        return convertirDto(tratamientosUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tratamientosRepository.deleteById(id);
    }

}
