package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.gestao.grafico.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

}
