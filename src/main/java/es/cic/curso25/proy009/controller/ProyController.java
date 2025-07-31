package es.cic.curso25.proy009.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proy009.model.Arbol;
import es.cic.curso25.proy009.service.ProyService;

@RestController
@RequestMapping("/arbol")
public class ProyController {

    @Autowired
    ProyService proyService;

    @GetMapping("/{id}")
    public Optional<Arbol> getById(@PathVariable(required = true) Long id){
        return proyService.getById(id);
    }

    @GetMapping("")
    public List<Arbol> getAll(){
        return proyService.getAll();
    }

    @PostMapping("")
    public Arbol create(@RequestBody Arbol arbol){
        if (arbol.getId() != null) {
            throw new CreationException();
        }
        return proyService.create(arbol);
    }

    @PutMapping("")
    public void update(@RequestBody Arbol arbol){
        if (proyService.getById(arbol.getId()) == null) {
            throw new CreationException("Estas intentando actualizar un arbol inexistente");
        }
        proyService.update(arbol);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        if (proyService.getById(id) == null) {
            throw new CreationException("Estas intentando borrar un arbol que ya no existe");
        }
        proyService.delete(id);
    }
}
