package univalle.lab.veterinaria.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import univalle.lab.veterinaria.entity.Citas;
import univalle.lab.veterinaria.repository.CitasRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

@Controller
public class CitasView {

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private MascotasRepository mascotasRepository;

    @GetMapping("/view/citas")
    public String lista(Model model) {
        model.addAttribute("citas", citasRepository.findAll());
        return "citas/list";
    }

    @GetMapping("/view/citas/form")
    public String form(Model model) {
        model.addAttribute("citas", new Citas());
        model.addAttribute("mascotas", mascotasRepository.findAll());
        return "citas/form";
    }

    @PostMapping("/view/citas/save")
    public String save(@ModelAttribute Citas citas, RedirectAttributes rs) {
        citasRepository.save(citas);
        rs.addFlashAttribute("message", "Cita guardada correctamente");
        return "redirect:/view/citas";
    }

    @GetMapping("/view/citas/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Citas citas = citasRepository.findById(id).orElse(null);
        model.addAttribute("citas", citas);
        model.addAttribute("mascotas", mascotasRepository.findAll());
        return "citas/form";
    }

    @PostMapping("/view/citas/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        citasRepository.deleteById(id);
        rs.addFlashAttribute("message", "Cita eliminada correctamente");
        return "redirect:/view/citas";
    }
}
