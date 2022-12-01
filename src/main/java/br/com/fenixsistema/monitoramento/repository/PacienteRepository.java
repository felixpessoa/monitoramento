package br.com.fenixsistema.monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fenixsistema.monitoramento.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	
	@Query("SELECT p FROM Paciente p WHERE  p.nome LIKE %:nome%")
	List<Paciente> findByNome(@Param("nome") String nome);

}
