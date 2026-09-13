package cl.inmate302.springedumanager.api;

import cl.inmate302.springedumanager.domain.Evaluacion;
import cl.inmate302.springedumanager.service.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionRestController {

    private final EvaluacionService evaluacionService;

    public EvaluacionRestController(
            EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public ResponseEntity<List<Evaluacion>> listarEvaluaciones() {
        return ResponseEntity.ok(
                evaluacionService.findAll()
        );
    }
}
