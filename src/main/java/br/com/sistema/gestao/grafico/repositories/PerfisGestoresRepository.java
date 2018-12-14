package br.com.sistema.gestao.grafico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.PerfisGestores;

public interface PerfisGestoresRepository extends JpaRepository<PerfisGestores, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Gestor obj INNER JOIN obj.perfisGestores pg INNER JOIN pg.perfilModulosIQS iqs INNER JOIN pg.perfilModulosIRS irs WHERE obj.idGestor = :idGestor")
	Gestor findByIdGestor(@Param("idGestor") Integer idGestor);

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM PerfisGestores obj  WHERE obj.gestor.idGestor = :idGestor AND  obj.perfilModulosIQS.idPerfilModulosIQS = :idPerfilModulosIQS")
	PerfisGestores findVerifyProfileAndGestorIQS(@Param("idGestor") Integer idGestor, @Param("idPerfilModulosIQS") Integer idPerfilModulosIQS);
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM PerfisGestores obj  WHERE obj.gestor.idGestor = :idGestor AND  obj.perfilModulosIRS.idPerfilModulosIRS = :idPerfilModulosIRS")
	PerfisGestores findVerifyProfileAndGestorIRS(@Param("idGestor") Integer idGestor, @Param("idPerfilModulosIRS") Integer idPerfilModulosIRS);

}
