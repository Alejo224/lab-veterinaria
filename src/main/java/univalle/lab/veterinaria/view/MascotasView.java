package univalle.lab.veterinaria.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import univalle.lab.veterinaria.entity.Mascotas;
import univalle.lab.veterinaria.repository.DuenosRepository;
import univalle.lab.veterinaria.repository.MascotasRepository;

@Controller
public class MascotasView {

    @Autowired
    private MascotasRepository mascotasRepository;

    @Autowired
    private DuenosRepository duenosRepository;

    @GetMapping("/view/mascotas")
    public String lista(Model model) {
        model.addAttribute("mascotas", mascotasRepository.findAll());
        return "mascotas/list";
    }

    @GetMapping("/view/mascotas/form")
    public String form(Model model) {
        model.addAttribute("mascotas", new Mascotas());
        model.addAttribute("duenos", duenosRepository.findAll());
        return "mascotas/form";
    }

    @PostMapping("/view/mascotas/save")
    public String save(@ModelAttribute Mascotas mascotas, RedirectAttributes rs) {
        mascotasRepository.save(mascotas);
        rs.addFlashAttribute("message", "Mascota guardada correctamente");
        return "redirect:/view/mascotas";
    }

    @GetMapping("/view/mascotas/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Mascotas mascotas = mascotasRepository.findById(id).orElse(null);
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("duenos", duenosRepository.findAll());
        return "mascotas/form";
    }

    @PostMapping("/view/mascotas/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        mascotasRepository.deleteById(id);
        rs.addFlashAttribute("message", "Mascota eliminada correctamente");
        return "redirect:/view/mascotas";
    }
}
