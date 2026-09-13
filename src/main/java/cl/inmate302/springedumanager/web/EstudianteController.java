package cl.inmate302.springedumanager.web;

import cl.inmate302.springedumanager.domain.Estudiante;
import cl.inmate302.springedumanager.service.EstudianteService;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteservice;

    public EstudianteController(EstudianteService estudianteservice) {
        this.estudianteservice = estudianteservice;
    }

    @GetMapping
    public String listEstudiantes(Principal principal, Model model) {
        String usr = principal.getName();
        String clnusr = usr.replaceAll("@.*", "");
        model.addAttribute("username", clnusr);
        model.addAttribute("estudiantes", estudianteservice.findAll());
        return "estudiantes/list";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String showForm(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/form";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteservice.save(estudiante);
        return "redirect:/estudiantes";
    }

    @PostMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEstudiante(@PathVariable Long id) {
        estudianteservice.delete(id);
        return "redirect:/estudiantes";
    }
}
