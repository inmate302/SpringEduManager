package cl.inmate302.springedumanager.api;

import cl.inmate302.springedumanager.domain.Estudiante;
import cl.inmate302.springedumanager.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(
            EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estudiante> obtenerEstudiante(
            @PathVariable Long id) {

        Estudiante estudiante = estudianteService.findById(id);

        if (estudiante == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estudiante);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estudiante> crearEstudiante(
            @RequestBody Estudiante estudiante) {

        Estudiante estudianteCreado =
                estudianteService.save(estudiante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(estudianteCreado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estudiante> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody Estudiante estudiante) {

        Estudiante estudianteActualizado =
                estudianteService.update(id, estudiante);

        if (estudianteActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estudianteActualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarEstudiante(
            @PathVariable Long id) {

        boolean eliminado = estudianteService.delete(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
