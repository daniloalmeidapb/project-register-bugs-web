package br.com.ellos.registerbugs.util.jpa;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    @Inject
    private EntityManager manager;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        System.out.println("Opening transaction");
        this.manager.getTransaction().begin();

        Object result = context.proceed();

        this.manager.getTransaction().commit();
        System.out.println("Closing transaction");

        return result;
    }
}
