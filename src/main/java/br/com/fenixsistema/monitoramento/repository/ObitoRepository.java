package br.com.fenixsistema.monitoramento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Obito;

@Repository
public interface ObitoRepository extends JpaRepository<Obito, Long>{

}
