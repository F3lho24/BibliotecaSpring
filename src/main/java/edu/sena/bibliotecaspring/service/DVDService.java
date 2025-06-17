package edu.sena.bibliotecaspring.service;

import edu.sena.bibliotecaspring.model.DVD;
import edu.sena.bibliotecaspring.repository.DVDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional


public class DVDService {

    private final DVDRepository dvdRepository;

    @Autowired
    public DVDService(DVDRepository dvdRepository) {
        this.dvdRepository = dvdRepository;
    }

    public List<DVD> findAll() {
        return dvdRepository.findAll();
    }

    public DVD findById(Long id) {
        return dvdRepository.findById(id).orElse(null);
    }

    public DVD saveDVD(DVD dvd) {
        return dvdRepository.save(dvd);
    }

    public void deleteById(Long id) {
        dvdRepository.deleteById(id);
    }

    public List<DVD> findByGenero(String genero) {
        return dvdRepository.findByGeneroContaining(genero);
    }

    public List<DVD> findByDirector(String director) {
        return dvdRepository.findByDirectorContaining(director);
    }
}

