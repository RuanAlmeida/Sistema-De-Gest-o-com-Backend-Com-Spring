package br.com.sistema.gestao.grafico.dto.gestor;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GestorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String nrCpf;
	private String dsLogin;
	private String nrPassword;
	private String dsCargo;
	private Integer idEmmpresagestor;
	private Integer idEndereco;
	private String dsEndereco;
	private String nrEndereco;
	private String dsComplemento;
	private String nrCep;
	private Integer idIbge;
	private String dsMunicipio;
	private String dsUf;
}
