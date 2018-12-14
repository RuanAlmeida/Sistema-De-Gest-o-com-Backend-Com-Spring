package br.com.sistema.gestao.grafico.dto.endereco;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idEndereco;
	private String dsEndereco;
	private String nrEndereco;
	private String dsComplemento;
	private String nrCep;
	private Integer municipio;
	private Integer gestor;
	private Integer empresagestores;


}