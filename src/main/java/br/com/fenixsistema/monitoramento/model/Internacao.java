package br.com.fenixsistema.monitoramento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Internacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "localInternacao_id")
	private LocalInternacao localInternacao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate inicio;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fim;
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCadastro;
	private boolean ativo;

}
