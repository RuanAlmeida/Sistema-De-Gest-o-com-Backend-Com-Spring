package br.com.sistema.gestao.grafico.services.cadastro.empresagestor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.EmpresaGestor;
import br.com.sistema.gestao.grafico.domain.Endereco;
import br.com.sistema.gestao.grafico.domain.Municipio;
import br.com.sistema.gestao.grafico.dto.empresagestor.EmpresaGestorDTO;
import br.com.sistema.gestao.grafico.dto.empresagestor.EmpresaGestorNewDTO;
import br.com.sistema.gestao.grafico.repositories.EmpresaGestorRepository;
import br.com.sistema.gestao.grafico.repositories.EnderecoRepository;
import br.com.sistema.gestao.grafico.repositories.MunicipioRepository;
import br.com.sistema.gestao.grafico.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresaGestorService {

	@Autowired
	EnderecoRepository endRepo;

	@Autowired
	MunicipioRepository muniRepo;

	@Autowired
	EmpresaGestorRepository empresagestorrepository;

	public EmpresaGestor findForGestor(Integer IdGestor) {
		EmpresaGestor obj = empresagestorrepository.findByIdGestor(IdGestor);
		return obj;
	}

	public EmpresaGestor find(Integer id) {
		Optional<EmpresaGestor> obj = empresagestorrepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + EmpresaGestor.class.getName()));

	}

	@Transactional
	public EmpresaGestor insert(EmpresaGestor obj) {
		endRepo.save(obj.getEndereco());
		obj = empresagestorrepository.save(obj);
		return obj;
	}

	public EmpresaGestor update(EmpresaGestor obj) {
		EmpresaGestor newObj = find(obj.getIdEmpresaGestor());
		updateData(newObj, obj);
		return empresagestorrepository.save(newObj);
	}

	public EmpresaGestor fromDTO(EmpresaGestorDTO objDto) {
		return new EmpresaGestor(objDto.getIdEmpresaGestor(), objDto.getNrCnpj(), objDto.getDsRazaoSocial(),
				objDto.getNrCnes(), null, null);
	}

	public EmpresaGestor fromDTO(EmpresaGestorNewDTO objDto) {

		Optional<Municipio> muni = muniRepo.findById(objDto.getIdIbge());
		Endereco end = new Endereco(null, objDto.getDsEndereco(), objDto.getNrEndereco(), objDto.getDsComplemento(),
				objDto.getNrCep(), muni.get(), null, null);
		EmpresaGestor emprGest = new EmpresaGestor(objDto.getIdEmpresaGestor(), objDto.getNrCnpj(),
				objDto.getDsRazaoSocial(), objDto.getNrCnes(), end, null);
		emprGest.setEndereco(end);
		return emprGest;
	}

	private void updateData(EmpresaGestor newObj, EmpresaGestor obj) {
		newObj.setDsRazaoSocial(obj.getDsRazaoSocial());
		newObj.setIdEmpresaGestor(obj.getIdEmpresaGestor());
		newObj.setNrCnpj(obj.getNrCnpj());
	}

}
