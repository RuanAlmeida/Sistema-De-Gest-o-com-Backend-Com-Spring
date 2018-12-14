package br.com.sistema.gestao.grafico.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.gestao.grafico.domain.Endereco;
import br.com.sistema.gestao.grafico.dto.endereco.EnderecoDTO;
import br.com.sistema.gestao.grafico.services.cadastro.endereco.EnderecoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	EnderecoService endservice;

	@ApiOperation(value = "Buscar endereço do gestor pelo ID do Gestor ou id da empresa do gestor")
	@RequestMapping(value = "porgestor/{idGestor}/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> findForGestor(@PathVariable Integer idGestor, @PathVariable Long cnpj) {
		Endereco obj = endservice.findForGestor(idGestor, cnpj);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Buscar endereço do gestor pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {
		Endereco obj = endservice.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Atualizar a endereço do gestor pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EnderecoDTO objDto, @PathVariable Integer id) {
		Endereco obj = endservice.fromDTO(objDto);
		obj.setIdEndereco(id);
		obj = endservice.update(obj);
		return ResponseEntity.noContent().build();
	}
}
