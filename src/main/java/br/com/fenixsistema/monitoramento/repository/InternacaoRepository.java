package br.com.fenixsistema.monitoramento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.LocalInternacao;
import br.com.fenixsistema.monitoramento.model.Paciente;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long>{
	
	@Query("SELECT obj FROM Internacao obj WHERE obj.paciente =:paciente and obj.ativo = true")
	Internacao findByParciente(@Param("paciente")Paciente paciente);

	
	@Query("SELECT obj FROM Internacao obj WHERE obj.inicio >= :dataDe AND obj.inicio <= :dataAte")
	List<Internacao> findByInternamentoData(@Param("dataDe") LocalDate dataDe, @Param("dataAte") LocalDate dataAte);
	
	@Query("SELECT COUNT(1) FROM Internacao obj WHERE obj.inicio >= :dataDe AND obj.inicio <= :dataAte")
	int findByInternamentoMes(@Param("dataDe") LocalDate dataDe, @Param("dataAte") LocalDate dataAte);
	
	List<Internacao> findByAtivoAndLocalInternacaoIn(boolean ativo, List<LocalInternacao> obj);
	
	List<Internacao> findByAtivo(boolean ativo);

}
