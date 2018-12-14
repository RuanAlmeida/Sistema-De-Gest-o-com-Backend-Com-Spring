package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Integer> {

	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT mail, fone FROM Gestor obj INNER JOIN obj.emails mail INNER JOIN obj.telefones fone WHERE obj.idGestor = :id")
	Object findByIdGestoContatol(@Param("id") Integer id);
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Gestor obj WHERE obj.nome LIKE %:nome%")
	Page<Gestor> findDistinctByNomeContaining(@Param("nome") String nome, Pageable pageRequest);
		
}
