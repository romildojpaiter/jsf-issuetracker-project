package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import br.com.triadworks.issuetracker.dao.ProjetoDao;
import br.com.triadworks.issuetracker.model.Projeto;
import br.com.triadworks.issuetracker.qualifier.Unit;

public class ProjetoDaoImpl implements ProjetoDao {

	@Inject @Unit
	private EntityManager entityManager;

	@Override
	public List<Projeto> listaTudo() {
		return entityManager.createQuery("from Projeto", Projeto.class)
				.getResultList();
	}

	@Override
	@Transactional
	public void salva(Projeto projeto) {
		entityManager.persist(projeto);
	}

	@Override
	@Transactional
	public void atualiza(Projeto projeto) {
		entityManager.merge(projeto);
	}

	@Override
	@Transactional
	public void remove(Projeto projeto) {
		entityManager.remove(entityManager.merge(projeto));
	}

	@Override
	@Transactional
	public Projeto carrega(Long id) {
		return entityManager.find(Projeto.class, id);
	}

	@PreDestroy
	public void cleanup() {
		if (this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}

}
