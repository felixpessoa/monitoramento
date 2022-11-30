package br.com.fenixsistema.monitoramento.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fenixsistema.monitoramento.dto.InternacaoDTO;
import br.com.fenixsistema.monitoramento.dto.InternacaoDTONew;
import br.com.fenixsistema.monitoramento.model.Internacao;
import br.com.fenixsistema.monitoramento.model.LocalInternacao;
import br.com.fenixsistema.monitoramento.service.InternacaoService;

@RestController
@RequestMapping("/api/internacao")
public class InternacaoControlle {
	
	@Autowired
	private InternacaoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Internacao> findByIdInternacao(@PathVariable Long id) {
		Internacao obj = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@GetMapping("/setor/{ids}")
	public ResponseEntity<List<Internacao>> findBysetor(@PathVariable List<Long> ids) {
		List<Internacao> obj = service.findBySetor(ids);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}

	@GetMapping("/ativos")
	public ResponseEntity<List<Internacao>> findAllAtivos() {
		List<Internacao> list = service.findAllAtivos();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<List<Internacao>> findAll() {
		List<Internacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Internacao> insert(@RequestBody InternacaoDTONew objDTO) {
		Internacao obj = service.fromDTO(objDTO); 
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Internacao> update(@PathVariable Long id, @RequestBody InternacaoDTO objDTO) {
		objDTO.setId(id);
		Internacao obj = service.fromDTO(objDTO);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
