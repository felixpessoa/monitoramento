package br.com.fenixsistema.monitoramento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.Paciente;

@Repository
public interface InternacaoRepository extends JpaRepository<Internacao, Long>{
	
	@Query("SELECT obj FROM Internacao obj WHERE obj.paciente =:paciente")
	Internacao findByParciente(@Param("paciente")Paciente paciente);

}
