package br.com.triadworks.issuetracker.producer;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.triadworks.issuetracker.qualifier.Unit;

/**
 * 
 * @author rmpestano
 * 
 */
@SessionScoped
public class EntityManagerProducer implements Serializable {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("issueTrackerPU");

	private EntityManager em;
	
	@Produces
	public EntityManager createEntityManager(InjectionPoint ip) {
		try {
			if (em == null || !em.isOpen()) {
				return emf.createEntityManager();
			} 
			else {
				return em;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
