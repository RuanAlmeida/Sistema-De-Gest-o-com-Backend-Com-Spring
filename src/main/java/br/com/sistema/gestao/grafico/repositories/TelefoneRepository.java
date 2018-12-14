package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.gestao.grafico.domain.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Integer> {

}
