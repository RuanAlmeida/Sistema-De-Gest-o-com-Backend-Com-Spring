package br.com.sistema.gestao.grafico.dto.contatos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContatoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idEmail;
	private List<String> dsEmail;
	
	private Integer idTelefone;
	private List<String> tpTelefone;
	private List<String> dsTelefone;
	
	private Integer gestor;

}
