package br.com.sistema.gestao.grafico.dto.contatos;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContatoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Integer> idEmail;
	private List<String> dsEmail;
	
	private List<Integer> idTelefone;
	private List<String> tpTelefone;
	private List<String> dsTelefone;
	
	private Integer idGestor;
	private Integer Contato;
	private char tipoContato;

}