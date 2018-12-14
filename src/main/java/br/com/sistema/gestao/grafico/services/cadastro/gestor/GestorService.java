package br.com.sistema.gestao.grafico.services.cadastro.gestor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.EmpresaGestor;
import br.com.sistema.gestao.grafico.domain.Endereco;
import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.Municipio;
import br.com.sistema.gestao.grafico.dto.gestor.GestorDTO;
import br.com.sistema.gestao.grafico.dto.gestor.GestorNewDTO;
import br.com.sistema.gestao.grafico.repositories.EmpresaGestorRepository;
import br.com.sistema.gestao.grafico.repositories.EnderecoRepository;
import br.com.sistema.gestao.grafico.repositories.GestorRepository;
import br.com.sistema.gestao.grafico.repositories.MunicipioRepository;
import br.com.sistema.gestao.grafico.services.exceptions.DataIntegrityException;
import br.com.sistema.gestao.grafico.services.exceptions.ObjectNotFoundException;

@Service
public class GestorService {
	
	@Autowired
	EnderecoRepository endRepo;

	@Autowired
	MunicipioRepository muniRepo;

	@Autowired
	GestorRepository gestorRepo;

	@Autowired
	EmpresaGestorRepository empresaGestorRepo;

	public Gestor find(Integer id) {

		Optional<Gestor> obj = gestorRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Gestor.class.getName()));

	}

	public List<Gestor> findAll() {
		return gestorRepo.findAll();
	}

	@Transactional
	public Gestor insert(Gestor obj) {
		obj.setIdGestor(null);
		endRepo.save(obj.getEndereco());
		obj = gestorRepo.save(obj);
		
		return obj;
	}

	public Gestor update(Gestor obj) {
		Gestor newObj = find(obj.getIdGestor());
		updateData(newObj, obj);
		return gestorRepo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			gestorRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public Gestor fromDTO(GestorDTO objDto) {
		return new Gestor(objDto.getIdGestor(), objDto.getNome(), objDto.getNrCpf(), objDto.getDsLogin(),
				objDto.getNrPassword(), objDto.getDsCargo(), null, null, null, null, null, null, null);
	}

	public Gestor fromDTO(GestorNewDTO objDto) {

		Optional<EmpresaGestor> empresagestor = empresaGestorRepo.findById(objDto.getIdEmmpresagestor());
		
		
		Optional<Municipio> muni = muniRepo.findById(objDto.getIdIbge());
		
		
		Endereco end = new Endereco(null, objDto.getDsEndereco(), objDto.getNrEndereco(), objDto.getDsComplemento(),
				objDto.getNrCep(), muni.get(), null, null);

		
		Gestor gest = new Gestor(null, objDto.getNome(), objDto.getNrCpf(), objDto.getDsLogin(),
				objDto.getNrPassword(), objDto.getDsCargo(), empresagestor.get(), end, null, null, null, null, null);
		
		return gest;
	}

	private void updateData(Gestor newObj, Gestor obj) {
		newObj.setNome(obj.getNome());
		newObj.setNrCpf(obj.getNrCpf());
		newObj.setDsCargo(obj.getDsCargo());
		newObj.setDsLogin(obj.getDsLogin());
		newObj.setNrPassword(obj.getNrPassword());
	}

	public Page<Gestor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction, String nome) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return gestorRepo.findDistinctByNomeContaining(nome, pageRequest);
	}

}
