package dao.interfaces;

import entities.Exchange;
import entities.Share;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Анна on 2/11/2016.
 */
@Local
public interface ExchangeDAO {
    void create(Exchange exchange);

    List<Exchange> findAllExchanges();

    void update(int id, Exchange exchange);

    void delete(int id);

    Exchange read(int id);

    void update(int exchangeID, Share share);
}
