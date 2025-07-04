package edu.sena.bibliotecaspring.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "revista")
public class Revista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer numeroEdicion = 1;
    private Long id;

    private String categoria;
    private int numero;
    private String editorial;
    private String titulo;
    private String autor;
    private Integer anoPublicacion;


    // Constructor vacío requerido por JPA
    public Revista() {
        this.anoPublicacion = LocalDate.now().getYear();
        this.categoria = "General";
        this.numero = 1;
    }

    // Constructor para crear nuevas revistas
    public Revista(String titulo, String autor, Integer anoPublicacion,
                   String categoria, int numero, String editorial) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = (categoria != null && !categoria.trim().isEmpty()) ? categoria : "General";
        this.numero = numero;
        this.editorial = editorial;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = (categoria != null && !categoria.trim().isEmpty()) ? categoria : "General";
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(Integer anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    // Método para garantizar que categoría nunca sea nula
    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (categoria == null || categoria.trim().isEmpty()) {
            categoria = "General";
        }
    }

    @Override
    public String toString() {
        return "Revista: " + titulo + " - Autor: " + autor + " - Año: " + anoPublicacion +
                " - Edición: " + numero + " - " + categoria + " - Editorial: " + editorial;
    }
}