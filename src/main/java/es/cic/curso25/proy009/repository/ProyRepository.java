package es.cic.curso25.proy009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso25.proy009.model.Arbol;

@Repository
public interface ProyRepository extends JpaRepository<Arbol, Long>{

}
