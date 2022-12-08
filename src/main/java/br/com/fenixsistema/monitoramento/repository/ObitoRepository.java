package br.com.fenixsistema.monitoramento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Obito;

@Repository
public interface ObitoRepository extends JpaRepository<Obito, Long>{

	
	@Query("SELECT obj FROM Obito obj WHERE obj.dataObito >= :dataDe AND obj.dataObito <= :dataAte")
	List<Obito> findByInternamentoData(@Param("dataDe") LocalDate dataDe, @Param("dataAte") LocalDate dataAte);

}
