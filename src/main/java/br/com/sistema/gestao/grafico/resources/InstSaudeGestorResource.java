package br.com.sistema.gestao.grafico.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.gestao.grafico.domain.InstSaudeGestor;
import br.com.sistema.gestao.grafico.dto.instsauede.InstituicaoSaudeNewDTO;
import br.com.sistema.gestao.grafico.services.cadastro.instituicaosaude.InstituicaoSaudeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/instsaudes")
public class InstSaudeGestorResource {

	@Autowired
	InstituicaoSaudeService instSaudeservice;

	@ApiOperation(value = "Buscar Empresa onde o Gestor trabalhar pelo ID do Gestor")
	@RequestMapping(value = "porgestor/{id}", method = RequestMethod.GET)
	public ResponseEntity<InstSaudeGestor> findForGestor(@PathVariable Integer idGestor) {
		InstSaudeGestor obj = instSaudeservice.findForGestor(idGestor);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Inserir uma nova Empresa onde o Gestor trabalhar")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody List<InstituicaoSaudeNewDTO> objdto) {
		objdto = instSaudeservice.insert(objdto);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "Remover Empresa onde o Gestor trabalhar pelo CNPJ")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir a Empresa onde o Gestor trabalhar"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		instSaudeservice.delete(id);
		return ResponseEntity.noContent().build();
	}
}
