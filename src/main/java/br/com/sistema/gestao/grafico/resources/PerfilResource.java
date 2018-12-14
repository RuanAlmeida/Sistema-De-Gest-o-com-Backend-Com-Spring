package br.com.sistema.gestao.grafico.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.ModuloIQS;
import br.com.sistema.gestao.grafico.domain.ModuloIRS;
import br.com.sistema.gestao.grafico.domain.PerfilModulosIQS;
import br.com.sistema.gestao.grafico.domain.PerfilModulosIRS;
import br.com.sistema.gestao.grafico.dto.modulos.ModulosIQSDTO;
import br.com.sistema.gestao.grafico.dto.modulos.ModulosIRSDTO;
import br.com.sistema.gestao.grafico.dto.perfil.PerfiGestorlDTO;
import br.com.sistema.gestao.grafico.dto.perfil.PerfilNewDTO;
import br.com.sistema.gestao.grafico.services.cadastro.perfis.PerfilService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/perfis")
public class PerfilResource {

	@Autowired
	PerfilService perfilservice;

	@ApiOperation(value = "Listar ModulosIQS.")
	@RequestMapping(value = "modulosiqs/", method=RequestMethod.GET)
	public ResponseEntity<List<ModulosIQSDTO>> findAllIQS() {
		List<ModuloIQS> list = perfilservice.findAllIQS();
		List<ModulosIQSDTO> listDto = list.stream().map(obj -> new ModulosIQSDTO(obj.getIdModuloIQS(), obj.getDsModuloIQS())).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	@ApiOperation(value = "Listar ModulosIRS.")
	@RequestMapping(value = "modulosirs/", method=RequestMethod.GET)
	public ResponseEntity<List<ModulosIRSDTO>> findAllIRS() {
		List<ModuloIRS> list = perfilservice.findAllIRS();
		List<ModulosIRSDTO> listDto = list.stream().map(obj -> new ModulosIRSDTO(obj.getIdModuloIRS(), obj.getDsModuloIRS())).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@ApiOperation(value = "Listar PerfilModulosIQS.")
	@RequestMapping(value = "perfismodulosiqs/", method=RequestMethod.GET)
	public ResponseEntity<List<PerfilModulosIQS>> findAllPMIQS() {
		List<PerfilModulosIQS> list = perfilservice.findAllPMIQS();
		return ResponseEntity.ok().body(list);
	}

	
	@ApiOperation(value = "Listar PerfilModulosIRS.")
	@RequestMapping(value = "perfismodulosirs/", method=RequestMethod.GET)
	public ResponseEntity<List<PerfilModulosIRS>> findAllPMIRS() {
		List<PerfilModulosIRS> list = perfilservice.findAllPMIRS();
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value = "Buscar perfis do gestor pelo id")
	@RequestMapping(value = "/{idGestor}", method = RequestMethod.GET)
	public ResponseEntity<Gestor> find(@PathVariable Integer idGestor) {
		Gestor obj = perfilservice.findByIdGestor(idGestor);
		return ResponseEntity.ok().body(obj);
	}
	

	@ApiOperation(value = "Vincular perfis já existentes a um gestor")
	@RequestMapping(value = "perfisexistentes/", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PerfiGestorlDTO objDto) {
		perfilservice.insertExist(objDto);
		return ResponseEntity.ok().build();
	}

	
	@ApiOperation(value = "Cria novos perfis e vincula a um gestor")
	@RequestMapping(value = "perfisnovos/", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PerfilNewDTO pDTO) {
		perfilservice.insertNew(pDTO);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Remover vinculo de perfis com Gestor")
	@RequestMapping(value = "/{idPerfilGestor}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable List<Integer> idPerfilGestor) {
		perfilservice.delete(idPerfilGestor);
		return ResponseEntity.noContent().build();
	}
	
}
