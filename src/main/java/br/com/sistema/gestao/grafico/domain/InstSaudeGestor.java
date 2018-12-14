package br.com.sistema.gestao.grafico.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class InstSaudeGestor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idInstSaudeGestor;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idGestor")
	private Gestor gestor;
	
	
	@ManyToOne
	@JoinColumn(name = "idInstituicao")
	private InstituicaoSaude instituicaoSaude;
}
