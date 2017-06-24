package de.fau.amos.virtualledger.server.persistence;

import de.fau.amos.virtualledger.server.model.SavingsAccount;
import de.fau.amos.virtualledger.server.model.SavingsAccountToUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by Georg on 23.06.2017.
 */
@ApplicationScoped
public class SavingsAccountRepository {

    private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    EntityManagerFactory entityManagerFactory;

    @Inject
    public SavingsAccountRepository(EntityManagerFactoryProvider entityManagerFactoryProvider) {
        this.entityManagerFactory = entityManagerFactoryProvider.getEntityManagerFactory();
    }
    protected SavingsAccountRepository() { };


    public void createSavingsAccount(String email, SavingsAccount savingsAccount)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();
                entityManager.persist(savingsAccount);
                entityManager.persist(new SavingsAccountToUser(email, savingsAccount.id));
                entityTransaction.commit();
            } catch(EntityExistsException entityExistsException) {
                logger.info("Entity already exists: "+ savingsAccount);
                entityTransaction.rollback();
                throw entityExistsException;
            } catch(IllegalArgumentException persistenceException) {
                logger.warn("", persistenceException);
                entityTransaction.rollback();
                throw persistenceException;
            }
        }
        finally {
            entityManager.close();
        }
    }

    public List<SavingsAccount> getSavingsAccountsByUserEmail(final String email)
    {
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            final Query query = entityManager.createQuery("Select u FROM SavingsAccount u JOIN SavingsAccountToUser s WHERE s.email = :email");
            query.setParameter("email", email);
            final List resultList = query.getResultList();
            return resultList;
        } finally {
            entityManager.close();
        }
    }

    public void deleteSavingsAccountById(final int id)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try{
            final EntityTransaction entityTransaction = entityManager.getTransaction();
            try {
                entityTransaction.begin();

                Query query = entityManager.createQuery("Select s FROM SavingsAccount s WHERE s.id = :id");
                query.setParameter("id", id);
                List<SavingsAccount> savingsAccountList = query.getResultList();
                for(int i = 0; i < savingsAccountList.size(); ++i)
                {
                    SavingsAccount savingsAccount = savingsAccountList.get(i);
                    entityManager.remove(savingsAccount);
                }

                query = entityManager.createQuery("Select s FROM SavingsAccountToUser s WHERE s.id_savingsaccount = :id_savingsaccount");
                query.setParameter("id_savingsaccount", id);
                List<SavingsAccountToUser> savingsAccountToUserList = query.getResultList();
                for(int i = 0; i < savingsAccountToUserList.size(); ++i)
                {
                    SavingsAccountToUser savingsAccountToUser = savingsAccountToUserList.get(i);
                    entityManager.remove(savingsAccountToUser);
                }

                entityTransaction.commit();
            } catch(final Exception e) {
                logger.warn("", e);
                entityTransaction.rollback();
                throw e;
            }
        }
        finally {
            entityManager.close();
        }
    }
}