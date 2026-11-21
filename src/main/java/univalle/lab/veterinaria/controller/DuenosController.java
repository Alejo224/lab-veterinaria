package univalle.lab.veterinaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Duenos;
import univalle.lab.veterinaria.repository.DuenosRepository;

import java.util.List;

@RestController
@RequestMapping("/api/duenos")
class DuenosController {

    @Autowired
    private DuenosRepository duenosRepository;

    @GetMapping
    public List<Duenos> getAll() {
        return duenosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Duenos getById(@PathVariable Long id) {
        return duenosRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Duenos create(@RequestBody Duenos dueno) {
        return duenosRepository.save(dueno);
    }

    @PutMapping("/{id}")
    public Duenos update(@PathVariable Long id, @RequestBody Duenos dueno) {
        dueno.setId(id);
        return duenosRepository.save(dueno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        duenosRepository.deleteById(id);
    }

}
