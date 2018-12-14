package br.com.sistema.gestao.grafico.services.cadastro.perfis;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.ModuloIQS;
import br.com.sistema.gestao.grafico.domain.ModuloIRS;
import br.com.sistema.gestao.grafico.domain.Perfil;
import br.com.sistema.gestao.grafico.domain.PerfilModulosIQS;
import br.com.sistema.gestao.grafico.domain.PerfilModulosIRS;
import br.com.sistema.gestao.grafico.domain.PerfisGestores;
import br.com.sistema.gestao.grafico.domain.Visao;
import br.com.sistema.gestao.grafico.dto.perfil.PerfiGestorlDTO;
import br.com.sistema.gestao.grafico.dto.perfil.PerfilNewDTO;
import br.com.sistema.gestao.grafico.repositories.GestorRepository;
import br.com.sistema.gestao.grafico.repositories.ModuloIQSRepository;
import br.com.sistema.gestao.grafico.repositories.ModuloIRSRepository;
import br.com.sistema.gestao.grafico.repositories.PerfilModulosIQSRepository;
import br.com.sistema.gestao.grafico.repositories.PerfilModulosIRSRepository;
import br.com.sistema.gestao.grafico.repositories.PerfilRepository;
import br.com.sistema.gestao.grafico.repositories.PerfisGestoresRepository;
import br.com.sistema.gestao.grafico.repositories.VisaoRepository;
import br.com.sistema.gestao.grafico.services.exceptions.DataIntegrityException;

@Service
public class PerfilService {

	@Autowired
	ModuloIQSRepository miqsRepo;

	@Autowired
	ModuloIRSRepository mirsRepo;

	@Autowired
	VisaoRepository visaoRepo;

	@Autowired
	PerfilRepository perfilRepo;

	@Autowired
	PerfilModulosIRSRepository perfilIRSRepo;

	@Autowired
	PerfilModulosIQSRepository perfilIQSRepo;

	@Autowired
	PerfilModulosIQSRepository pmiqsRepo;

	@Autowired
	PerfilModulosIRSRepository pmirsRepo;

	@Autowired
	PerfisGestoresRepository pgRepo;

	@Autowired
	GestorRepository gestorRepo;

	public List<ModuloIQS> findAllIQS() {
		return miqsRepo.findAll();
	}

	public List<ModuloIRS> findAllIRS() {
		return mirsRepo.findAll();
	}

	public List<Visao> findAllVisoes() {
		return visaoRepo.findAll();
	}

	public List<Perfil> findAllPerfis() {
		return perfilRepo.findAll();
	}

	public List<PerfilModulosIQS> findAllPMIQS() {
		return pmiqsRepo.findAll();
	}

	public List<PerfilModulosIRS> findAllPMIRS() {
		return pmirsRepo.findAll();
	}

	public Gestor findByIdGestor(Integer idGestor) {
		Gestor pg = pgRepo.findByIdGestor(idGestor);
		return pg;
	}

	@Transactional
	public PerfiGestorlDTO insertExist(PerfiGestorlDTO objDTO) {

		saveGestorPerfilIQS(objDTO);

		saveGestorPerfilIRS(objDTO);

		return objDTO;
	}

	private void saveGestorPerfilIQS(PerfiGestorlDTO objDTO) {
		for (int i = 0; i < objDTO.getPerfilIQS().size(); i++) {

			Optional<PerfilModulosIQS> pIQS = perfilIQSRepo.findById(objDTO.getPerfilIQS().get(i));
			Optional<Gestor> gest = gestorRepo.findById(objDTO.getIdGestor());

			PerfisGestores verifyProfileGestorIQS = pgRepo.findVerifyProfileAndGestorIQS(gest.get().getIdGestor(),
					objDTO.getPerfilIQS().get(i));

			if (verifyProfileGestorIQS == null) {
				PerfisGestores pg = new PerfisGestores(null, gest.get(), pIQS.get(), null);
				pgRepo.save(pg);
			}

		}
	}

	private void saveGestorPerfilIRS(PerfiGestorlDTO objDTO) {
		for (int i = 0; i < objDTO.getPerfilIRS().size(); i++) {

			Optional<PerfilModulosIRS> pIRS = perfilIRSRepo.findById(objDTO.getPerfilIRS().get(i));

			PerfisGestores verifyProfileGestorIRS = pgRepo.findVerifyProfileAndGestorIRS(objDTO.getIdGestor(),
					objDTO.getPerfilIRS().get(i));

			if (verifyProfileGestorIRS == null) {
				vincularGestorComPerfilIRS(objDTO.getIdGestor(), pIRS.get());
			}
		}
	}

	@Transactional
	public PerfilNewDTO insertNew(PerfilNewDTO pDTO) {

		for (int i = 0; i < pDTO.getDsPerfil().size(); i++) {
			Perfil profile = new Perfil(null, pDTO.getDsPerfil().get(i), null, null);
			Perfil pl = perfilRepo.save(profile);
			functionSavePerfilMIQS(pDTO, pl);
			functionSavePerfilMIRS(pDTO, pl);
		}

		return pDTO;

	}

	private void functionSavePerfilMIRS(PerfilNewDTO pDTO, Perfil pl) {
		for (int j = 0; j < pDTO.getModuloIRS().size(); j++) {
			Optional<ModuloIRS> mirl = mirsRepo.findById(pDTO.getModuloIRS().get(j));
			PerfilModulosIRS pmirs = new PerfilModulosIRS(null, pl, mirl.get(), null);
			pmirs = pmirsRepo.save(pmirs);

			if (pmirs != null) {
				vincularGestorComPerfilIRS(pDTO.getIdGestor(), pmirs);
			}
		}
	}

	private void functionSavePerfilMIQS(PerfilNewDTO pDTO, Perfil pl) {
		for (int i = 0; i < pDTO.getModuloIQS().size(); i++) {
			Optional<ModuloIQS> miql = miqsRepo.findById(pDTO.getModuloIQS().get(i));
			PerfilModulosIQS pmiql = new PerfilModulosIQS(null, pl, miql.get(), null);
			pmiql = pmiqsRepo.save(pmiql);

			if (pmiql != null) {
				vincularGestorComPerfilIQS(pDTO.getIdGestor(), pmiql);
			}
		}
	}

	private void vincularGestorComPerfilIQS(Integer idGestor, PerfilModulosIQS pIQS) {
		Optional<Gestor> gest = gestorRepo.findById(idGestor);
		PerfisGestores pg = new PerfisGestores(null, gest.get(), pIQS, null);
		pgRepo.save(pg);
	}

	private void vincularGestorComPerfilIRS(Integer idGestor, PerfilModulosIRS pIRS) {
		Optional<Gestor> gest = gestorRepo.findById(idGestor);
		PerfisGestores pg = new PerfisGestores(null, gest.get(), null, pIRS);
		pgRepo.save(pg);
	}

	public void delete(List<Integer> idPerfilGestor) {
		for (int i = 0; i < idPerfilGestor.size(); i++) {
			Optional<PerfisGestores> pg = pgRepo.findById(idPerfilGestor.get(i));
			try {
				pgRepo.deleteById(pg.get().getIdPerfilGestor());
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidaes relacionados");
			}
		}
	}
}
