package edu.sena.bibliotecaspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.property.access.spi.Getter;

import java.time.LocalDate;

@Entity
public class Revista extends ElementoBiblioteca {
    private String categoria;
    private int numero;
    private String editorial;

    @Column(name = "numero_Edicion", nullable = false)
    private Integer numeroEdicion = 1; // Inicializa con un valor por defecto



    public Revista() {
    }

    public Revista(String titulo, LocalDate fechaPublicacion, String categoria,
                   int numero, String editorial, Integer numeroEdicion) {
        super(titulo, fechaPublicacion);
        this.categoria = categoria;
        this.numero = numero;
        this.editorial = editorial;
        this.numeroEdicion = numeroEdicion; // Añade esta línea
    }

    public Integer getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(Integer numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}
