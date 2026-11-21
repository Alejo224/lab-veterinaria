package univalle.lab.veterinaria.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Duenos;
import univalle.lab.veterinaria.entity.Mascotas;
import univalle.lab.veterinaria.repository.DuenosRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

@Controller
@RequestMapping("/web/mascotas")
public class MascotasWebController {

    private final MascotasRepository mascotasRepo;
    private final DuenosRepository duenosRepo;

    public MascotasWebController(MascotasRepository mascotasRepo, DuenosRepository duenosRepo) {
        this.mascotasRepo = mascotasRepo;
        this.duenosRepo = duenosRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("mascotas", mascotasRepo.findAll());
        return "mascotas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("mascota", new Mascotas());
        model.addAttribute("duenos", duenosRepo.findAll());
        return "mascotas/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("mascota", mascotasRepo.findById(id).orElseThrow());
        model.addAttribute("duenos", duenosRepo.findAll());
        return "mascotas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("mascota") Mascotas mascota,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("duenos", duenosRepo.findAll());
            return "mascotas/form";
        }
        Duenos dueno = duenosRepo.findById(mascota.getDueno().getId()).orElseThrow();

        if (mascota.getId() != null) {
            Mascotas existente = mascotasRepo.findById(mascota.getId()).orElseThrow();
            existente.setNombre(mascota.getNombre());
            existente.setEspecie(mascota.getEspecie());
            existente.setRaza(mascota.getRaza());
            existente.setEdad(mascota.getEdad());
            existente.setDueno(dueno);
            mascotasRepo.save(existente);
        } else {
            mascota.setDueno(dueno);
            mascotasRepo.save(mascota);
        }
        return "redirect:/web/mascotas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mascotasRepo.deleteById(id);
        return "redirect:/web/mascotas";
    }
}