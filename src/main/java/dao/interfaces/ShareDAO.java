package dao.interfaces;

import entities.Buyer;
import entities.Exchange;
import entities.Share;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

@Local
public interface ShareDAO {
    void create(Share sh, int idCompany, int idBuyer);

    List<Share> findAllShares();

    void update(int id, int price);

    void delete(int id);

    Share read(int id);

    ArrayList<Share> sellingShare();

    void removeShareFromExchange(int shareID);

    void updateOwner(int id, Buyer newBuyer);

    void update(int id, Exchange exchange);
}
