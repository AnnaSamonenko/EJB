package ejb;

import dao.impl.BankAccountDAOimpl;
import dao.interfaces.BankAccountDAO;
import dao.interfaces.BuyerDAO;
import entities.BankAccount;
import entities.Buyer;

import javax.ejb.*;

@Stateless
@Local(BankAccountLocalBean.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BankAccountEJB implements BankAccountLocalBean {
    @EJB
    private BuyerDAO buyerDAOimpl;
    @EJB
    private BankAccountDAO bankAccountDAO;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void create(int buyerID){
        bankAccountDAO.create(buyerID);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addMoney(int buyerID, int moneyToAdd) {
        Buyer buyerUpdate = buyerDAOimpl.read(buyerID);
        BankAccount bankAccount = buyerUpdate.getBankAccount();
        bankAccount.setMoneyOnBankAccount(bankAccount.getMoneyOnBankAccount() + moneyToAdd);
        BankAccountDAO bankAccountDAOimpl = new BankAccountDAOimpl();
        bankAccountDAOimpl.update(bankAccount.getIdBankAccount(), bankAccount);
    }
}