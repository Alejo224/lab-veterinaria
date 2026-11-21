package univalle.lab.veterinaria.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Citas;
import univalle.lab.veterinaria.entity.Mascotas;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

@Controller
@RequestMapping("/web/citas")
public class CitasWebController {

    private final CitasRepository citasRepo;
    private final MascotasRepository mascotasRepo;

    public CitasWebController(CitasRepository citasRepo, MascotasRepository mascotasRepo) {
        this.citasRepo = citasRepo;
        this.mascotasRepo = mascotasRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("citas", citasRepo.findAll());
        return "citas/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("cita", new Citas());
        model.addAttribute("mascotas", mascotasRepo.findAll());
        return "citas/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("cita", citasRepo.findById(id).orElseThrow());
        model.addAttribute("mascotas", mascotasRepo.findAll());
        return "citas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("cita") Citas cita,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("mascotas", mascotasRepo.findAll());
            return "citas/form";
        }
        Mascotas mascota = mascotasRepo.findById(cita.getMascota().getId()).orElseThrow();

        if (cita.getId() != null) {
            Citas existente = citasRepo.findById(cita.getId()).orElseThrow();
            existente.setFecha(cita.getFecha());
            existente.setMotivo(cita.getMotivo());
            existente.setVeterinario(cita.getVeterinario());
            existente.setMascota(mascota);
            citasRepo.save(existente);
        } else {
            cita.setMascota(mascota);
            citasRepo.save(cita);
        }
        return "redirect:/web/citas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        citasRepo.deleteById(id);
        return "redirect:/web/citas";
    }
}