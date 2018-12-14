package br.com.sistema.gestao.grafico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@RequiredArgsConstructor
public class TipoInstituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idTipoInstituicao;
	private String dsTipoInstituicao;

	
	@OneToMany(mappedBy = "tipoInstituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<InstituicaoSaude> instituicaosaudes = new ArrayList<>();
}
