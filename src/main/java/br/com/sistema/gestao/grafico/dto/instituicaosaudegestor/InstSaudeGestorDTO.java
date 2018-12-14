package br.com.sistema.gestao.grafico.dto.instituicaosaudegestor;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstSaudeGestorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer gestor;
	private List<Integer> instituicaoSaude;

}