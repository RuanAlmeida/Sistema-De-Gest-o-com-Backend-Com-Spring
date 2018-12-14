package br.com.sistema.gestao.grafico.dto.modulos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModulosIRSDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idModuloIRS;
	private String dsModuloIRS;
}
