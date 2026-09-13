package cl.inmate302.springedumanager.dto;

import java.time.LocalDate;
import cl.inmate302.springedumanager.domain.Evaluacion.EstadoEvaluacion;

public class EvaluacionDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String objetivo;
    private Double ponderacion;
    private LocalDate fecha;
    private EstadoEvaluacion estado;
    private Long cursoId;

    public EvaluacionDTO() {}
    public EvaluacionDTO(Long id, String nombre, String descripcion, String objetivo, Double ponderacion, LocalDate fecha, EstadoEvaluacion estado, Long estudianteId, Long cursoId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.ponderacion = ponderacion;
        this.fecha = fecha;
        this.estado = estado;
        this.cursoId = cursoId;
    }
    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getObjetivo() {
        return objetivo;
    }

    public Double getPonderacion() {
        return ponderacion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public EstadoEvaluacion getEstado() {
        return estado;
    }

    public Long getCursoId() {
        return cursoId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void setPonderacion(Double ponderacion) {
        this.ponderacion = ponderacion;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setEstado(EstadoEvaluacion estado) {
        this.estado = estado;
    }
    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

}
