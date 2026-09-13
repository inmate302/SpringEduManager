package cl.inmate302.springedumanager.web;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.domain.Estudiante;
import cl.inmate302.springedumanager.domain.Evaluacion;
import cl.inmate302.springedumanager.service.CursoService;
import cl.inmate302.springedumanager.service.EstudianteService;
import cl.inmate302.springedumanager.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping ("/dashboard") 
    public String dashboard(Principal principal, Model model) {
        String usr = principal.getName();
        String clnusr = usr.replaceAll("@.*", "");
        model.addAttribute("username", clnusr);
        
        List<Curso> cursos = cursoService.findAll();
        List<Estudiante> estudiantes = estudianteService.findAll();
        List<Evaluacion> evaluaciones = evaluacionService.findAll();

        model.addAttribute("cursos", cursos);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("evaluaciones", evaluaciones);

        return "dashboard";
    }

}

