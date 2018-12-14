package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.InstituicaoSaude;

public interface InstituicaoSaudeRepository extends JpaRepository<InstituicaoSaude, Integer> {

	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM InstituicaoSaude obj WHERE obj.idInstituicao = :id")
	InstituicaoSaude findNaMao(@Param("id") Integer id);
	
}
