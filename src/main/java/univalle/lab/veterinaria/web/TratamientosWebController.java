package univalle.lab.veterinaria.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import univalle.lab.veterinaria.entity.Citas;
import univalle.lab.veterinaria.entity.Tratamientos;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.TratamientosRepository;

@Controller
@RequestMapping("/web/tratamientos")
public class TratamientosWebController {

    private final TratamientosRepository tratamientosRepo;
    private final CitasRepository citasRepo;

    public TratamientosWebController(TratamientosRepository tratamientosRepo, CitasRepository citasRepo) {
        this.tratamientosRepo = tratamientosRepo;
        this.citasRepo = citasRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tratamientos", tratamientosRepo.findAll());
        return "tratamientos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(@RequestParam(required = false) Long citaId, Model model) {
        Tratamientos t = new Tratamientos();
        if (citaId != null) {
            citasRepo.findById(citaId).ifPresent(t::setCita);
        }
        model.addAttribute("tratamiento", t);
        model.addAttribute("citas", citasRepo.findAll());
        return "tratamientos/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tratamiento", tratamientosRepo.findById(id).orElseThrow());
        model.addAttribute("citas", citasRepo.findAll());
        return "tratamientos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("tratamiento") Tratamientos tratamiento,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("citas", citasRepo.findAll());
            return "tratamientos/form";
        }
        Citas cita = citasRepo.findById(tratamiento.getCita().getId()).orElseThrow();
        tratamiento.setCita(cita);
        tratamientosRepo.save(tratamiento);
        return "redirect:/web/tratamientos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        tratamientosRepo.deleteById(id);
        return "redirect:/web/tratamientos";
    }
}