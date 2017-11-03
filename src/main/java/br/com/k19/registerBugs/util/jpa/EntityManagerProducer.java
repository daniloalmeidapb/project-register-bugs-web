package br.com.k19.registerBugs.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactory factory;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }

    public void close(@Disposes EntityManager manager) {
        manager.close();
    }
}
