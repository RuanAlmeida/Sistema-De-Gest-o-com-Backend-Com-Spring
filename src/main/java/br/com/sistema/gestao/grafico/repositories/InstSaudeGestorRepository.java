package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import br.com.sistema.gestao.grafico.domain.InstSaudeGestor;

public interface InstSaudeGestorRepository extends JpaRepository<InstSaudeGestor, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM InstSaudeGestor obj INNER JOIN obj.instituicaoSaude its INNER JOIN obj.gestor gt WHERE gt.idGestor = :idGestor")
	InstSaudeGestor findByIdGestores(@Param("idGestor") Integer idGestor);
	
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM InstSaudeGestor obj INNER JOIN obj.instituicaoSaude its INNER JOIN obj.gestor gt WHERE its.idInstituicao = :idInstituicao AND gt.idGestor = :idGestor")
	InstSaudeGestor findByIdGestorAndIdInstituicao(@Param("idInstituicao") Integer idInstituicao, @Param("idGestor") Integer idGestor);

}
