package cl.inmate302.springedumanager.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import cl.inmate302.springedumanager.domain.Curso;
import cl.inmate302.springedumanager.domain.Estudiante;
import cl.inmate302.springedumanager.domain.Evaluacion;
import cl.inmate302.springedumanager.domain.Evaluacion.EstadoEvaluacion;
import cl.inmate302.springedumanager.repository.CursoRepository;
import cl.inmate302.springedumanager.repository.EstudianteRepository;
import cl.inmate302.springedumanager.repository.EvaluacionRepository;


@Configuration
public class DataInitializer {

    Curso curso1 = new Curso();
    Curso curso2 = new Curso();
    LocalDate fecha = LocalDate.now();

    @Bean
    @Transactional
    CommandLineRunner loadInitialData(
            CursoRepository cursoRepository,
            EstudianteRepository estudianteRepository,
            EvaluacionRepository evaluacionRepository) {

        return args -> {
            if (cursoRepository.count() == 0) {
                createCursos(cursoRepository);
            }

            if (estudianteRepository.count() == 0) {
                createEstudiantes(estudianteRepository);
            }

            if (evaluacionRepository.count() == 0) {
                createEvaluaciones(evaluacionRepository);
            }

        };
    }

    private void createCursos(CursoRepository cursoRepository) {
        curso1.setNombre("Java 101");
        curso1.setDescripcion("Principios básicos de programación en Java");
        curso1.setFechaInicio(fecha);
        curso1.setFechaFin(fecha.plusMonths(1));

        curso2.setNombre("Spring Boot Basics");
        curso2.setDescripcion("Principios básicos de desarrollo web usando Spring Boot");
        curso2.setFechaInicio(fecha.plusMonths(1));
        curso2.setFechaFin(fecha.plusMonths(2));

        cursoRepository.save(curso1);
        cursoRepository.save(curso2);
    }

    private void createEstudiantes(EstudianteRepository estudianteRepository) {

        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombre("Lucho");
        estudiante1.setEmail("lucho@bootcamp.net");
        estudiante1.setPassword("lucho2026");

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombre("Carlos");
        estudiante2.setEmail("carlos@bootcamp.net");
        estudiante2.setPassword("carlos2026");


        estudianteRepository.save(estudiante1);
        estudianteRepository.save(estudiante2);
    }

    private void createEvaluaciones(EvaluacionRepository evaluacionRepository) {
        Evaluacion evaluacion1 = new Evaluacion();
        Evaluacion evaluacion2 = new Evaluacion();
        Evaluacion evaluacion3 = new Evaluacion();
        Evaluacion evaluacion4 = new Evaluacion();
        Evaluacion evaluacion5 = new Evaluacion();


        evaluacion1.setNombre("Quiz 1 Primitives");
        evaluacion1.setDescripcion("Completa la oración usando el concepto correcto");
        evaluacion1.setObjetivo("Reconocer los tipos de datos primitivos en Java.");
        evaluacion1.setCurso(curso1);
        evaluacion1.setEstado(EstadoEvaluacion.PENDIENTE);
        evaluacion1.setFecha(fecha.plusDays(7));
        evaluacion1.setPonderacion(20.0);

        evaluacion2.setNombre("Quiz 2 Variables");
        evaluacion2.setDescripcion("Completa la variable usando el tipo de dato correcto");
        evaluacion2.setObjetivo("Comprender el uso de variables en Java.");
        evaluacion2.setCurso(curso1);
        evaluacion2.setEstado(EstadoEvaluacion.PENDIENTE);
        evaluacion2.setFecha(fecha.plusDays(7));
        evaluacion2.setPonderacion(20.0);

        evaluacion3.setNombre("Quiz 3 Funciones");
        evaluacion3.setDescripcion("Selecciona la opción correcta para completar las funciones");
        evaluacion3.setObjetivo("Comprender las partes de una función en Java.");
        evaluacion3.setCurso(curso1);
        evaluacion3.setEstado(EstadoEvaluacion.PENDIENTE);
        evaluacion3.setFecha(fecha.plusDays(7));
        evaluacion3.setPonderacion(20.0);

        evaluacion4.setNombre("Trabajo Práctico 1");
        evaluacion4.setDescripcion("Escriba una función para calcular el área de un rectángulo");
        evaluacion4.setObjetivo("Implementar pseudocódigo para resolver un problema de baja complejidad en Java.");
        evaluacion4.setCurso(curso1);
        evaluacion4.setEstado(EstadoEvaluacion.PENDIENTE);
        evaluacion4.setFecha(fecha.plusDays(7));
        evaluacion4.setPonderacion(40.0);

        evaluacion5.setNombre("Trabajo Práctico 2");
        evaluacion5.setDescripcion("Crea una aplicación web simple para una tienda");
        evaluacion5.setObjetivo("Aplicar conceptos MVC usando Spring boot.");
        evaluacion5.setCurso(curso2);
        evaluacion5.setEstado(EstadoEvaluacion.PENDIENTE);
        evaluacion5.setFecha(fecha.plusMonths(1));
        evaluacion5.setPonderacion(100.0);

        evaluacionRepository.save(evaluacion1);
        evaluacionRepository.save(evaluacion2);
        evaluacionRepository.save(evaluacion3);
        evaluacionRepository.save(evaluacion4);
        evaluacionRepository.save(evaluacion5);
    }
}

