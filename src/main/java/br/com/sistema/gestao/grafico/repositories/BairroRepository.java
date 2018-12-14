package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Integer> {
	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Bairro obj WHERE obj.dsBairro = :dsBairro")
	Bairro FindByDsBairro(@Param("dsBairro") String dsBairro);

}
