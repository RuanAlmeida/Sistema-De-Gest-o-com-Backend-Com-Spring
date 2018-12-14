package br.com.sistema.gestao.grafico.domain;

import java.io.Serializable;
import java.util.Date;

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
public class LogSistema implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLogSistema;
	private Date dtRegistroUso;
	private String noTabelaAcessada;
	private String dsEspelho;
	
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idGestor")
	private Gestor gestor;

}
