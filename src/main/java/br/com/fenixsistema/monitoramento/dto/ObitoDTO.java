package br.com.fenixsistema.monitoramento.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.fenixsistema.monitoramento.model.Obito;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ObitoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataObito;
	private String descricao;
	private Long paciente;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCadastro;
	
	
	public ObitoDTO(Obito obj) {
		super();
		this.id = obj.getId();
		this.dataObito = obj.getDataObito();
		this.descricao = obj.getDescricao();
		this.dataCadastro = obj.getDataCadastro();
	}
	
	

}
