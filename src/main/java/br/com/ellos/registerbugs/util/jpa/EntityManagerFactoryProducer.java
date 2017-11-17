package br.com.ellos.registerbugs.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

    @Produces
    @RequestScoped
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("register-bugs");
    }

    public void close(@Disposes EntityManagerFactory factory) {
        factory.close();
    }
}
