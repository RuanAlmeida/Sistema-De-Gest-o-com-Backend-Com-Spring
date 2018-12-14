package br.com.sistema.gestao.grafico.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idEndereco;
	private String dsEndereco;
	private String nrEndereco;
	private String dsComplemento;
	private String nrCep;

	
	@ManyToOne
	@JoinColumn(name = "idIbge")
	private Municipio municipio;
	
	@JsonIgnore
	@OneToOne(mappedBy = "endereco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Gestor gestor;

	@JsonIgnore
	@OneToMany(mappedBy = "endereco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EmpresaGestor> empresagestores = new ArrayList<>();

}
