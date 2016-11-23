package ejb;

import dao.interfaces.ExchangeDAO;
import dao.interfaces.ShareDAO;
import entities.Exchange;
import entities.Share;

import javax.ejb.*;

@Stateless
@Local(SellShareLocalBean.class)
public class SellShareEJB implements SellShareLocalBean{
    @EJB
    private ShareDAO shareDAOimpl;
    @EJB
    private ExchangeDAO exchangeDAOimpl;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addShare(int exchangeID, int shareID) {
        Exchange exchange = exchangeDAOimpl.read(exchangeID);
        Share share = shareDAOimpl.read(shareID);
        // method update requiersNew
        exchangeDAOimpl.update(exchangeID, share);
        shareDAOimpl.update(shareID, exchange);
    }
}
