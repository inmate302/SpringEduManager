package cl.inmate302.springedumanager.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "evaluaciones")
public class Evaluacion {
    
    public enum EstadoEvaluacion {
    PENDIENTE, COMPLETADA, CALIFICADA
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 150)
    private String nombre;
    
    @Column(length = 500)
    private String descripcion;

    @Column(length = 300)
    private String objetivo;
    
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2) DEFAULT 0.0")
    private Double ponderacion;
    
    @Column(nullable = false)
    private LocalDate fecha = LocalDate.now().plusDays(7);
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEvaluacion estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id", nullable = false)
    @JsonIgnore 
    private Curso curso;

    public Evaluacion() {}

    public Evaluacion(String nombre, String descripcion, String objetivo, int ponderacion, LocalDate fecha, EstadoEvaluacion estado, Estudiante estudiante, Curso curso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.ponderacion = 0.0;
        this.fecha = fecha;
        this.estado = EstadoEvaluacion.PENDIENTE;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public Double getPonderacion() { return ponderacion; }
    public void setPonderacion(Double ponderacion) { this.ponderacion = ponderacion; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public EstadoEvaluacion getEstado() { return estado; }
    public void setEstado(EstadoEvaluacion estado) { this.estado = estado; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
}

