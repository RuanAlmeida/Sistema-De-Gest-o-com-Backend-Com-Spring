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
public class Gestor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGestor;
	private String nome;
	private String nrCpf;
	private String dsLogin;
	private String nrPassword;
	private String dsCargo;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "nrCnpj")
	private EmpresaGestor empresagestor;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "gestor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Email> emails = new ArrayList<>();


	@OneToMany(mappedBy = "gestor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Telefone> telefones = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "gestor")
	private List<LogSistema> logssistemas = new ArrayList<>();


	@OneToMany(mappedBy = "gestor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<InstSaudeGestor> instSaudeGestores = new ArrayList<>();


	@OneToMany(mappedBy = "gestor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<PerfisGestores> perfisGestores = new ArrayList<>();

}
