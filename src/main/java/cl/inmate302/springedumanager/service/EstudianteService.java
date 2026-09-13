package cl.inmate302.springedumanager.service;

import cl.inmate302.springedumanager.domain.Estudiante;
import cl.inmate302.springedumanager.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository repository;

    public EstudianteService(EstudianteRepository repository) {
        this.repository = repository;
    }

    public List<Estudiante> findAll() {
        return repository.findAll();
    }

    public Estudiante findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estudiante save(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    public Estudiante update(Long id, Estudiante estudianteData) {
        Estudiante estudianteExistente = findById(id);

        if (estudianteExistente == null) {
            return null;
        }

        estudianteExistente.setNombre(estudianteData.getNombre());
        estudianteExistente.setEmail(estudianteData.getEmail());
        estudianteExistente.setPassword(estudianteData.getPassword());
        estudianteExistente.getRol();
        estudianteExistente.setFechaRegistro(estudianteData.getFechaRegistro());

        return repository.save(estudianteExistente);
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
