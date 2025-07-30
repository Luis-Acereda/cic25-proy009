package es.cic.curso25.proy009.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso25.proy009.model.Rama;

@Repository
public interface RamaRepository extends JpaRepository<Rama, Long>{

}
