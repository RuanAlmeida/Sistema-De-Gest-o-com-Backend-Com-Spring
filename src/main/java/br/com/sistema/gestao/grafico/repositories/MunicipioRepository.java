package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.gestao.grafico.domain.Municipio;

public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

}
