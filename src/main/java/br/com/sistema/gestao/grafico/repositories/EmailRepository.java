package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.gestao.grafico.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Integer> {

}
