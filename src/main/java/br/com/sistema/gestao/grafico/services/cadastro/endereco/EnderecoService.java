package br.com.sistema.gestao.grafico.services.cadastro.endereco;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sistema.gestao.grafico.domain.Endereco;
import br.com.sistema.gestao.grafico.dto.endereco.EnderecoDTO;
import br.com.sistema.gestao.grafico.repositories.EnderecoRepository;
import br.com.sistema.gestao.grafico.repositories.GestorRepository;
import br.com.sistema.gestao.grafico.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository endRepo;

	@Autowired
	GestorRepository gestorRepo;

	public Endereco findForGestor(Integer idGestor, Long cnpj) {

		Endereco obj = endRepo.findByIdGestoresORIdEmpresaGestor(idGestor, cnpj);

		return obj;
	}

	public Endereco find(Integer id) {

		Optional<Endereco> obj = endRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));

	}

	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getIdEndereco());
		updateData(newObj, obj);
		return endRepo.save(newObj);
	}

	public Endereco fromDTO(EnderecoDTO objDto) {
		return new Endereco(objDto.getIdEndereco(), objDto.getDsEndereco(), objDto.getNrEndereco(),
				objDto.getDsComplemento(), objDto.getNrCep(), null, null, null);
	}


	private void updateData(Endereco newObj, Endereco obj) {
		newObj.setDsEndereco(obj.getDsEndereco());
		newObj.setNrEndereco(obj.getNrEndereco());
		newObj.setDsComplemento(obj.getDsComplemento());
		newObj.setNrCep(obj.getNrCep());
	}

}
