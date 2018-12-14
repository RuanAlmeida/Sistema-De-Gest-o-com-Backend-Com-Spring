package br.com.sistema.gestao.grafico.services.cadastro.contato;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sistema.gestao.grafico.domain.Email;
import br.com.sistema.gestao.grafico.domain.Gestor;
import br.com.sistema.gestao.grafico.domain.Telefone;
import br.com.sistema.gestao.grafico.dto.contatos.ContatoDTO;
import br.com.sistema.gestao.grafico.repositories.EmailRepository;
import br.com.sistema.gestao.grafico.repositories.GestorRepository;
import br.com.sistema.gestao.grafico.repositories.TelefoneRepository;
import br.com.sistema.gestao.grafico.services.exceptions.DataIntegrityException;
import br.com.sistema.gestao.grafico.services.exceptions.ObjectNotFoundException;

@Service
public class ContatoService {

	@Autowired
	TelefoneRepository foneRepo;

	@Autowired
	EmailRepository mailRepo;

	@Autowired
	GestorRepository gestorRepo;

	public Object findForGesto(ContatoDTO obj) {

		Object contatos = gestorRepo.findByIdGestoContatol(obj.getIdGestor());

		if (contatos == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + obj.getIdGestor() + ", Tipo: " + ContatoDTO.class.getName());
		}
		return contatos;
	}

	public Email findByIdEmail(Integer id) {

		Optional<Email> obj = mailRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Gestor.class.getName()));

	}

	public Telefone findByIdFone(Integer id) {

		Optional<Telefone> obj = foneRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Gestor.class.getName()));

	}

	@Transactional
	public ContatoDTO insert(ContatoDTO objDTO) {

		if (objDTO.getDsEmail().size() != 0) {
			for (int i = 0; i < objDTO.getDsEmail().size(); i++) {
				objDTO.setIdEmail(null);
				Optional<Gestor> gest = gestorRepo.findById(objDTO.getIdGestor());
				Email mail = new Email(null, objDTO.getDsEmail().get(i), gest.get());
				mailRepo.save(mail);

			}
		}

		if (objDTO.getDsTelefone().size() != 0) {
			for (int i = 0; i < objDTO.getDsTelefone().size(); i++) {
				objDTO.setIdEmail(null);
				Optional<Gestor> gest = gestorRepo.findById(objDTO.getIdGestor());
				Telefone fone = new Telefone(null, objDTO.getTpTelefone().get(i), objDTO.getDsTelefone().get(i),
						gest.get());
				foneRepo.save(fone);

			}
		}

		return objDTO;
	}

	public void delete(Integer contato, char tipoContato) {

		if (tipoContato == 'e') {

			findByIdEmail(contato);
			try {
				mailRepo.deleteById(contato);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidades relacionados");
			}
		}
		if (tipoContato == 't') {

			findByIdFone(contato);
			try {
				foneRepo.deleteById(contato);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidades relacionados");
			}
		}

	}

}
