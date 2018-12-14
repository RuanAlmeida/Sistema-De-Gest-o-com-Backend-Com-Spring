package br.com.sistema.gestao.grafico.dto.instsauede;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstituicaoSaudeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idInstituicao;
	private String dsInstituicao;
	
}