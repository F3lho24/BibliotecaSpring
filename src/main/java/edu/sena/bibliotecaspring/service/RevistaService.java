package edu.sena.bibliotecaspring.service;

import edu.sena.bibliotecaspring.model.Revista;
import edu.sena.bibliotecaspring.repository.RevistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RevistaService {

    @Autowired
    private RevistaRepository revistaRepository;

    public List<Revista> findAll() {
        return revistaRepository.findAll();
    }

    public void save(Revista revista) {
        revistaRepository.save(revista);
    }

    public Revista findById(Long id) {
        Optional<Revista> optional = revistaRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteById(Long id) {
        revistaRepository.deleteById(id);
    }

    public List<Revista> findByCategoria(String categoria) {
        return revistaRepository.findByCategoriaContaining(categoria);
    }

    public List<Revista> findByEditorial(String editorial) {
        return revistaRepository.findByEditorialContaining(editorial);
    }
}