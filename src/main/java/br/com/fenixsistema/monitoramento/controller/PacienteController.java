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

import br.com.fenixsistema.monitoramento.model.Paciente;
import br.com.fenixsistema.monitoramento.service.PacienteService;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

	@Autowired
	private PacienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Paciente> findByIdCliente(@PathVariable Long id) {
		Paciente obj = service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Paciente>> findAll() {
		List<Paciente> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<Paciente> insert(@RequestBody Paciente obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Paciente> update(@PathVariable Long id, @RequestBody Paciente obj) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delet(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
