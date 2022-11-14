package br.com.fenixsistema.monitoramento.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataAlta;
	private String tipoDeAlta;
	private Long paciente;
	
	
	
	

}
