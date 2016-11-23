package ejb;

import dao.interfaces.BankAccountDAO;
import dao.interfaces.BuyerDAO;
import dao.interfaces.ShareDAO;
import entities.BankAccount;
import entities.Buyer;
import entities.Share;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Local(BuyShareLocalBean.class)
public class BuyShareEJB implements BuyShareLocalBean {
    @EJB
    private BuyerDAO buyerDAOimpl;
    @EJB
    private ShareDAO shareDAOimpl;
    @EJB
    private BankAccountDAO bankAccountDAO;
    @Resource
    private UserTransaction userTransaction;
    private static final Logger log = Logger.getLogger(BuyShareEJB.class);

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void buy(int buyerID, int shareID) {

        // share
        Share share = shareDAOimpl.read(shareID);
        int priceOfShare = share.getPrice();
        // new owner
        Buyer newOwner = buyerDAOimpl.read(buyerID);
        BankAccount bankAccountNewOwner = newOwner.getBankAccount();
        // prev owner
        Buyer prevOwner = share.getBuyer();
        BankAccount bankAccountPrevOwner = prevOwner.getBankAccount();

        // update owner of share && clean exchange of share
        try {
            userTransaction.begin();
            // +- money

            log.info(bankAccountNewOwner.getMoneyOnBankAccount());
            if (bankAccountNewOwner.getMoneyOnBankAccount() < priceOfShare) {
                throw new Exception("Not enough money");
            }
            bankAccountNewOwner.setMoneyOnBankAccount(bankAccountNewOwner.getMoneyOnBankAccount());
            shareDAOimpl.updateOwner(shareID, newOwner);
            shareDAOimpl.removeShareFromExchange(shareID);

            bankAccountPrevOwner.setMoneyOnBankAccount(bankAccountPrevOwner.getMoneyOnBankAccount() + priceOfShare);
            // transaction of money
            bankAccountDAO.update(newOwner.getBankAccount().getIdBankAccount(), bankAccountNewOwner);
            bankAccountDAO.update(prevOwner.getBankAccount().getIdBankAccount(), bankAccountPrevOwner);
            userTransaction.commit();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
            try {
                userTransaction.rollback();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
