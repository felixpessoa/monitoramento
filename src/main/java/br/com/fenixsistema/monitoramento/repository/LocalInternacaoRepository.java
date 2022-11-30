package br.com.fenixsistema.monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.LocalInternacao;

@Repository
public interface LocalInternacaoRepository extends JpaRepository<LocalInternacao, Long>{
	
	List<LocalInternacao> findByAtivo(boolean ativo); 

}
