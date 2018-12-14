package br.com.sistema.gestao.grafico.dto.instsauede;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstituicaoSaudeNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idGestor;
	
	private Integer idInstituicao;
	private String dsInstituicao;

	private Integer idTipoInstituicao;
	private String dsTipoInstituicao;
	
	private Integer idIbge;
	private String dsMunicipio;
	private String dsUf;
	
	private String dsBairro;

}
