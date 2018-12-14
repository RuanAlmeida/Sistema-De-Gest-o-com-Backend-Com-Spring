package br.com.sistema.gestao.grafico.dto.empresagestor;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmpresaGestorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nrCnpj;
	private Integer idEmpresaGestor;
	private String dsRazaoSocial;
	private String nrCnes;
	private Integer endereco;
	private Integer gestores;
}