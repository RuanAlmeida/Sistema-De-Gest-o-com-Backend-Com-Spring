package br.com.sistema.gestao.grafico.dto.perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerfilNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<String> dsPerfil = new ArrayList<>();
	
	private Integer idGestor;
	
	private List<Integer> moduloIQS = new ArrayList<>();
	
	private List<Integer> moduloIRS = new ArrayList<>();
	
}
