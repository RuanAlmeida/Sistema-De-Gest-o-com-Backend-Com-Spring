package br.com.sistema.gestao.grafico.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.gestao.grafico.dto.contatos.ContatoDTO;
import br.com.sistema.gestao.grafico.services.cadastro.contato.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/contatos")
public class ContatosResource {

	@Autowired
	ContatoService contatosservice;

	@ApiOperation(value = "Buscar os contatos pelo ID do Gestor")
	@RequestMapping(value = "porgestor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findForGestor(@PathVariable ContatoDTO objDTO) {
		Object obj = contatosservice.findForGesto(objDTO);
		return ResponseEntity.ok().body(obj);
	}


	@ApiOperation(value = "Inserir um novo contato de um gestor podendo ser um e-mail ou telefone.")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ContatoDTO objDTO) {
		objDTO = contatosservice.insert(objDTO);
		return ResponseEntity.ok().build();
	}


	@ApiOperation(value = "Remover o contato de um gestor podendo ser um e-mail ou telefone. Tem que passar o tipo se telefone = 't', se email = 'e'.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir o contato"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@RequestMapping(value = "/{id}/{tipo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id, @PathVariable char tipo) {
		contatosservice.delete(id, tipo);
		return ResponseEntity.noContent().build();
	}
}
