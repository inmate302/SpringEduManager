package cl.inmate302.springedumanager.web;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.domain.Evaluacion;
import cl.inmate302.springedumanager.domain.Evaluacion.EstadoEvaluacion;
import cl.inmate302.springedumanager.dto.EvaluacionDTO;
import cl.inmate302.springedumanager.repository.EvaluacionRepository;
import cl.inmate302.springedumanager.service.CursoService;
import cl.inmate302.springedumanager.service.EstudianteService;
import cl.inmate302.springedumanager.service.EvaluacionService;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/evaluaciones")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;
    private final EstudianteService estudianteService;
    private final CursoService cursoService;
    private final EvaluacionRepository evaluacionRepository;

    public EvaluacionController(
            EvaluacionService evaluacionService,
            EstudianteService estudianteService,
            CursoService cursoService,
            EvaluacionRepository evaluacionRepository) {
        this.evaluacionService = evaluacionService;
        this.estudianteService = estudianteService;
        this.cursoService = cursoService;
        this.evaluacionRepository = evaluacionRepository;
    }

    @GetMapping
    public String listar(
            Principal principal,
            @RequestParam(name = "cursoId", required = false) Long cursoId,
            Model model) {

        String usr = principal.getName();
        String clnusr = usr.replaceAll("@.*", "");

        List<Evaluacion> evaluaciones;

        if (cursoId == null) {
            evaluaciones = evaluacionService.findAll();
        } else {
            evaluaciones = evaluacionRepository.findByCursoId(cursoId);
        }

        model.addAttribute("username", clnusr);
        model.addAttribute("evaluaciones", evaluaciones);
        model.addAttribute("cursos", cursoService.findAll());
        model.addAttribute("cursoSeleccionadoId", cursoId);

        return "evaluaciones/list";
    }


    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        List<Curso> cursos = cursoService.findAll();

        if (cursos.isEmpty()) {
            return "redirect:/evaluaciones?error=sin-cursos";
        }

        model.addAttribute("evaluacion", new EvaluacionDTO());
        model.addAttribute("estudiantes", estudianteService.findAll());
        model.addAttribute("cursos", cursos);
        model.addAttribute("estadosEvaluacion", EstadoEvaluacion.values());

        return "evaluaciones/form";
    }



    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute("evaluacion") EvaluacionDTO dto,
            RedirectAttributes redirectAttributes) {

        evaluacionService.saveFromDTO(dto);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Evaluación guardada correctamente");

        return "redirect:/evaluaciones";
    }

    @PostMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEstudiante(@PathVariable Long id) {
        evaluacionService.delete(id);
        return "redirect:/evaluaciones";
    }
}
