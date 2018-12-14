package br.com.sistema.gestao.grafico.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sistema.gestao.grafico.domain.EmpresaGestor;
import br.com.sistema.gestao.grafico.dto.empresagestor.EmpresaGestorDTO;
import br.com.sistema.gestao.grafico.dto.empresagestor.EmpresaGestorNewDTO;
import br.com.sistema.gestao.grafico.services.cadastro.empresagestor.EmpresaGestorService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/empresasgestores")
public class EmpresaGestorResource {

	@Autowired
	EmpresaGestorService empresaeestorservice;

	@ApiOperation(value = "Buscar Empresa onde o Gestor trabalhar pelo ID do Gestor")
	@RequestMapping(value = "porgestor/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpresaGestor> findForGestor(@PathVariable Integer idGestor) {
		EmpresaGestor obj = empresaeestorservice.findForGestor(idGestor);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Buscar Empresa onde o Gestor trabalhar pelo CNPJ")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpresaGestor> find(@PathVariable Integer id) {
		EmpresaGestor obj = empresaeestorservice.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Inserir uma nova Empresa onde o Gestor trabalhar")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EmpresaGestorNewDTO objDto) {
		EmpresaGestor obj = empresaeestorservice.fromDTO(objDto);
		obj = empresaeestorservice.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getNrCnpj())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualizar a Empresa onde o Gestor trabalhar pelo CNPJ")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EmpresaGestorDTO objDto, @PathVariable Integer id) {
		EmpresaGestor obj = empresaeestorservice.fromDTO(objDto);
		obj.setIdEmpresaGestor(id);
		obj = empresaeestorservice.update(obj);
		return ResponseEntity.noContent().build();
	}


}
