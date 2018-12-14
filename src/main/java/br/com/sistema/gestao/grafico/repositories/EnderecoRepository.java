package br.com.sistema.gestao.grafico.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import br.com.sistema.gestao.grafico.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Endereco obj INNER JOIN obj.gestor gest INNER JOIN obj.empresagestores empreG WHERE gest.idGestor = :idGestor OR empreG.nrCnpj = :cnpj")
	Endereco findByIdGestoresORIdEmpresaGestor(@Param("idGestor") Integer Id, @Param("cnpj") Long cnpj);
	
}