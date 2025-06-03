package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.Libro;
import edu.sena.bibliotecaspring.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/libros")
public class LibroTestController {

    private final LibroService libroService;

    public LibroTestController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibrosById(@PathVariable Long id) {
        Libro libro = libroService.findById(id);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.save(libro);
        return ResponseEntity.status(201).body(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        Libro LibroExistente = libroService.findById(id);
        if (LibroExistente == null) {
            return ResponseEntity.notFound().build();
        }
        libro.setId(id);
        Libro libroActualizado = libroService.save(libro);
        return ResponseEntity.ok(libroActualizado);
    }
}
