package br.com.fenixsistema.monitoramento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fenixsistema.monitoramento.dto.AltaDTO;
import br.com.fenixsistema.monitoramento.dto.AltaDTONew;
import br.com.fenixsistema.monitoramento.model.Alta;
import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.Paciente;
import br.com.fenixsistema.monitoramento.repository.AltaRepository;
import br.com.fenixsistema.monitoramento.service.exception.DataIntegrityException;
import br.com.fenixsistema.monitoramento.service.exception.ObjectNotFoundException;

@Service
public class AltaService {
	
	@Autowired
	private AltaRepository repository;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private InternacaoService internacaoService;
	
	
	public List<Alta> findAll(){
		return repository.findAll();
	}
	
	public Alta findById(Long id) {
		Optional<Alta> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Alta.class.getName()));
	}
	
	@Transactional
	public Alta create(Alta obj) {
		obj.setId(null);
		obj.setDataCadastro(LocalDateTime.now());
//		Internacao internacao = new Internacao();
		Internacao internacao = internacaoService.findByPact(obj.getPaciente());
		internacao.setFim(obj.getDataAlta());
		internacao.setAtivo(false);
		internacao = internacaoService.update(internacao);
		return repository.save(obj);
	}
	
	public Alta update(Alta obj) {
		Alta newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	private void updateData(Alta newObj, Alta obj) {
		newObj.setTipoDeAlta(obj.getTipoDeAlta());
		newObj.setDataAlta(obj.getDataAlta());
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

	public Alta fromDTO(AltaDTO objDTO) {
		Alta alta = new Alta();
		alta.setId(objDTO.getId());
		alta.setDataAlta(objDTO.getDataAlta());
		alta.setTipoDeAlta(objDTO.getTipoDeAlta());
		return alta;
	}

	public Alta fromDTO(AltaDTONew objDTO) {
		Alta alta = new Alta();
		alta.setDataAlta(objDTO.getDataAlta());
		alta.setTipoDeAlta(objDTO.getTipoDeAlta());
		Paciente paciente = pacienteService.findById(objDTO.getPaciente());
		alta.setPaciente(paciente);
		return alta;
	}

}
