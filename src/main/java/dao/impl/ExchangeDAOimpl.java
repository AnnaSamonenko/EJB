package dao.impl;

import dao.interfaces.ExchangeDAO;
import entities.Exchange;
import entities.Share;
import org.apache.log4j.Logger;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local(ExchangeDAO.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ExchangeDAOimpl implements ExchangeDAO {
    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Share");
    private EntityManager entitymanager = emfactory.createEntityManager();
    private static final Logger log = Logger.getLogger(ExchangeDAOimpl.class);

    /**
     * Create exchange
     *
     * @param exchange
     */
    public void create(Exchange exchange) {
        entitymanager.getTransaction().begin();
        entitymanager.persist(exchange);
        entitymanager.getTransaction().commit();
        entitymanager.close();
    }

    /**
     * @return all exchanges
     */
    public List<Exchange> findAllExchanges() {
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createQuery("Select ex from Exchange ex");
        return (ArrayList<Exchange>) query.getResultList();
    }

    /**
     * Update name of Exchange
     *
     * @param id
     * @param update
     */
    public void update(int id, Exchange update) {
        EntityManager entitymanager = emfactory.createEntityManager();
        Exchange ex = entitymanager.find(Exchange.class, id);
        ex.setName(update.getName());
        entitymanager.getTransaction().begin();
        entitymanager.persist(ex);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        log.info("Update exchange with id: " + id);
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void update(int exchangeId, Share share) {
        try {
            if(1 == 1){
                throw new Exception("Update don't work");
            }
            EntityManager entitymanager = emfactory.createEntityManager();
            Exchange ex = entitymanager.find(Exchange.class, exchangeId);
            entitymanager.getTransaction().begin();
            ex.getShares().add(share);
            entitymanager.persist(ex);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            log.info("Update exchange with id: " + exchangeId);
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }
    /**
     * Delete exchanges by id
     *
     * @param id
     */
    public void delete(int id) {
        EntityManager entitymanager = emfactory.createEntityManager();
        Exchange ex = entitymanager.find(Exchange.class, id);
        entitymanager.getTransaction().begin();
        entitymanager.remove(ex);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        log.info("Delete exchange with id: " + id);
    }

    /**
     * Read exchanges by id
     *
     * @param id
     * @return exchange
     */
    public Exchange read(int id) {
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Exchange ex = entitymanager.find(Exchange.class, id);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        return ex;
    }
}
