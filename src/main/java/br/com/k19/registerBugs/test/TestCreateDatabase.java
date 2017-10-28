package br.com.k19.registerBugs.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestCreateDatabase {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("register-bugs");
        EntityManager manager = factory.createEntityManager();

        manager.close();
    }
}
