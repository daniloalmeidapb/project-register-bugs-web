package br.com.k19.registerBugs.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JpaUtil {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("register-bugs");

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return this.factory.createEntityManager();
    }

    public void close(@Disposes EntityManager manager) {
        manager.close();
    }
}
