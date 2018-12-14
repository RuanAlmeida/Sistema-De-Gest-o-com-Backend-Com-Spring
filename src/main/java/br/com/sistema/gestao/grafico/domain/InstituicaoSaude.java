package br.com.sistema.gestao.grafico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class InstituicaoSaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idInstituicao;
	
	private String dsInstituicao;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idTipoInstituicao")
	private TipoInstituicao tipoInstituicao;


	
	@ManyToOne
	@JoinColumn(name = "idBairro")
	private Bairro bairro;

	@JsonIgnore
	@OneToMany(mappedBy = "instituicaoSaude", fetch = FetchType.LAZY)
	private List<InstSaudeGestor> instSaudeGestores = new ArrayList<>();

}
