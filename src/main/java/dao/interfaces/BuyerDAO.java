package dao.interfaces;

import entities.Buyer;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Анна on 19/10/2016.
 */

@Local
public interface BuyerDAO {
    void create(Buyer buyer);

    List<Buyer> findAllBuyers();

    void update(int id, Buyer buyer);

    void delete(int id);

    Buyer read(int id);
}
