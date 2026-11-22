package univalle.lab.veterinaria.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import univalle.lab.veterinaria.entity.Tratamientos;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.TratamientosRepository;

@Controller
public class TratamientosView {

    @Autowired
    private TratamientosRepository tratamientosRepository;

    @Autowired
    private CitasRepository citasRepository;

    @GetMapping("/view/tratamientos")
    public String lista(Model model) {
        model.addAttribute("tratamientos", tratamientosRepository.findAll());
        return "tratamientos/list";
    }

    @GetMapping("/view/tratamientos/form")
    public String form(Model model) {
        model.addAttribute("tratamientos", new Tratamientos());
        model.addAttribute("citas", citasRepository.findAll());
        return "tratamientos/form";
    }

    @PostMapping("/view/tratamientos/save")
    public String save(@ModelAttribute Tratamientos tratamientos, RedirectAttributes rs) {
        tratamientosRepository.save(tratamientos);
        rs.addFlashAttribute("message", "Tratamiento guardado correctamente");
        return "redirect:/view/tratamientos";
    }

    @GetMapping("/view/tratamientos/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Tratamientos tratamientos = tratamientosRepository.findById(id).orElse(null);
        model.addAttribute("tratamientos", tratamientos);
        model.addAttribute("citas", citasRepository.findAll());
        return "tratamientos/form";
    }

    @PostMapping("/view/tratamientos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        tratamientosRepository.deleteById(id);
        rs.addFlashAttribute("message", "Tratamiento eliminado correctamente");
        return "redirect:/view/tratamientos";
    }
}
