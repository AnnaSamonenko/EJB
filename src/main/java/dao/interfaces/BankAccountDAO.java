package dao.interfaces;

import entities.BankAccount;

import javax.ejb.Local;

@Local
public interface BankAccountDAO {
    void create(int buyerID);

    void update(int id, BankAccount bankAccount);

    void delete(int id);

    BankAccount read(int id);
}
