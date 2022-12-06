package br.com.fenixsistema.monitoramento.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AltaDTONew implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAlta;
	private Integer tipoDeAlta;
	private String descricao;
	private Long paciente;
	
	
	
	

}
