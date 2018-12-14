package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.EmpresaGestor;

public interface EmpresaGestorRepository extends JpaRepository<EmpresaGestor, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM EmpresaGestor obj INNER JOIN obj.gestores gest WHERE gest.idGestor = :id")
	EmpresaGestor findByIdGestor(@Param("id") Integer id);
	
	
//	@Transactional(readOnly=true)
//	@Query("SELECT DISTINCT obj FROM EmpresaGestor obj WHERE obj.nrCnpj = :id")
//	Optional<EmpresaGestor> findByNrCnpj(@Param("id") String id);

}
