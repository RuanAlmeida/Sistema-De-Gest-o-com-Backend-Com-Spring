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
public class Visao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVisao;
	private String dsVisao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "visao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ModuloIQS> modulosIQS = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "visao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ModuloIRS> modulosIRS = new ArrayList<>();

}
