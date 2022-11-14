package br.com.fenixsistema.monitoramento.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fenixsistema.monitoramento.model.Internacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class InternacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long localInternacao;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime inicio;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime fim;
	private Long paciente;
	
	
	public InternacaoDTO() {
		super();
	}
	
	public InternacaoDTO(Internacao obj) {
		super();
		this.id = obj.getId();
		this.localInternacao = obj.getLocalInternacao().getId();
		this.inicio = obj.getInicio();
		this.fim = obj.getFim();
		this.paciente = obj.getPaciente().getId();
	}



	
	
	
	
}
