package br.com.fenixsistema.monitoramento.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.fenixsistema.monitoramento.model.LocalInternacao;
import br.com.fenixsistema.monitoramento.repository.LocalInternacaoRepository;
import br.com.fenixsistema.monitoramento.service.exception.DataIntegrityException;
import br.com.fenixsistema.monitoramento.service.exception.ObjectNotFoundException;

@Service
public class LocalInternacaoService {
	
	@Autowired
	private LocalInternacaoRepository repository;
	
	
	
	public List<LocalInternacao> findAll(){
		return repository.findAll();
	}
	
	public LocalInternacao findById(Long id) {
		Optional<LocalInternacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + LocalInternacao.class.getName()));
	}
	
	
	public LocalInternacao create(LocalInternacao obj) {
		obj.setId(null);
		obj.setDataCadastro(LocalDateTime.now());
		obj.setAtivo(true);
		return repository.save(obj);
	}
	
	public LocalInternacao update(LocalInternacao obj) {
		LocalInternacao newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	private void updateData(LocalInternacao newObj, LocalInternacao obj) {
		newObj.setNome(obj.getNome());
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

//	public LocalInternacao fromDTO(LocalInternacaoDTO objDTO) {
//		LocalInternacao alta = new LocalInternacao();
//		alta.setId(objDTO.getId());
//		alta.setDataLocalInternacao(objDTO.getDataLocalInternacao());
//		alta.setTipoDeLocalInternacao(objDTO.getTipoDeLocalInternacao());
//		return alta;
//	}
//
//	public LocalInternacao fromDTO(LocalInternacaoDTONew objDTO) {
//		LocalInternacao alta = new LocalInternacao();
//		alta.setDataLocalInternacao(objDTO.getDataLocalInternacao());
//		alta.setTipoDeLocalInternacao(objDTO.getTipoDeLocalInternacao());
//		Paciente paciente = pacienteService.findById(objDTO.getPaciente());
//		alta.setPaciente(paciente);
//		return alta;
//	}

}
