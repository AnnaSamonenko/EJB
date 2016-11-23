package ejb;

import javax.ejb.Local;

@Local
public interface BankAccountLocalBean {
    void create(int buyerID) throws Exception;

    void addMoney(int buyerID, int moneyToAdd);
}
