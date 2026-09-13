package cl.inmate302.springedumanager.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String password; 
    
    @Column(nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USER;

    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore 
    private Set<Curso> cursos = new HashSet<>();
    /* TODO implementar calificaciones
    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Calificacion> calificaciones = new HashSet<>();*/

    public Estudiante() {}

    public Estudiante(String nombre, String email, String password, Rol rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.fechaRegistro = LocalDate.now();
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public Rol getRol() { return rol; }
    public Set<Curso> getCursos() { return cursos; }
    //public Set<Calificacion> getCalificaciones() { return calificaciones; }
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public void setCursos(Set<Curso> cursos) { this.cursos = cursos; }
    //public void setCalificaciones(Set<Calificacion> calificaciones) { this.calificaciones = calificaciones; }
}
