package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.Revista;
import edu.sena.bibliotecaspring.service.RevistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revistas")
@CrossOrigin(origins = "*")
public class RevistaRestController {

    private final RevistaService revistaService;

    public RevistaRestController(RevistaService revistaService) {
        this.revistaService = revistaService;
    }

    @GetMapping
    public ResponseEntity<List<Revista>> obtenerTodasLasRevistas() {
        try {
            List<Revista> revistas = revistaService.findAll();
            return ResponseEntity.ok(revistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revista> obtenerRevistaPorId(@PathVariable Long id) {
        try {
            Revista revista = revistaService.findById(id);
            if (revista == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(revista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Revista> crearRevista(@RequestBody Revista revista) {
        try {
            if (revista.getTitulo() == null || revista.getTitulo().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (revista.getNumero() <= 0) {
                revista.setNumero(1);
            }
            Revista revistaGuardada = revistaService.save(revista);
            return ResponseEntity.status(HttpStatus.CREATED).body(revistaGuardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Revista> actualizarRevista(@PathVariable Long id, @RequestBody Revista revista) {
        try {
            Revista existente = revistaService.findById(id);
            if (existente == null) {
                return ResponseEntity.notFound().build();
            }
            revista.setId(id); // Asegura que el ID es el correcto
            if (revista.getTitulo() == null || revista.getTitulo().trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (revista.getNumero() <= 0) {
                revista.setNumero(1);
            }
            Revista revistaActualizada = revistaService.save(revista);
            return ResponseEntity.ok(revistaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRevista(@PathVariable Long id) {
        try {
            Revista existente = revistaService.findById(id);
            if (existente == null) {
                return ResponseEntity.notFound().build();
            }
            revistaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/categoria")
    public ResponseEntity<List<Revista>> buscarPorCategoria(@RequestParam("q") String categoria) {
        try {
            if (categoria == null || categoria.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<Revista> revistas = revistaService.findByCategoria(categoria);
            return ResponseEntity.ok(revistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/editorial")
    public ResponseEntity<List<Revista>> buscarPorEditorial(@RequestParam("q") String editorial) {
        try {
            if (editorial == null || editorial.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<Revista> revistas = revistaService.findByEditorial(editorial);
            return ResponseEntity.ok(revistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar/autor")
    public ResponseEntity<List<Revista>> buscarPorAutor(@RequestParam("q") String autor) {
        try {
            if (autor == null || autor.trim().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            List<Revista> revistas = revistaService.findByAutor(autor);
            return ResponseEntity.ok(revistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Revista>> buscarRevistas(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String editorial,
            @RequestParam(required = false) String autor) {
        try {
            List<Revista> revistas;
            if (categoria != null && !categoria.trim().isEmpty()) {
                revistas = revistaService.findByCategoria(categoria);
            } else if (editorial != null && !editorial.trim().isEmpty()) {
                revistas = revistaService.findByEditorial(editorial);
            } else if (autor != null && !autor.trim().isEmpty()) {
                revistas = revistaService.findByAutor(autor);
            } else {
                revistas = revistaService.findAll();
            }
            return ResponseEntity.ok(revistas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<Revista>> buscarPorNumero(@PathVariable int numero) {
        try {
            if (numero <= 0) {
                return ResponseEntity.badRequest().build();
            }
            List<Revista> todasRevistas = revistaService.findAll();
            List<Revista> revistasPorNumero = todasRevistas.stream()
                    .filter(revista -> revista.getNumero() == numero)
                    .toList();
            return ResponseEntity.ok(revistasPorNumero);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}