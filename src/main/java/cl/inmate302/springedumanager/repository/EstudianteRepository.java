package cl.inmate302.springedumanager.repository;

import cl.inmate302.springedumanager.domain.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Optional<Estudiante> findByEmail(String email);
    List<Estudiante> findByCursosId(Long cursoId);
}
