package univalle.lab.veterinaria.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import univalle.lab.veterinaria.entity.Duenos;
import univalle.lab.veterinaria.repository.DuenosRepository;

@Controller
public class DuenosView {

    @Autowired
    private DuenosRepository duenosRepository;

    @GetMapping("/view/duenos")
    public String lista(Model model) {
        model.addAttribute("duenos", duenosRepository.findAll());
        return "duenos/list";
    }

    @GetMapping("/view/duenos/form")
    public String form(Model model) {
        model.addAttribute("duenos", new Duenos());
        return "duenos/form";
    }

    @PostMapping("/view/duenos/save")
    public String save(@ModelAttribute Duenos duenos, RedirectAttributes rs) {
        duenosRepository.save(duenos);
        rs.addFlashAttribute("message", "Dueño guardado correctamente");
        return "redirect:/view/duenos";
    }

    @GetMapping("/view/duenos/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Duenos duenos = duenosRepository.findById(id).orElse(null);
        model.addAttribute("duenos", duenos);
        return "duenos/form";
    }

    @PostMapping("/view/duenos/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rs) {
        duenosRepository.deleteById(id);
        rs.addFlashAttribute("message", "Dueño eliminado correctamente");
        return "redirect:/view/duenos";
    }
}