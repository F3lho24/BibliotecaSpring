package edu.sena.bibliotecaspring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dvd")
public class DVD extends ElementoBiblioteca {

    private String director;
    private String genero;
    private int duracion;

    // Constructor vacío requerido por JPA
    public DVD() {
        super();
        this.setTipo("DVD");
    }

    // Constructor para crear nuevos DVD
    public DVD(String titulo, String autor, Long anoPublicacion,
               String director, String genero, Long duracion) {
        super(titulo, autor, anoPublicacion != null ? anoPublicacion.intValue() : null, "DVD");
        this.director = director;
        this.genero = genero != null ? genero : "Sin clasificar";
        this.duracion = duracion != null ? duracion.intValue() : 0;
    }

    // Getters y setters
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero != null && !genero.trim().isEmpty() ? genero : "Sin clasificar";
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Método para garantizar que genero nunca sea nulo
    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (genero == null || genero.trim().isEmpty()) {
            genero = "Sin clasificar";
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - Director: " + director + " - Duración: " + duracion + " min - " + genero;
    }
}