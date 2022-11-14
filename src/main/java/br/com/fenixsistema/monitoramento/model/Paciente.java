package br.com.fenixsistema.monitoramento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paciente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private char sexo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAdmissao;
	private String numeroDoGal;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDaColetaCovid;
	private String amostra;
	private String localDeColetaCovid;
	private String statusCovid;
	private String municipioDeOrigem;
	private String statusInfluenza;
	private String vacina;
	private String comorbidade;
	private boolean tr;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataColetaTr;
	private String localColetaTr;
	private String statusTr;
	@JsonFormat(pattern = "HH:mm dd/MM/yyyy")
	private LocalDateTime dataCadastro;
	private boolean ativo;
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Alta> altas = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Internacao> internacao = new ArrayList<>();
	
	
	
	
	

}
