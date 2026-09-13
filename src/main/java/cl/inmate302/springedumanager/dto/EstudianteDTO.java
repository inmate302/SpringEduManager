package cl.inmate302.springedumanager.dto;

import java.time.LocalDate;

public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String email;
    private LocalDate fechaRegistro;

    public EstudianteDTO() {}
    public EstudianteDTO(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}