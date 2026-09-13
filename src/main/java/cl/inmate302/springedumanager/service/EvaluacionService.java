package cl.inmate302.springedumanager.service;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.domain.Evaluacion;
import cl.inmate302.springedumanager.domain.Evaluacion.EstadoEvaluacion;
import cl.inmate302.springedumanager.dto.EvaluacionDTO;
import cl.inmate302.springedumanager.repository.CursoRepository;
import cl.inmate302.springedumanager.repository.EstudianteRepository;
import cl.inmate302.springedumanager.repository.EvaluacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final CursoRepository cursoRepository;

    public EvaluacionService(
            EvaluacionRepository evaluacionRepository,
            EstudianteRepository estudianteRepository,
            CursoRepository cursoRepository) {

        this.evaluacionRepository = evaluacionRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion findById(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void delete(Long id) { evaluacionRepository.deleteById(id); }


   public void saveFromDTO(EvaluacionDTO dto) {
    if (dto.getCursoId() == null) {
        throw new IllegalArgumentException("cursoId is required");
    }

    Curso curso = cursoRepository.findById(dto.getCursoId())
            .orElseThrow(() -> new IllegalArgumentException(
                    "Curso no encontrado: " + dto.getCursoId()));

    Evaluacion evaluacion;

    if (dto.getId() != null) {
        evaluacion = evaluacionRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Evaluación no encontrada: " + dto.getId()));
    } else {
        evaluacion = new Evaluacion();
        evaluacion.setPonderacion(0.0);
        evaluacion.setEstado(EstadoEvaluacion.PENDIENTE);
    }

    evaluacion.setNombre(dto.getNombre());
    evaluacion.setDescripcion(dto.getDescripcion());
    evaluacion.setObjetivo(dto.getObjetivo());
    evaluacion.setFecha(dto.getFecha());
    evaluacion.setCurso(curso);

    if (dto.getPonderacion() != null) {
        evaluacion.setPonderacion(dto.getPonderacion());
    }

    if (dto.getEstado() != null) {
        evaluacion.setEstado(dto.getEstado());
    }

    evaluacionRepository.save(evaluacion);
    }
}    
