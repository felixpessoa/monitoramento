package br.com.fenixsistema.monitoramento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fenixsistema.monitoramento.dto.ObitoDTO;
import br.com.fenixsistema.monitoramento.dto.ObitoDTONew;
import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.Obito;
import br.com.fenixsistema.monitoramento.model.Paciente;
import br.com.fenixsistema.monitoramento.repository.ObitoRepository;
import br.com.fenixsistema.monitoramento.service.exception.DataIntegrityException;
import br.com.fenixsistema.monitoramento.service.exception.ObjectNotFoundException;

@Service
public class ObitoService {
	
	@Autowired
	private ObitoRepository repository;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private InternacaoService internacaoService;
	
	
	public List<Obito> findAll(){
		return repository.findAll();
	}
	
	public Obito findById(Long id) {
		Optional<Obito> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Obito.class.getName()));
	}
	
	@Transactional
	public Obito create(Obito obj) {
		obj.setId(null);
		obj.setDataCadastro(LocalDateTime.now());
//		Internacao internacao = new Internacao();
		Internacao internacao = internacaoService.findByPact(obj.getPaciente());
		internacao.setFim(obj.getDataObito());
		internacao.setAtivo(false);
		internacao = internacaoService.update(internacao);
		
		Paciente pact = pacienteService.findById(obj.getPaciente().getId());
		pact.setAtivo(false);
		pact = pacienteService.update(pact);
		
		return repository.save(obj);
	}
	
	public Obito update(Obito obj) {
		Obito newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	private void updateData(Obito newObj, Obito obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataObito(obj.getDataObito());
		newObj.setPaciente(obj.getPaciente());
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionadas");
		}
	}

	public Obito fromDTO(ObitoDTO objDTO) {
		Obito obito = new Obito();
		obito.setId(objDTO.getId());
		obito.setDataObito(objDTO.getDataObito());
		obito.setDescricao(objDTO.getDescricao());
		return obito;
	}

	public Obito fromDTO(ObitoDTONew objDTO) {
		Obito alta = new Obito();
		alta.setDataObito(objDTO.getDataObito());
		alta.setDescricao(objDTO.getDescricao());
		Paciente paciente = pacienteService.findById(objDTO.getPaciente());
		alta.setPaciente(paciente);
		return alta;
	}

}
