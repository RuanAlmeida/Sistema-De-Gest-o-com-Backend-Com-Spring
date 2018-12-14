package br.com.sistema.gestao.grafico.resources;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.dto.gestor.GestorDTO;
import br.com.sistema.gestao.grafico.dto.gestor.GestorNewDTO;
import br.com.sistema.gestao.grafico.services.cadastro.gestor.GestorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/gestores")
public class GestorResource {

	@Autowired
	GestorService gestorservice;


	@ApiOperation(value = "Buscar gestor pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Gestor> find(@PathVariable Integer id) {
		Gestor obj = gestorservice.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Listar gestores")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Stream<Object>> findAll() {
		List<Gestor> list = gestorservice.findAll();
		Stream<Object> listDto = list.stream().map(obj -> new GestorDTO(obj.getIdGestor(), obj.getNome(), obj.getNrCpf(), obj.getDsLogin(), obj.getNrPassword(), obj.getDsCargo()));  
		return ResponseEntity.ok().body(listDto);
	}

	@ApiOperation(value = "Inserir novo gestor")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody GestorNewDTO objDto) {
		Gestor obj = gestorservice.fromDTO(objDto);
		obj = gestorservice.insert(obj);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Atualizar o gestor pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody GestorDTO objDto, @PathVariable Integer id) {
		Gestor obj = gestorservice.fromDTO(objDto);
		obj.setIdGestor(id);
		obj = gestorservice.update(obj);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Remover gestor pelo id")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir o gestor"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		gestorservice.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Paginação de gestor, pesquisa por nome.")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<GestorDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction,
			@RequestParam(value="nome", defaultValue="nome") String nome) {
		Page<Gestor> list = gestorservice.findPage(page, linesPerPage, orderBy, direction, nome);
		Page<GestorDTO> listDto = list.map(obj -> new GestorDTO(obj.getIdGestor(), obj.getNome(), obj.getNrCpf(), obj.getDsLogin(), obj.getNrPassword(), obj.getDsCargo()));  
		return ResponseEntity.ok().body(listDto);
	}
	
}
