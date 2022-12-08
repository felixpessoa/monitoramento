package br.com.fenixsistema.monitoramento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Alta;

@Repository
public interface AltaRepository extends JpaRepository<Alta, Long>{


	@Query("SELECT obj FROM Alta obj WHERE obj.dataAlta >= :dataDe AND obj.dataAlta <= :dataAte")
	List<Alta> findByInternamentoData(@Param("dataDe") LocalDate dataDe, @Param("dataAte") LocalDate dataAte);
}
