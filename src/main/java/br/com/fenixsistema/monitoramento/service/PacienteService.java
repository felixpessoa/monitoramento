package br.com.fenixsistema.monitoramento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import br.com.fenixsistema.monitoramento.model.Paciente;
import br.com.fenixsistema.monitoramento.repository.PacienteRepository;
import br.com.fenixsistema.monitoramento.service.exception.DataIntegrityException;
import br.com.fenixsistema.monitoramento.service.exception.ObjectNotFoundException;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository repository;
	
	
	public List<Paciente> findAll(){
		return repository.findAll();
	}
	
	public Paciente findById(Long id) {
		Optional<Paciente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Paciente.class.getName()));
	}
	
	public List<Paciente> findByName(String valor) {
		return repository.findByNome(valor);
	}
	
	
	public Paciente create(Paciente obj) {
		obj.setId(null);
		obj.setAtivo(true);
		obj.setDataCadastro(LocalDateTime.now());
		return repository.save(obj);
	}
	
	public Paciente update(Paciente obj) {
		Paciente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	private void updateData(Paciente newObj, Paciente obj) {
		newObj.setNome(obj.getNome());
		newObj.setSexo(obj.getSexo());
		newObj.setDataNascimento(obj.getDataNascimento());
		newObj.setDataAdmissao(obj.getDataAdmissao());
		newObj.setNumeroDoGal(obj.getNumeroDoGal());
		newObj.setDataDaColetaCovid(obj.getDataDaColetaCovid());
		newObj.setAmostra(obj.getAmostra());
		newObj.setLocalDeColetaCovid(obj.getLocalDeColetaCovid());
		newObj.setStatusCovid(obj.getStatusCovid());
		newObj.setMunicipioDeOrigem(obj.getMunicipioDeOrigem());
		newObj.setStatusInfluenza(obj.getStatusInfluenza());
		newObj.setVacina(obj.getVacina());
		newObj.setComorbidade(obj.getComorbidade());
		newObj.setTr(obj.isTr());
		newObj.setDataColetaTr(obj.getDataColetaTr());
		newObj.setLocalColetaTr(obj.getLocalColetaTr());
		newObj.setStatusTr(obj.getStatusTr());
		newObj.setAtivo(obj.isAtivo());
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionadas");
		}
	}

}
