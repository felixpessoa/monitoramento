package br.com.fenixsistema.monitoramento.model.enuns;

import lombok.Getter;

@Getter
public enum TipoDeAlta {
	
	ALTAMELHORA(1, "Alta Melhora"),
	ALTAEVASAO(2, "Alta Evasão"),
	ALTATRANSFERENCIA(3, "Alta Transferencia");
	
	private int cod;
	private String descricao;
	
	private TipoDeAlta(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public static TipoDeAlta toEnum(Integer cod) {
		if (cod == null ) {
			return null;
		}
		
		for (TipoDeAlta x : TipoDeAlta.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}

}
