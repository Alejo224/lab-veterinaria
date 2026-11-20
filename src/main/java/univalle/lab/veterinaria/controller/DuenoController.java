package univalle.lab.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Dueno;
import univalle.lab.veterinaria.repository.DuenoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
class DuenoController {

    @Autowired
    private DuenoRepository duenoRepository;

    @GetMapping
    public List<Dueno> getAll() {
        return duenoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Dueno getById(@PathVariable Long id) {
        return duenoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Dueno create(@RequestBody Dueno dueno) {
        return duenoRepository.save(dueno);
    }

    @PutMapping("/{id}")
    public Dueno update(@PathVariable Long id, @RequestBody Dueno dueno) {
        dueno.setId(id);
        return duenoRepository.save(dueno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        duenoRepository.deleteById(id);
    }

}
