package univalle.lab.veterinaria.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Duenos;
import univalle.lab.veterinaria.repository.DuenosRepository;

@Controller
@RequestMapping("/web/duenos")
public class DuenosWebController {

    private final DuenosRepository duenosRepo;

    public DuenosWebController(DuenosRepository duenosRepo) {
        this.duenosRepo = duenosRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("duenos", duenosRepo.findAll());
        return "duenos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("dueno", new Duenos());
        return "duenos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("dueno", duenosRepo.findById(id).orElseThrow());
        return "duenos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("dueno") Duenos dueno, BindingResult result) {
        if (result.hasErrors()) {
            return "duenos/form";
        }
        if (dueno.getId() != null) {
            Duenos existente = duenosRepo.findById(dueno.getId()).orElseThrow();
            existente.setNombre(dueno.getNombre());
            existente.setTelefono(dueno.getTelefono());
            existente.setCorreo(dueno.getCorreo());
            existente.setDireccion(dueno.getDireccion());
            duenosRepo.save(existente);
        } else {
            duenosRepo.save(dueno);
        }
        return "redirect:/web/duenos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        duenosRepo.deleteById(id);
        return "redirect:/web/duenos";
    }
}