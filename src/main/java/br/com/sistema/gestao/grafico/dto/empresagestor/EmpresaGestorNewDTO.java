package br.com.sistema.gestao.grafico.dto.empresagestor;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpresaGestorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nrCnpj;
	private Integer idEmpresaGestor;
	private String dsRazaoSocial;
	private String nrCnes;
	
	private String dsEndereco;
	private String nrEndereco;
	private String dsComplemento;
	private String nrCep;
	private Integer idIbge;
	private String dsMunicipio;
	private String dsUf;

}
