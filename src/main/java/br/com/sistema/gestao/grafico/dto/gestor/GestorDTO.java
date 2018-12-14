package br.com.sistema.gestao.grafico.dto.gestor;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GestorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idGestor;
	private String nome;
	private String nrCpf;
	private String dsLogin;
	private String nrPassword;
	private String dsCargo;

}