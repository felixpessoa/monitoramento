package br.com.fenixsistema.monitoramento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Alta;

@Repository
public interface AltaRepository extends JpaRepository<Alta, Long>{

}
