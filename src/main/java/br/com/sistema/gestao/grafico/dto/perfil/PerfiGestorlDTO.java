package br.com.sistema.gestao.grafico.dto.perfil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerfiGestorlDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idGestor;
	
	private List<Integer> perfilIQS = new ArrayList<>();
	
	private List<Integer> perfilIRS = new ArrayList<>();

}