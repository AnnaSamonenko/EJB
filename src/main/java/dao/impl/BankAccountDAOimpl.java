package dao.impl;

import dao.interfaces.BankAccountDAO;
import entities.BankAccount;
import entities.Buyer;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Stateless
@Local(BankAccountDAO.class)
public class BankAccountDAOimpl implements BankAccountDAO {
    private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Share");
    private EntityManager entitymanager = emfactory.createEntityManager();
    private static final Logger log = Logger.getLogger(BankAccountDAOimpl.class);

    public void create(int buyerID) {
        BankAccount bankAccount = new BankAccount(0);
        entitymanager.getTransaction().begin();
        Buyer buyer = entitymanager.find(Buyer.class, buyerID);
        entitymanager.persist(bankAccount);
        buyer.setBankAccount(bankAccount);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        log.info("Create bank account with id: " + bankAccount.getIdBankAccount());
    }

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void update(int id, BankAccount update) {
        EntityManager entitymanager = emfactory.createEntityManager();
        BankAccount bankAccount = entitymanager.find(BankAccount.class, id);
        bankAccount.setMoneyOnBankAccount(update.getMoneyOnBankAccount());
        entitymanager.getTransaction().begin();
        entitymanager.persist(bankAccount);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        log.info("Update bank account with id: " + id);
    }

    public void delete(int id) {
        EntityManager entitymanager = emfactory.createEntityManager();
        BankAccount bankAccount = entitymanager.find(BankAccount.class, id);
        entitymanager.getTransaction().begin();
        entitymanager.remove(bankAccount);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        log.info("Delete banl account with id: " + id);
    }

    public BankAccount read(int id) {
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        BankAccount bankAccount = entitymanager.find(BankAccount.class, id);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        return bankAccount;
    }
}
