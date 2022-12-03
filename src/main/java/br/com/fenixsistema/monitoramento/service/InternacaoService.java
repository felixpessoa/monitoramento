package br.com.fenixsistema.monitoramento.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fenixsistema.monitoramento.dto.InternacaoDTO;
import br.com.fenixsistema.monitoramento.dto.InternacaoDTONew;
import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.LocalInternacao;
import br.com.fenixsistema.monitoramento.model.Paciente;
import br.com.fenixsistema.monitoramento.repository.InternacaoRepository;
import br.com.fenixsistema.monitoramento.service.exception.DataIntegrityException;
import br.com.fenixsistema.monitoramento.service.exception.ObjectNotFoundException;

@Service
public class InternacaoService {
	
	@Autowired
	private InternacaoRepository repository;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private LocalInternacaoService localInternacaoService;
	
	
	
	public List<Internacao> findAll(){
		return repository.findAll();
	}
	
	public List<Internacao> findAllAtivos(){
		return repository.findByAtivo(true);
	}
	
	public List<Internacao> findBySetor(List<Long> ids){
		List<LocalInternacao> listSetor = new ArrayList<>();
		for(Long id: ids){
			LocalInternacao obj = localInternacaoService.findById(id);
			listSetor.add(obj);
		}
		return repository.findByAtivoAndLocalInternacaoIn(true, listSetor);
	}
	
	public Internacao findById(Long id) {
		Optional<Internacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Internacao.class.getName()));
	}
	
	
	public Internacao create(Internacao obj) {
		
		if (repository.findByParciente(obj.getPaciente())!= null) {
			throw new ObjectNotFoundException("Paciente já Internado.");
		}
		
		obj.setId(null);
		obj.setDataCadastro(LocalDateTime.now());
		obj.setAtivo(true);
		obj = repository.save(obj);
		LocalInternacao localInternacao = obj.getLocalInternacao();
//		LocalInternacao localInternacao = new LocalInternacao();
		localInternacao.getInternacao().add(obj);
		Paciente paciente = obj.getPaciente();
		paciente.getInternacao().add(obj);
		return obj;
	}
	
	public Internacao update(Internacao obj) {
		Internacao newObj = findById(obj.getId());
		updateData(newObj, obj);
		LocalInternacao localInternacao = obj.getLocalInternacao();
		localInternacao.getInternacao().add(obj);
		Paciente paciente = obj.getPaciente();
		paciente.getInternacao().add(obj);
		return repository.save(newObj);
	}

	private void updateData(Internacao newObj, Internacao obj) {
		newObj.setLocalInternacao(obj.getLocalInternacao());
		newObj.setInicio(obj.getInicio());
		newObj.setFim(obj.getFim());
		newObj.setPaciente(obj.getPaciente());
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
	
	public Internacao findByPact(Paciente paciente) {
		return repository.findByParciente(paciente);
	}

	public Internacao fromDTO(InternacaoDTO objDTO) {
		Internacao obj = new Internacao();
		LocalInternacao localInternacao = localInternacaoService.findById(objDTO.getLocalInternacao());
		obj.setId(objDTO.getId());
		obj.setLocalInternacao(localInternacao);
		obj.setInicio(objDTO.getInicio());
		obj.setFim(objDTO.getFim());
		Paciente paciente = pacienteService.findById(objDTO.getPaciente());
		obj.setPaciente(paciente);
		obj.setAtivo(objDTO.isAtivo());
		return obj;
	}

	public Internacao fromDTO(InternacaoDTONew objDTO) {
		Internacao obj = new Internacao();
		LocalInternacao localInternacao = localInternacaoService.findById(objDTO.getLocalInternacao());
		obj.setLocalInternacao(localInternacao);
		obj.setInicio(objDTO.getInicio());
		obj.setFim(objDTO.getFim());
		Paciente paciente = pacienteService.findById(objDTO.getPaciente());
		obj.setPaciente(paciente);
		obj.setAtivo(objDTO.isAtivo());
		return obj;
	}

}
