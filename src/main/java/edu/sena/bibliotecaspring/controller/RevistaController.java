package edu.sena.bibliotecaspring.controller;

import edu.sena.bibliotecaspring.model.Revista;
import edu.sena.bibliotecaspring.service.RevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/revistas")
public class RevistaController {

    private final RevistaService revistaService;

    public RevistaController(RevistaService revistaService) {
        this.revistaService = revistaService;
    }

    @GetMapping
    public String listarRevistas(Model model) {
        model.addAttribute("revistas", revistaService.findAll());
        return "revistas/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        try {
            Revista revista = new Revista();
            revista.setAnoPublicacion(LocalDate.now().getYear()); // Asume que el campo es Integer/int
            revista.setCategoria("General");
            revista.setNumero(1);

            model.addAttribute("revista", revista);
            return "revistas/formulario";
        }
        catch (Exception e) {
            // Loggear el error
            model.addAttribute("error", "Error al inicializar el formulario");
            return "error"; // Vista de error
        }
    }

    @PostMapping("/guardar")
    public String guardarRevista(@ModelAttribute Revista revista) {
        // Validación adicional para asegurar que categoría nunca sea nula
        if (revista.getCategoria() == null || revista.getCategoria().trim().isEmpty()) {
            revista.setCategoria("General");
        }

        try {
            revistaService.save(revista);
            return "redirect:/revistas";
        } catch (Exception e) {
            // Registrar el error
            System.err.println("Error al guardar revista: " + e.getMessage());
            e.printStackTrace();

            // Redirigir a una página de error
            return "error";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("revista", revistaService.findById(id));
        return "revistas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarRevista(@PathVariable Long id) {
        revistaService.deleteById(id);
        return "redirect:/revistas";
    }

    @GetMapping("/buscar")
    public String buscarPorCriterio(@RequestParam(required = false) String categoria,
                                    @RequestParam(required = false) String editorial,
                                    @RequestParam(required = false) String autor,
                                    Model model) {
        if (categoria != null && !categoria.isEmpty()) {
            model.addAttribute("revistas", revistaService.findByCategoria(categoria));
        } else if (editorial != null && !editorial.isEmpty()) {
            model.addAttribute("revistas", revistaService.findByEditorial(editorial));
        } else if (autor != null && !autor.isEmpty()) {
            model.addAttribute("revistas", revistaService.findByAutor(autor));
        } else {
            model.addAttribute("revistas", revistaService.findAll());
        }
        return "revistas/lista";
    }
}