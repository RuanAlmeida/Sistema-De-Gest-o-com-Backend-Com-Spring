package br.com.sistema.gestao.grafico.services.cadastro.instituicaosaude;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.Bairro;
import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.InstSaudeGestor;
import br.com.sistema.gestao.grafico.domain.InstituicaoSaude;
import br.com.sistema.gestao.grafico.domain.Municipio;
import br.com.sistema.gestao.grafico.domain.TipoInstituicao;
import br.com.sistema.gestao.grafico.dto.instsauede.InstituicaoSaudeDTO;
import br.com.sistema.gestao.grafico.dto.instsauede.InstituicaoSaudeNewDTO;
import br.com.sistema.gestao.grafico.repositories.BairroRepository;
import br.com.sistema.gestao.grafico.repositories.GestorRepository;
import br.com.sistema.gestao.grafico.repositories.InstSaudeGestorRepository;
import br.com.sistema.gestao.grafico.repositories.InstituicaoSaudeRepository;
import br.com.sistema.gestao.grafico.repositories.MunicipioRepository;
import br.com.sistema.gestao.grafico.repositories.TipoInstituicaoRepository;
import br.com.sistema.gestao.grafico.services.exceptions.DataIntegrityException;
import br.com.sistema.gestao.grafico.services.exceptions.ObjectNotFoundException;

@Service
public class InstituicaoSaudeService {

	@Autowired
	InstituicaoSaudeRepository instSaudeRepo;

	@Autowired
	InstSaudeGestorRepository instgestorRepo;

	@Autowired
	GestorRepository gestorRepo;

	@Autowired
	MunicipioRepository muniRepo;

	@Autowired
	BairroRepository bairroRepo;

	@Autowired
	TipoInstituicaoRepository tipoInstRepo;

	public InstSaudeGestor findForGestor(Integer IdGestor) {
		InstSaudeGestor obj = instgestorRepo.findByIdGestores(IdGestor);
		return obj;
	}

	public InstituicaoSaude find(Integer id) {
		Optional<InstituicaoSaude> obj = instSaudeRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + InstituicaoSaude.class.getName()));

	}

	@Transactional
	public List<InstituicaoSaudeNewDTO> insert(List<InstituicaoSaudeNewDTO> objdto) {
		for (int i = 0; i < objdto.size(); i++) {
			System.err.println(objdto.get(i).getIdInstituicao());

			Optional<Gestor> gest = gestorRepo.findById(objdto.get(i).getIdGestor());

			Bairro bar = saveBairro(objdto.get(i), objdto.get(i).getIdIbge());
			TipoInstituicao tipo = saveTipoInstituicao(objdto, i);
			saveInstituicaoSaude(objdto.get(i), gest.get(), bar, tipo);
		}
		return objdto;
	}

	private InstituicaoSaude saveInstituicaoSaude(InstituicaoSaudeNewDTO objdto, Gestor gest, Bairro bar, TipoInstituicao tipo) {


		InstituicaoSaude verifyInstSaude = instSaudeRepo.findNaMao(objdto.getIdInstituicao());

		InstituicaoSaude inst = new InstituicaoSaude(objdto.getIdInstituicao(), objdto.getDsInstituicao(), tipo, bar,
				null);

		if (verifyInstSaude == null) {
			inst = instSaudeRepo.save(inst);
			saveInstituicaSaudeGestor(inst, gest);
			return inst;
		} else {
			saveInstituicaSaudeGestor(verifyInstSaude, gest);
			return verifyInstSaude;
		}
	}

	private TipoInstituicao saveTipoInstituicao(List<InstituicaoSaudeNewDTO> objdto, int i) {

		Optional<TipoInstituicao> verifyTipoInstituicao = tipoInstRepo.findById(objdto.get(i).getIdTipoInstituicao());

		TipoInstituicao tipo = new TipoInstituicao(objdto.get(i).getIdTipoInstituicao(),
				objdto.get(i).getDsTipoInstituicao(), null);

		if (verifyTipoInstituicao.isPresent() == false) {
			tipoInstRepo.save(tipo);
			return tipo;
		} else {
			return verifyTipoInstituicao.get();
		}
	}

	private Bairro saveBairro(InstituicaoSaudeNewDTO objdto, Integer idIbge) {

		Bairro veirifyBairro = bairroRepo.FindByDsBairro(objdto.getDsBairro());

		Optional<Municipio> muni = muniRepo.findById(idIbge);

		Bairro bar = new Bairro(null, objdto.getDsBairro(), muni.get(), null);

		if (veirifyBairro == null) {
			bar = bairroRepo.save(bar);
			return bar;
		} else {
			return veirifyBairro;
		}

	}

	private void saveInstituicaSaudeGestor(InstituicaoSaude inst, Gestor gestor) {
		InstSaudeGestor verifyInstSaudeGestor = instgestorRepo.findByIdGestorAndIdInstituicao(inst.getIdInstituicao(),
				gestor.getIdGestor());
		
		if (verifyInstSaudeGestor == null) {
			InstSaudeGestor instgestor = new InstSaudeGestor(null, gestor, inst);
			instgestorRepo.save(instgestor);
		}
	}

	public void delete(Integer id) {
		find(id);
		try {
			instSaudeRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidaes relacionados");
		}
	}

	public InstituicaoSaude fromDTO(InstituicaoSaudeDTO objDto) {
		return new InstituicaoSaude(objDto.getIdInstituicao(), objDto.getDsInstituicao(), null, null, null);
	}

}
