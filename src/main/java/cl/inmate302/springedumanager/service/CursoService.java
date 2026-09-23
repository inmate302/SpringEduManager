package cl.inmate302.springedumanager.service;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public List<Curso> findAll() {
        return repository.findAll();
    }

    public Curso findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    public Curso update(Long id, Curso cursoData) {
        Curso cursoExistente = findById(id);

        if (cursoExistente == null) {
            return null;
        }

        cursoExistente.setNombre(cursoData.getNombre());
        cursoExistente.setDescripcion(cursoData.getDescripcion());
        cursoExistente.setFechaInicio(cursoData.getFechaInicio());
        cursoExistente.setFechaFin(cursoData.getFechaFin());

        return repository.save(cursoExistente);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
