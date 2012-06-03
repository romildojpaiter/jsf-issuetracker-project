package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Comentario;
import br.com.triadworks.issuetracker.model.Issue;
import br.com.triadworks.issuetracker.qualifier.Unit;

public class IssueDaoImpl implements IssueDao {

	@Inject @Unit(name="issueTrackerPU")
	private EntityManager entityManager;

	@Override
	public List<Issue> listaTudo() {
		return entityManager.createQuery("from Issue", Issue.class)
				.getResultList();
	}

	@Override
	@Transactional
	public void salva(Issue issue) {
		entityManager.persist(issue);
	}

	@Override
	@Transactional
	public void atualiza(Issue issue) {
		entityManager.merge(issue);
	}

	@Override
	@Transactional
	public void remove(Issue issue) {
		entityManager.remove(entityManager.merge(issue));
	}

	@Override
	public Issue carrega(Long id) {
		return entityManager.find(Issue.class, id);
	}

	@Override
	public List<Issue> getIssuesDoUsuario(Long id) {
		return entityManager
				.createQuery("from Issue where assinadoPara.id = :id",
						Issue.class).setParameter("id", id).getResultList();
	}

	@Override
	@Transactional
	public void comenta(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.comenta(comentario); // thanks persistence context ;-)
	}

	@Override
	@Transactional
	public void fecha(Long id, Comentario comentario) {
		Issue issue = carrega(id);
		issue.fecha(comentario); // thanks persistence context ;-)
	}

	@PreDestroy
	public void cleanup() {
		if (this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}

}
