package cl.inmate302.springedumanager.web;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.service.CursoService;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listCourses(Principal principal, Model model) {
        String usr = principal.getName();
        String clnusr = usr.replaceAll("@.*", "");
        model.addAttribute("username", clnusr);
        model.addAttribute("cursos", cursoService.findAll());
        return "cursos/list";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String showForm(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/form";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveCourse(@ModelAttribute("curso") Curso curso,
                            BindingResult result,
                            Model model) {

        if (curso.getFechaInicio() != null &&
            curso.getFechaFin() != null &&
            curso.getFechaFin().isBefore(curso.getFechaInicio()) ||
            curso.getFechaFin().isEqual(curso.getFechaInicio())){

            result.rejectValue(
                "fechaFin",
                "fechaFin.invalida",
                "La fecha final no puede ser igual o anterior a la fecha inicial"
            );
        }

        if (result.hasErrors()) {
            return "cursos/form";
        }

        cursoService.save(curso);
        return "redirect:/cursos";
    }



    @PostMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCourse(@PathVariable Long id) {
        cursoService.delete(id);
        return "redirect:/cursos";
    }
}
