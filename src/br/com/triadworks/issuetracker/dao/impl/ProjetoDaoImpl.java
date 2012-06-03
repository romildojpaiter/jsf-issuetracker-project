package br.com.triadworks.issuetracker.dao.impl;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.myfaces.extensions.cdi.jpa.api.Transactional;

import br.com.triadworks.issuetracker.dao.ProjetoDao;
import br.com.triadworks.issuetracker.model.Projeto;

@Dependent
@Named("projetoDao")//é acessado diretamente na tela(combo de projetos)
public class ProjetoDaoImpl implements ProjetoDao {

	@PersistenceContext(unitName="issueTrackerPU")
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
