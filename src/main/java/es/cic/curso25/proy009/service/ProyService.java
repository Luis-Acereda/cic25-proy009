package es.cic.curso25.proy009.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.repository.ArbolRepository;
import es.cic.curso25.proy009.repository.RamaRepository;

@Service
public class ProyService {

    @Autowired
    ArbolRepository arbolRepository;

    @Autowired
    RamaRepository ramaRepository;

    public Arbol create(Arbol arbol){
        arbolRepository.save(arbol);
        return arbol;
    }

    @Transactional(readOnly = true)
    public Optional<Arbol> getById(Long id){
        return arbolRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Arbol> getAll(){
        return arbolRepository.findAll();
    }

    public void update(Arbol newArbol){
        arbolRepository.save(newArbol);
    }

    public void delete(Long id){
        arbolRepository.deleteById(id);
    }
}
