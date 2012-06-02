package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.triadworks.issuetracker.dao.UsuarioDao;
import br.com.triadworks.issuetracker.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext(unitName = "issueTrackerPU")
	private EntityManager entityManager;

	@Override
	public List<Usuario> listaTudo() {
		return entityManager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	@Override
	@Transactional
	public void salva(Usuario usuario) {
		entityManager.persist(usuario);
	}

	@Override
	@Transactional
	public void atualiza(Usuario usuario) {
		entityManager.merge(usuario);
	}

	@Override
	@Transactional
	public void remove(Usuario usuario) {
		entityManager.remove(entityManager.merge(usuario));
	}

	@Override
	public Usuario buscaPor(String login, String senha) {
		return (Usuario) createCriteria().add(Restrictions.eq("login", login))
				.add(Restrictions.eq("senha", senha)).uniqueResult();
	}

	@Override
	public Usuario carrega(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	private Criteria createCriteria() {
		Session session = ((Session) entityManager.getDelegate());
		return session.createCriteria(Usuario.class);
	}

	@PreDestroy
	public void cleanup() {
		if (this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}

}
