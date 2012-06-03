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
 * Apenas examplo de produtor de entity manager, no projeto estamos utilizando 
 * @PerstenceContext gerenciado pelo OpenWebBeans
 * 
 * @author rmpestano
 *
 */
@SessionScoped
public class EntityManagerProducer implements Serializable{
	
	
	@Produces @Unit @Dependent 
	public EntityManager createEntityManager(InjectionPoint ip){
		try{
			
		if(ip.getAnnotated().isAnnotationPresent(Unit.class)){
			Unit unit = ip.getAnnotated().getAnnotation(Unit.class);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory(unit.name());
			return emf.createEntityManager();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
