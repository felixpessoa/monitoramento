package br.com.fenixsistema.monitoramento.dto;

import java.io.Serializable;
import java.time.LocalDate;

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
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate inicio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fim;
	private Long paciente;
	private boolean ativo;
	
	
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
		this.ativo = obj.isAtivo();
	}



	
	
	
	
}
